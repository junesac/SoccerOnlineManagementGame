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
import com.exception.UserException;
import com.helper.TeamUtility;
import com.mapper.UserMapper;
import com.model.Player;
import com.model.Team;
import com.model.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public User createUser(User user) {

		checkUserNameAvailable(user.getUserName());
		Long id = getMaxId("userid", "users1");
		user.setUserId(id + 1);

		jdbcTemplate.update("INSERT INTO users1 (userid, username, password, enabled) VALUES (?, ?, ?, ?)",
				user.getUserId(), user.getUserName(), bCryptPasswordEncoder.encode(user.getPassword()), 1);

		// Only user role will be given to any user
		jdbcTemplate.update("INSERT INTO user_role (userid, rid) VALUES (?, ?)", user.getUserId(), 2);

		System.out.println("User Added!!");

		createTeamForUser(user.getUserName());
		return user;

	}

	private Long getMaxId(String columnName, String tableName) {
		Long id = jdbcTemplate.queryForObject("select max(" + columnName + ") from " + tableName, Long.class);
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

	private void createTeamForUser(String owner) {

		// Let's store Team for the owner

		Long teamId = getMaxId("id", "team");
		Long playerId = getMaxId("id", "player");
		playerId++;

		Team team = TeamUtility.getTeam();

		jdbcTemplate.update("INSERT INTO team (id, team_name, country, team_budget, owner) VALUES (?, ?, ?,  ?, ?)",
				teamId + 1, team.getTeamName(), team.getCountry().toString(), team.getTeamBudget().intValue(), owner);

		List<Player> players = team.getPlayers();
		for (Player player : players) {

			int isTransferList = player.isPresentOnTransferList() ? 1 : 0;

			jdbcTemplate.update(
					"INSERT INTO player (id, first_name, last_name, country, age, market_value, "
							+ "present_on_transfer_list, player_type, owner) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
					playerId++, player.getFirstName(), player.getLastName(), player.getCountry().toString(),
					player.getAge(), player.getMarketValue().intValue(), isTransferList,
					player.getPlayerType().toString(), owner);

		}

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
