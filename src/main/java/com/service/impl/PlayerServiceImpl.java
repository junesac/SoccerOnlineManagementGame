package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.PlayerDao;
import com.model.Player;
import com.service.PlayerService;

@Service
public class PlayerServiceImpl implements PlayerService {

	@Autowired
	private PlayerDao playerDao;

	@Override
	@PreAuthorize("@accessManager.hasRole({ 'USER' })")
	public void addToTransferList(int id) {
		playerDao.addToTransferList(id);
	}

	@Override
	@PreAuthorize("@accessManager.hasRole({ 'USER' })")
	public void removeFromTransferList(int id) {
		playerDao.removeFromTransferList(id);
	}

	@Override
	public List<Player> getTransferList() {
		return playerDao.getTransferList();
	}

	@Override
	@Transactional
	public void buyPlayer(int id) {
		playerDao.buyPlayer(id);
	}

}
