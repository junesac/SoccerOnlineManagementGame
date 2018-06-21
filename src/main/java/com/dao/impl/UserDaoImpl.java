package com.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.dao.UserDao;
import com.exception.UserException;
import com.helper.PlayersUtility;
import com.model.Player;
import com.model.Team;
import com.model.User;

public class UserDaoImpl implements UserDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public User createUser(User user) {

		checkUserNameAvailable(user.getUserName());
		Long id = getMaxId("userid", "users1");
		user.setUserId(id + 1);

		jdbcTemplate.update("INSERT INTO users1 (userid, username, password, enabled) VALUES (?, ?, ?, ?)",
				user.getUserId(), user.getUserName(), user.getPassword(), 1);

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

		Team team = PlayersUtility.getTeam();

		jdbcTemplate.update(
				"INSERT INTO team (id, team_name, country, team_value, team_budget, owner) VALUES (?, ?, ?, ?, ?, ?)",
				teamId + 1, team.getTeamName(), team.getCountry().getName(), team.getTeamValue().intValue(),
				team.getTeamBudget().intValue(), owner);

		List<Player> players = team.getPlayers();
		for (Player player : players) {

			int isTransferList = player.isPresentOnTransferList() ? 1 : 0;

			jdbcTemplate.update(
					"INSERT INTO player (id, first_name, last_name, country, age, market_value, "
							+ "present_on_transfer_list, player_type, owner) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
					playerId++, player.getFirstName(), player.getLastName(), player.getCountry().getName(),
					player.getAge(), player.getMarketValue().intValue(), isTransferList,
					player.getPlayerType().getName(), owner);

		}

	}

	@Override
	public User login(User user) {
		User result = jdbcTemplate.queryForObject(
				"SELECT USERNAME, ROLENAME as roles FROM USERS1 U, USER_ROLE UR, ROLES R "
						+ "WHERE U.USERID=UR.USERID AND UR.RID = R.RID and lower(u.userName) = ? and u.password = ?",
				new Object[] { user.getUserName().toLowerCase(), user.getPassword() },
				new BeanPropertyRowMapper<User>(User.class));

		return result;
	}

}
