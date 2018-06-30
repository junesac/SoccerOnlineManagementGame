package com.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.model.User;

@Repository
public interface UserDao {

	User createUser(User user);

	List<User> getAllUsers();

	void makeAdmin(Long userId);

	void makeUser(Long userId);

	int checkUserNameAvailable(String userName);

}
