package com.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.CommonDao;
import com.dao.PlayerDao;
import com.dao.TeamDao;
import com.dao.UserDao;
import com.exception.SoccerManagementException;
import com.helper.AppUtility;
import com.helper.TeamUtility;
import com.model.Player;
import com.model.Team;
import com.model.User;
import com.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private TeamDao teamDao;

	@Autowired
	private CommonDao commonDao;

	@Autowired
	private PlayerDao playerDao;

	@Override
	@Transactional
	public User createUser(User user) {

		AppUtility.checkMaximumNameLength(user.getUserName());
		AppUtility.checkMinimumNameLength(user.getUserName());

		int count = userDao.checkUserNameAvailable(user.getUserName());
		if (count != 0) {
			throw new SoccerManagementException("User Already exist.");
		}

		Long id = commonDao.getMaxId("userid", "users1");
		user.setUserId(id + 1);
		User result = userDao.createUser(user);

		Team team = createTeam(user);
		createPlayers(user, team);
		return result;
	}

	private void createPlayers(User user, Team team) {
		Long playerId = commonDao.getMaxId("id", "player");
		playerId++;

		List<Player> players = team.getPlayers();
		for (Player player : players) {
			player.setOwner(user.getUserName());
			player.setId(playerId++);
			playerDao.createPlayer(player);
		}
	}

	private Team createTeam(User user) {
		Long teamId = commonDao.getMaxId("id", "team");
		Team team = TeamUtility.getTeam();
		team.setId(teamId + 1);
		team.setOwner(user.getUserName());
		teamDao.createTeam(team);
		return team;
	}

	@Override
	public User login(User user) {
		Set<String> roles = AppUtility.getRoles();
		user.setRoles(roles);
		user.setPassword("");
		return user;
	}

	@Override
	@PreAuthorize("@accessManager.hasRole({ 'ADMIN' })")
	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}

	@Override
	@Transactional
	@PreAuthorize("@accessManager.hasRole({ 'ADMIN' })")
	public void makeAdmin(Long userId) {
		userDao.makeAdmin(userId);
	}

	@Override
	@Transactional
	@PreAuthorize("@accessManager.hasRole({ 'ADMIN' })")
	public void makeUser(Long userId) {
		userDao.makeUser(userId);
	}

}
