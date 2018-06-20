package com.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.helper.PlayersUtility;
import com.model.Team;

@Service
public class TeamService {

	public Team getTeam() {
		return PlayersUtility.getTeam();
	}

	@PreAuthorize("@accessManager.hasRole({ 'ADMIN' })")
	public List<Team> getAllTeams() {
		return Arrays.asList(PlayersUtility.getTeam());
	}

}
