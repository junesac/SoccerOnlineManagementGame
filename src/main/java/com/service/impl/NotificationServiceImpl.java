package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.NotificationDao;
import com.model.Notification;
import com.service.NotificationService;

@Service
public class NotificationServiceImpl implements NotificationService {

	@Autowired
	private NotificationDao notificationDao;

	@Override
	public List<Notification> getNotifications() {
		return notificationDao.getNotifications();
	}

	@Override
	public void markItRead(Long id) {
		notificationDao.markItRead(id);
	}

	@Override
	public void markItUnRead(Long id) {
		notificationDao.markItUnRead(id);
	}
}
