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
		List<Team> teams = teamDao.getAllTeams();

		/**
		 * We don't need player informations at the loading time of all teams.
		 */
		// for (Team team : teams) {
		// List<Player> players =
		// playerDao.getPlayersBasedOnOwner(team.getOwner());
		// team.setPlayers(players);
		// }

		return teams;
	}

	@Override
	@Transactional
	@PreAuthorize("@accessManager.hasRole({ 'USER', 'ADMIN' })")
	public void updateTeam(Team team) {
		teamDao.updateTeam(team);
		playerDao.updatePlayers(team.getPlayers());
	}

	@Override
	@PreAuthorize("@accessManager.hasRole({ 'ADMIN' })")
	public Team getTeamById(int id) {

		Team team = teamDao.getTeamById(id);
		List<Player> players = playerDao.getPlayersBasedOnOwner(team.getOwner());
		team.setPlayers(players);
		return team;
	}

}
