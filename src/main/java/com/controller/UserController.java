package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.model.User;
import com.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	public User createUser(@RequestBody User user) {
		return userService.createUser(user);
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public User login(@RequestBody User user) {
		return userService.login(user);
	}

	@RequestMapping(value = "/getAllUsers")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	@RequestMapping(value = "/makeAdmin/{userId}", method = RequestMethod.PUT)
	public void makeAdmin(@PathVariable("userId") Long userId) {
		userService.makeAdmin(userId);
	}

	@RequestMapping(value = "/makeUser/{userId}", method = RequestMethod.PUT)
	public void makeUser(@PathVariable("userId") Long userId) {
		userService.makeUser(userId);
	}
}
