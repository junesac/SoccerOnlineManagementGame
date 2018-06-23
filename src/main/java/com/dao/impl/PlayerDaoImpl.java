package com.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dao.PlayerDao;

@Repository
public class PlayerDaoImpl implements PlayerDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void addToTransferList(int id) {
		this.jdbcTemplate.update("update player set PRESENT_ON_TRANSFER_LIST = ? where id = ?", 1, id);
	}

	@Override
	public void removeFromTransferList(int id) {
		this.jdbcTemplate.update("update player set PRESENT_ON_TRANSFER_LIST = ? where id = ?", 0, id);
	}

}
