package com.service.impl;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.UserDao;
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
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Set<String> roles = auth.getAuthorities().stream().map(r -> r.getAuthority()).collect(Collectors.toSet());
		user.setRoles(roles);
		return user;
	}

}
