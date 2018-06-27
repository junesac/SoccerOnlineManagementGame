package com.service;

import java.util.List;

import com.model.Team;

public interface TeamService {

	Team getTeam();

	List<Team> getAllTeams();

	void saveTeam(Team team);

	Team getTeamById(int id);

}
