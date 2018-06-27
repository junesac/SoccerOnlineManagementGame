package com.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dao.NotificationDao;
import com.helper.AppUtility;
import com.mapper.NotificationMapper;
import com.model.Notification;

@Repository
public class NotificationDaoImpl implements NotificationDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Notification> getNotifications() {
		String owner = AppUtility.getOwner();
		String query = "select ID, previousowner, NEWOWNER, SEEN, player_name from notifications where previousowner = ? and seen = ?";
		return jdbcTemplate.query(query, new Object[] { owner, 0 }, new NotificationMapper());
	}

	@Override
	public void markItRead(Long id) {
		this.jdbcTemplate.update("update notifications set seen = ? where id = ?", 1, id);
	}

	@Override
	public void markItUnRead(Long id) {
		this.jdbcTemplate.update("update notifications set seen = ? where id = ?", 0, id);
	}
}
