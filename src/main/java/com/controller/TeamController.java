package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.model.Team;
import com.service.TeamService;

@RestController
@RequestMapping("/team")
public class TeamController {

	@Autowired
	private TeamService teamService;

	@RequestMapping("/getTeam")
	public Team getTeam() {
		return teamService.getTeam();
	}

	@RequestMapping("/getTeamById/{id}")
	public Team getTeamById(@PathVariable("id") int id) {
		return teamService.getTeamById(id);
	}

	@RequestMapping("/getAllTeams")
	public List<Team> getAllTeams() {
		return teamService.getAllTeams();
	}

	@RequestMapping(value = "/updateTeam", method = RequestMethod.PUT)
	public void updateTeam(@RequestBody Team team) {
		teamService.updateTeam(team);
	}

}
