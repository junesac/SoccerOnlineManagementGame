package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.model.Notification;
import com.service.NotificationService;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

	@Autowired
	private NotificationService notificationService;

	@RequestMapping(value = "/get")
	public List<Notification> getNotifications() {
		return notificationService.getNotifications();
	}

	@RequestMapping(value = "/markItRead/{id}", method = RequestMethod.PUT)
	public void markItRead(@PathVariable Long id) {
		notificationService.markItRead(id);
	}

	@RequestMapping(value = "/markItUnRead/{id}", method = RequestMethod.PUT)
	public void markItUnRead(@PathVariable Long id) {
		notificationService.markItUnRead(id);
	}

}
