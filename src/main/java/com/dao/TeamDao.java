package com.dao;

import java.util.List;

import com.model.Team;

public interface TeamDao {

	Team getTeam();

	List<Team> getAllTeams();

}
