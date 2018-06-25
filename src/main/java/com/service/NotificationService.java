package com.service;

import java.util.List;

import com.model.Notification;

public interface NotificationService {

	List<Notification> getNotifications();

	void markItUnRead(Long id);

	void markItRead(Long id);
}
