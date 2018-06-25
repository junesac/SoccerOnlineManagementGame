package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.model.Notification;
import com.model.User;
import com.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/createUser", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public User createUser(@RequestBody User user) {
		return userService.createUser(user);
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public User login(@RequestBody User user) {
		return userService.login(user);
	}

	@RequestMapping(value = "/notifications")
	public List<Notification> getNotifications() {
		return userService.getNotifications();
	}

}
