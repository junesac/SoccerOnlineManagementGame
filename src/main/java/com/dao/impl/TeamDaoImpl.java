package com.dao.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.dao.TeamDao;
import com.helper.PlayersUtility;
import com.model.Team;

@Repository
public class TeamDaoImpl implements TeamDao {

	@Override
	public Team getTeam() {
		return PlayersUtility.getTeam();
	}

	@Override
	public List<Team> getAllTeams() {
		return Arrays.asList(PlayersUtility.getTeam());
	}

}
