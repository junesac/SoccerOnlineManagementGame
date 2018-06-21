package com.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dao.TeamDao;
import com.helper.PlayersUtility;
import com.model.Country;
import com.model.Player;
import com.model.PlayerType;
import com.model.Team;

@Repository
public class TeamDaoImpl implements TeamDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Team getTeam(String owner) {

		Team team = jdbcTemplate.queryForObject(
				"select t.id as id,t.team_name as teamName, t.country as country, t.team_value as"
						+ " teamValue, t.TEAM_BUDGET as teamBudget, t.owner as owner from"
						+ " team t where t.owner = ?",
				new Object[] { owner }, new BeanPropertyRowMapper<Team>(Team.class));

		String query = " select id, first_name, last_name, country, age, market_value, "
				+ "present_on_transfer_list, player_type," + " owner from player where owner = ?";

		List<Map<String, Object>> rows = jdbcTemplate.queryForList(query, new Object[] { owner });

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
			players.add(player);
		}
		team.setPlayers(players);

		return team;

	}

	@Override
	public List<Team> getAllTeams() {
		return Arrays.asList(PlayersUtility.getTeam());
	}

}
