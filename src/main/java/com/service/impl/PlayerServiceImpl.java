package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.dao.PlayerDao;
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

}