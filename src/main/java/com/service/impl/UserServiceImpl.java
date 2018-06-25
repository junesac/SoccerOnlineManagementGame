package com.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.UserDao;
import com.helper.AppUtility;
import com.model.Notification;
import com.model.User;
import com.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	@Transactional
	public User createUser(User user) {
		return userDao.createUser(user);
	}

	@Override
	public User login(User user) {
		Set<String> roles = AppUtility.getRoles();
		user.setRoles(roles);
		return user;
	}

	@Override
	public List<Notification> getNotifications() {
		return userDao.getNotifications();
	}

}
