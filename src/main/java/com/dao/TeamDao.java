package com.dao;

import java.util.List;

import com.model.Team;

public interface TeamDao {

	List<Team> getAllTeams();

	Team getTeam(String owner);

	Team saveTeam(Team team);

}
