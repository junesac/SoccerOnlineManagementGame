package com.dao;

import java.util.List;

import com.model.Player;
import com.model.Team;

public interface TeamDao {

	List<Team> getAllTeams();

	Team getTeam(String owner);

	void updateTeam(Team team);

	Team getTeamById(int id);

	void addPlayerToTeam(Player player);

	void createTeam(Team team);

}
