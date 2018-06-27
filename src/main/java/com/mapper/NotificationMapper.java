package com.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.model.Notification;

public class NotificationMapper implements RowMapper<Notification> {

	@Override
	public Notification mapRow(ResultSet rs, int rowNum) throws SQLException {
		Notification notification = new Notification();
		notification.setId(rs.getBigDecimal("id").longValue());
		notification.setPreviousOwner(rs.getString("previousowner"));
		notification.setNewOwner(rs.getString("newOwner"));
		notification.setPlayerName(rs.getString("player_Name"));
		notification.setSeen(rs.getBigDecimal("seen").intValue() == 1 ? true : false);
		return notification;
	}
}