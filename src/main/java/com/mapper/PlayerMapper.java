package com.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.model.Country;
import com.model.Player;
import com.model.PlayerType;

public class PlayerMapper implements RowMapper<Player> {

	@Override
	public Player mapRow(ResultSet rs, int rowNum) throws SQLException {
		Player player = new Player();
		player.setId(rs.getLong("id"));
		player.setFirstName(rs.getString("first_name"));
		player.setLastName(rs.getString("last_name"));
		player.setCountry(Country.valueOf(rs.getString("country")));
		player.setAge(rs.getBigDecimal("AGE").intValue());
		player.setMarketValue(rs.getBigDecimal("market_value").toBigInteger());
		player.setPresentOnTransferList((rs.getBigDecimal("present_on_transfer_list")).intValue() > 0 ? true : false);
		player.setPlayerType(PlayerType.valueOf(rs.getString("player_type")));
		player.setOwner(rs.getString("owner"));
		player.setTeamName(rs.getString("teamname"));
		return player;
	}

}