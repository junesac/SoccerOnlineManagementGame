package com.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dao.NotificationDao;
import com.helper.AppUtility;
import com.model.Notification;

@Repository
public class NotificationDaoImpl implements NotificationDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Notification> getNotifications() {

		String owner = AppUtility.getOwner();

		String query = "select ID, previousowner, NEWOWNER, SEEN, player_name from notifications where previousowner = ? and seen = ?";
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(query, new Object[] { owner, 0 });

		return notificationMapper(rows);

	}

	private List<Notification> notificationMapper(List<Map<String, Object>> rows) {
		List<Notification> notifications = new ArrayList<>();
		for (Map<String, Object> row : rows) {
			Notification notification = new Notification();
			notification.setId(((BigDecimal) row.get("id")).longValue());
			notification.setPreviousOwner((String) row.get("previousowner"));
			notification.setNewOwner((String) row.get("newOwner"));
			notification.setPlayerName((String) row.get("player_Name"));
			notification.setSeen(((BigDecimal) row.get("seen")).intValue() == 1 ? true : false);
			notifications.add(notification);
		}
		return notifications;
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
