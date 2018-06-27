package com.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dao.PlayerDao;
import com.helper.AppUtility;
import com.mapper.PlayerMapper;
import com.model.Player;

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

		return jdbcTemplate.query(query, new Object[] { 1, owner.toLowerCase() }, new PlayerMapper());
	}

	@Override
	public List<Player> getPlayersBasedOnOwner(String owner) {
		String query = "select p.id, first_name, last_name, p.country, age, market_value,"
				+ " present_on_transfer_list, player_type, p.owner, t.team_name as teamname "
				+ "from team t, player p where t.owner=p.owner and lower(p.owner) = ? ";

		return jdbcTemplate.query(query, new Object[] { owner.toLowerCase() }, new PlayerMapper());
	}

	@Override
	public Player getPlayersBasedOnId(int id) {

		String query = "select p.id, first_name, last_name, p.country, age, market_value,"
				+ " present_on_transfer_list, player_type, p.owner, t.team_name as teamname "
				+ "from team t, player p where t.owner=p.owner and p.id = ? ";

		return jdbcTemplate.query(query, new Object[] { id }, new PlayerMapper()).get(0);

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
	public void buyPlayer(Player player) {

		String owner = AppUtility.getOwner();

		// Let's send notification for the current owner
		Long notificationId = getMaxId("id", "notifications");

		jdbcTemplate.update(
				"INSERT INTO notifications (id, previousOwner, newOwner, seen, player_name) VALUES (?, ?, ?, ?, ?)",
				notificationId + 1, player.getOwner(), owner, 0, player.getFirstName() + " " + player.getLastName());

		this.jdbcTemplate.update("update player set PRESENT_ON_TRANSFER_LIST = ?, owner = ? where id = ?", 0, owner,
				player.getId());

		// We Assumed that one owner can buy only one team.
		this.jdbcTemplate.update("update team set team_budget = team_budget- ? where owner = ?",
				player.getMarketValue().intValue(), owner);

	}

}
