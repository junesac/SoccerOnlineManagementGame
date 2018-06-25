package com.dao;

import java.util.List;

import com.model.Notification;

public interface NotificationDao {

	List<Notification> getNotifications();

	void markItRead(Long id);

	void markItUnRead(Long id);

}
