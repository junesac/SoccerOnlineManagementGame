package com.dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.dao.UserDao;
import com.mapper.UserMapper;
import com.model.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public User createUser(User user) {

		jdbcTemplate.update("INSERT INTO users1 (userid, username, password, enabled) VALUES (?, ?, ?, ?)",
				user.getUserId(), user.getUserName(), bCryptPasswordEncoder.encode(user.getPassword()), 1);

		// Only user role will be given to any user
		jdbcTemplate.update("INSERT INTO user_role (userid, rid) VALUES (?, ?)", user.getUserId(), 2);

		return user;

	}

	@Override
	public int checkUserNameAvailable(String userName) {

		return jdbcTemplate.queryForObject("SELECT count(1) FROM users1 where lower(userName) = ? ",
				new Object[] { userName.toLowerCase() }, Integer.class);

	}

	@Override
	public List<User> getAllUsers() {
		String query = "select userid, username from users1";

		List<User> users = jdbcTemplate.query(query, new UserMapper());

		for (User user : users) {
			Set<String> roles = getRoles(user.getUserId());
			user.setRoles(roles);
		}

		return users;
	}

	private Set<String> getRoles(Long userId) {
		String query = "select rolename from user_role ur, role r where ur.rid=r.rid and userid=?";
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(query, userId);

		Set<String> roles = new HashSet<>();
		for (Map<String, Object> row : rows) {
			roles.add((String) row.get("rolename"));
		}

		return roles;
	}

	@Override
	public void makeAdmin(Long userId) {
		jdbcTemplate.update("INSERT INTO user_role (userid, rid) VALUES (?, ?)", userId, 1);
	}

	@Override
	public void makeUser(Long userId) {
		jdbcTemplate.update("delete from user_role where userid = ?  and rid = ?", userId, 1);
	}

}
