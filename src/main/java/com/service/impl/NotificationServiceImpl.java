package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.NotificationDao;
import com.model.Notification;
import com.service.NotificationService;

@Service
public class NotificationServiceImpl implements NotificationService {

	@Autowired
	private NotificationDao notificationDao;

	@Override
	@PreAuthorize("@accessManager.hasRole({ 'USER', 'ADMIN' })")
	public List<Notification> getNotifications() {
		return notificationDao.getNotifications();
	}

	@Override
	@Transactional
	@PreAuthorize("@accessManager.hasRole({ 'USER', 'ADMIN' })")
	public void markItRead(Long id) {
		notificationDao.markItRead(id);
	}

	@Override
	@Transactional
	@PreAuthorize("@accessManager.hasRole({ 'USER', 'ADMIN' })")
	public void markItUnRead(Long id) {
		notificationDao.markItUnRead(id);
	}
}
