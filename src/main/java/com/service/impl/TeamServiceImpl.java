package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.PlayerDao;
import com.dao.TeamDao;
import com.model.Player;
import com.model.Team;
import com.service.TeamService;

@Service
public class TeamServiceImpl implements TeamService {

	@Autowired
	private TeamDao teamDao;

	@Autowired
	PlayerDao playerDao;

	@Override
	@PreAuthorize("@accessManager.hasRole({ 'USER', 'ADMIN' })")
	public Team getTeam() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String owner = auth.getName();
		List<Player> players = playerDao.getPlayersBasedOnOwner(owner);
		Team team = teamDao.getTeam(owner);
		team.setPlayers(players);
		return team;
	}

	@Override
	@PreAuthorize("@accessManager.hasRole({ 'ADMIN' })")
	public List<Team> getAllTeams() {
		return teamDao.getAllTeams();
	}

	@Override
	@Transactional
	@PreAuthorize("@accessManager.hasRole({ 'USER', 'ADMIN' })")
	public Team saveTeam(Team team) {
		Team result = teamDao.saveTeam(team);
		playerDao.savePlayers(result.getPlayers());
		return result;
	}

}
