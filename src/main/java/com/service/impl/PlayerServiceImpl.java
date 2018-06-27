package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.PlayerDao;
import com.dao.TeamDao;
import com.model.Player;
import com.model.Team;
import com.service.PlayerService;

@Service
public class PlayerServiceImpl implements PlayerService {

	@Autowired
	private PlayerDao playerDao;

	@Autowired
	private TeamDao teamDao;

	@Override
	@PreAuthorize("@accessManager.hasRole({ 'USER', 'ADMIN' })")
	public void addToTransferList(int id) {
		playerDao.addToTransferList(id);
	}

	@Override
	@PreAuthorize("@accessManager.hasRole({ 'USER', 'ADMIN' })")
	public void removeFromTransferList(int id) {
		playerDao.removeFromTransferList(id);
	}

	@Override
	@PreAuthorize("@accessManager.hasRole({ 'USER', 'ADMIN' })")
	public List<Player> getTransferList() {
		return playerDao.getTransferList();
	}

	@Override
	@Transactional
	@PreAuthorize("@accessManager.hasRole({ 'USER', 'ADMIN' })")
	public void buyPlayer(int id) {

		Player player = playerDao.getPlayersBasedOnId(id);
		Team team = teamDao.getTeam(player.getOwner());

		if (team.getTeamBudget().intValue() < player.getMarketValue().intValue()) {
			throw new RuntimeException("Team budget should be more than player value");
		}

		playerDao.buyPlayer(player);
	}

}
