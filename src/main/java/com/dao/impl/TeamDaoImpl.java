package com.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.constants.AppConstants;
import com.dao.TeamDao;
import com.helper.AppUtility;
import com.mapper.TeamMapper;
import com.model.Player;
import com.model.Team;

@Repository
public class TeamDaoImpl implements TeamDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Team getTeam(String owner) {

		String query = AppConstants.GET_TEAM + " where t.owner = ?";
		Team team = jdbcTemplate.queryForObject(query, new Object[] { owner }, new TeamMapper());
		return team;
	}

	@Override
	public List<Team> getAllTeams() {
		String query = "select * from team";
		return jdbcTemplate.query(query, new TeamMapper());
	}

	@Override
	public void updateTeam(Team team) {
		String query = "update team set team_name = ?, country = ?, team_budget = ?  where id = ?";
		jdbcTemplate.update(query, team.getTeamName(), team.getCountry().toString(), team.getTeamBudget().intValue(),
				team.getId());
	}

	@Override
	public Team getTeamById(int id) {
		String query = AppConstants.GET_TEAM + " where t.id = ?";
		Team team = jdbcTemplate.queryForObject(query, new Object[] { id }, new TeamMapper());
		return team;
	}

	@Override
	public void addPlayerToTeam(Player player) {

		String owner = AppUtility.getOwner();
		this.jdbcTemplate.update("update team set team_budget = team_budget - ? where owner = ?",
				player.getMarketValue().intValue(), owner);
	}

	@Override
	public void createTeam(Team team) {

		jdbcTemplate.update("INSERT INTO team (id, team_name, country, team_budget, owner) VALUES (?, ?, ?,  ?, ?)",
				team.getId(), team.getTeamName(), team.getCountry().toString(), team.getTeamBudget().intValue(),
				team.getOwner());
	}

}
