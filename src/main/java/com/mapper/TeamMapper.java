package com.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.model.Country;
import com.model.Team;

public class TeamMapper implements RowMapper<Team> {

	@Override
	public Team mapRow(ResultSet rs, int rowNum) throws SQLException {
		Team team = new Team();
		team.setId(rs.getLong(1));
		team.setTeamName(rs.getString(2));
		team.setCountry(Country.valueOf(rs.getString(3)));
		team.setTeamBudget(rs.getBigDecimal(4).toBigInteger());
		team.setOwner(rs.getString(5));
		return team;
	}
}