package com.dao.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dao.PlayerDao;
import com.dao.TeamDao;
import com.helper.PlayersUtility;
import com.model.Player;
import com.model.Team;

@Repository
public class TeamDaoImpl implements TeamDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	PlayerDao playerDao;

	@Override
	public Team getTeam(String owner) {

		Team team = jdbcTemplate.queryForObject(
				"select t.id as id,t.team_name as teamName, t.country as country, t.team_value as"
						+ " teamValue, t.TEAM_BUDGET as teamBudget, t.owner as owner from"
						+ " team t where t.owner = ?",
				new Object[] { owner }, new BeanPropertyRowMapper<Team>(Team.class));

		List<Player> players = playerDao.getPlayersBasedOnOwner(owner);
		team.setPlayers(players);

		return team;

	}

	@Override
	public List<Team> getAllTeams() {
		return Arrays.asList(PlayersUtility.getTeam());
	}

}
