package com.dao;

import java.util.List;

import com.model.Notification;
import com.model.Player;

public interface NotificationDao {

	List<Notification> getNotifications();

	void markItRead(Long id);

	void markItUnRead(Long id);

	void createNotification(Long notificationId, Player player);

}
