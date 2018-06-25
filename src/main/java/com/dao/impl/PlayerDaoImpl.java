package com.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dao.PlayerDao;
import com.helper.AppUtility;
import com.model.Country;
import com.model.Player;
import com.model.PlayerType;

@Repository
public class PlayerDaoImpl implements PlayerDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void addToTransferList(int id) {
		this.jdbcTemplate.update("update player set PRESENT_ON_TRANSFER_LIST = ? where id = ?", 1, id);
	}

	@Override
	public void removeFromTransferList(int id) {
		this.jdbcTemplate.update("update player set PRESENT_ON_TRANSFER_LIST = ? where id = ?", 0, id);
	}

	@Override
	public List<Player> getTransferList() {

		String owner = AppUtility.getOwner();

		String query = "select p.id, first_name, last_name, p.country, age, market_value,"
				+ " present_on_transfer_list, player_type, p.owner, t.team_name as teamname "
				+ "from team t, player p where t.owner=p.owner and PRESENT_ON_TRANSFER_LIST = ? and lower(p.owner) != ? ";

		List<Map<String, Object>> rows = jdbcTemplate.queryForList(query, 1, owner.toLowerCase());
		return playerMapper(rows);
	}

	@Override
	public List<Player> getPlayersBasedOnOwner(String owner) {
		String query = " select id, first_name, last_name, country, age, market_value, "
				+ "present_on_transfer_list, player_type," + " owner from player where owner = ?";

		List<Map<String, Object>> rows = jdbcTemplate.queryForList(query, new Object[] { owner });

		return playerMapper(rows);
	}

	public Player getPlayersBasedOnId(int id) {
		String query = " select id, first_name, last_name, country, age, market_value, "
				+ "present_on_transfer_list, player_type," + " owner from player where id = ?";
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(query, new Object[] { id });

		return playerMapper(rows).get(0);
	}

	private List<Player> playerMapper(List<Map<String, Object>> rows) {
		List<Player> players = new ArrayList<>();
		for (Map<String, Object> row : rows) {
			Player player = new Player();
			player.setId(((BigDecimal) row.get("id")).longValue());
			player.setFirstName((String) row.get("first_name"));
			player.setLastName((String) row.get("last_name"));
			player.setCountry(Country.valueOf((String) row.get("country")));
			player.setAge(((BigDecimal) row.get("AGE")).intValue());
			player.setMarketValue(((BigDecimal) row.get("market_value")).toBigInteger());
			player.setPresentOnTransferList(
					((BigDecimal) row.get("present_on_transfer_list")).intValue() > 0 ? true : false);
			player.setPlayerType(PlayerType.valueOf((String) row.get("player_type")));
			player.setOwner((String) row.get("owner"));
			player.setTeamName((String) row.get("teamname"));
			players.add(player);
		}
		return players;
	}

	@Override
	public void savePlayers(List<Player> players) {

		String query = "update player set first_name = ?, last_name = ?, country = ? where id = ?";

		for (Player player : players) {
			jdbcTemplate.update(query, player.getFirstName(), player.getLastName(), player.getCountry().toString(),
					player.getId());
		}

	}

	private Long getMaxId(String columnName, String tableName) {
		Long id = jdbcTemplate.queryForObject("select max(" + columnName + ") from " + tableName, Long.class);
		if (id == null) {
			id = 1l;
		}

		return id;
	}

	@Override
	public void buyPlayer(int id) {

		String owner = AppUtility.getOwner();

		Player player = getPlayersBasedOnId(id);

		// Let's send notification for the current owner
		Long notificationId = getMaxId("id", "notifications");

		jdbcTemplate.update(
				"INSERT INTO notifications (id, previousOwner, newOwner, seen, player_name) VALUES (?, ?, ?, ?, ?)",
				notificationId + 1, player.getOwner(), owner, 0, player.getFirstName() + " " + player.getLastName());

		this.jdbcTemplate.update("update player set PRESENT_ON_TRANSFER_LIST = ?, owner = ? where id = ?", 0, owner,
				id);

		// We Assumed that one owner can buy only one team.
		this.jdbcTemplate.update("update team set team_budget = team_budget- ? where owner = ?",
				player.getMarketValue().intValue(), owner);

	}

}
