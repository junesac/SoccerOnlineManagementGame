package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.dao.UserDao;
import com.model.User;
import com.service.UserService;

public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public User createUser(User user) {
		return userDao.createUser(user);
	}

	@Override
	public User login(User user) {
		return userDao.login(user);
	}

}