package com.service;

import java.util.List;

import com.model.User;

public interface UserService {

	User createUser(User user);

	User login(User user);

	List<User> getAllUsers();

	void makeAdmin(Long userId);

	void makeUser(Long userId);

}
