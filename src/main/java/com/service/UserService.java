package com.service;

import com.model.User;

public interface UserService {

	User createUser(User user);

	User login(User user);

}
