package com.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dao.CommonDao;

@Repository
public class CommonDaoImpl implements CommonDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Long getMaxId(String columnName, String tableName) {
		Long id = jdbcTemplate.queryForObject("select max(" + columnName + ") from " + tableName, Long.class);
		if (id == null) {
			id = 1l;
		}
		return id;
	}
}
