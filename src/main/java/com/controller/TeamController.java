package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

	@RequestMapping("/getAllTeams")
	public List<Team> getAllTeams() {
		return teamService.getAllTeams();
	}

	@RequestMapping(value = "/saveTeam", method = RequestMethod.PUT)
	public Team saveTeam(@RequestBody Team team) {
		return teamService.saveTeam(team);
	}

}
