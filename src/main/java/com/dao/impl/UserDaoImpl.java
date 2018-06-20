package com.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.dao.UserDao;
import com.exception.UserException;
import com.model.User;

public class UserDaoImpl implements UserDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public User createUser(User user) {

		checkUserNameAvailable(user.getUserName());
		Long id = getMaxIdForUser();
		user.setUserId(id + 1);

		jdbcTemplate.update("INSERT INTO users1 (userid, username, password, enabled) VALUES (?, ?, ?, ?)",
				user.getUserId(), user.getUserName(), user.getPassword(), 1);

		// Only user role will be given to any user
		jdbcTemplate.update("INSERT INTO user_role (userid, rid) VALUES (?, ?)", user.getUserId(), 2);

		System.out.println("Person Added!!");
		return user;

	}

	private Long getMaxIdForUser() {
		Long id = jdbcTemplate.queryForObject("select max(userid) from users1", Long.class);
		if (id == null) {
			id = 1l;
		}
		return id;
	}

	private void checkUserNameAvailable(String userName) {

		int count = jdbcTemplate.queryForObject("SELECT count(1) FROM users1 u where lower(u.userName) = ? ",
				new Object[] { userName.toLowerCase() }, Integer.class);

		if (count != 0) {
			throw new UserException("User Already exist.");
		}
	}

	@Override
	public User login(User user) {
		User result = jdbcTemplate.queryForObject(
				"SELECT u.userName FROM users1 u where lower(u.userName) = ? and u.password = ?",
				new Object[] { user.getUserName().toLowerCase(), user.getPassword() },
				new BeanPropertyRowMapper<User>(User.class));

		return result;
	}

}
