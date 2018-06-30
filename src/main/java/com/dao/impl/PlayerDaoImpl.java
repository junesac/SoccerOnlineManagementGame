package com.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.constants.AppConstants;
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
		this.jdbcTemplate.update(AppConstants.UPDATE_PLAYER, 1, id);
	}

	@Override
	public void removeFromTransferList(int id) {
		this.jdbcTemplate.update(AppConstants.UPDATE_PLAYER, 0, id);
	}

	@Override
	public List<Player> getTransferList() {
		String owner = AppUtility.getOwner();
		String query = AppConstants.GET_PLAYER + " and PRESENT_ON_TRANSFER_LIST = ? and lower(p.owner) != ? ";
		return jdbcTemplate.query(query, new Object[] { 1, owner.toLowerCase() }, new PlayerMapper());
	}

	@Override
	public List<Player> getPlayersBasedOnOwner(String owner) {
		String query = AppConstants.GET_PLAYER + " and lower(p.owner) = ? ";
		return jdbcTemplate.query(query, new Object[] { owner.toLowerCase() }, new PlayerMapper());
	}

	@Override
	public Player getPlayersBasedOnId(int id) {
		String query = AppConstants.GET_PLAYER + " and p.id = ? ";
		return jdbcTemplate.query(query, new Object[] { id }, new PlayerMapper()).get(0);
	}

	@Override
	public void updatePlayers(List<Player> players) {
		String query = "update player set first_name = ?, last_name = ?, country = ?, market_value = ?, "
				+ "player_type = ?   where id = ?";
		for (Player player : players) {
			jdbcTemplate.update(query, player.getFirstName(), player.getLastName(), player.getCountry().toString(),
					player.getMarketValue().intValue(), player.getPlayerType().toString(), player.getId());
		}
	}

	@Override
	public void buyPlayer(Player player) {

		String owner = AppUtility.getOwner();
		this.jdbcTemplate.update("update player set PRESENT_ON_TRANSFER_LIST = ?, owner = ? where id = ?", 0, owner,
				player.getId());

	}

	@Override
	public Player createPlayer(Player player) {

		jdbcTemplate.update(
				"INSERT INTO player (id, first_name, last_name, country, age, market_value, "
						+ "present_on_transfer_list, player_type, owner) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
				player.getId(), player.getFirstName(), player.getLastName(), player.getCountry().toString(),
				player.getAge(), player.getMarketValue().intValue(), player.isPresentOnTransferList() ? 1 : 0,
				player.getPlayerType().toString(), player.getOwner());

		return player;
	}

	@Override
	public int checkPlayerNameAvailable(Player player) {

		return jdbcTemplate.queryForObject(
				"SELECT count(1) FROM player where lower(first_name) = ? and lower(last_name) = ? ",
				new Object[] { player.getFirstName().toLowerCase(), player.getLastName().toLowerCase() },
				Integer.class);

	}

}
