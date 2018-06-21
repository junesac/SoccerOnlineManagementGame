package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.dao.TeamDao;
import com.model.Team;
import com.service.TeamService;

@Service
public class TeamServiceImpl implements TeamService {

	@Autowired
	private TeamDao teamDao;

	@Override
	@PreAuthorize("@accessManager.hasRole({ 'USER' })")
	public Team getTeam() {
		return teamDao.getTeam();
	}

	@Override
	@PreAuthorize("@accessManager.hasRole({ 'ADMIN' })")
	public List<Team> getAllTeams() {
		return teamDao.getAllTeams();
	}

}
