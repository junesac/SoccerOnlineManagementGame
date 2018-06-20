package com.dao;

import org.springframework.stereotype.Repository;

import com.model.User;

@Repository("userDao")
public interface UserDao {

	User createUser(User user);

	User login(User user);

}
