package com.service;

import java.util.List;

import com.model.Notification;
import com.model.User;

public interface UserService {

	User createUser(User user);

	User login(User user);

	List<Notification> getNotifications();
}
