package com.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dao.TeamDao;
import com.mapper.TeamMapper;
import com.model.Team;

@Repository
public class TeamDaoImpl implements TeamDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Team getTeam(String owner) {

		String query = "select t.id as id,t.team_name as teamName, t.country as country, "
				+ "t.TEAM_BUDGET as teamBudget, t.owner as owner from team t where t.owner = ?";
		Team team = jdbcTemplate.queryForObject(query, new Object[] { owner }, new TeamMapper());
		return team;

	}

	@Override
	public List<Team> getAllTeams() {
		String query = "select * from team";
		return jdbcTemplate.query(query, new TeamMapper());
	}

	@Override
	public Team saveTeam(Team team) {
		String query = "update team set team_name = ?, country = ? where id = ?";
		jdbcTemplate.update(query, team.getTeamName(), team.getCountry().toString(), team.getId());
		return team;
	}

}
