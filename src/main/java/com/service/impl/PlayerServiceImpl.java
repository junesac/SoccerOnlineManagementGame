package com.service.impl;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.CommonDao;
import com.dao.NotificationDao;
import com.dao.PlayerDao;
import com.dao.TeamDao;
import com.exception.SoccerManagementException;
import com.helper.AppUtility;
import com.model.Player;
import com.model.Team;
import com.service.PlayerService;

@Service
public class PlayerServiceImpl implements PlayerService {

	@Autowired
	private PlayerDao playerDao;

	@Autowired
	private TeamDao teamDao;

	@Autowired
	private CommonDao commonDao;

	@Autowired
	private NotificationDao notificationDao;

	@Override
	@PreAuthorize("@accessManager.hasRole({ 'USER', 'ADMIN' })")
	public void addToTransferList(int id) {
		playerDao.addToTransferList(id);
	}

	@Override
	@PreAuthorize("@accessManager.hasRole({ 'USER', 'ADMIN' })")
	public void removeFromTransferList(int id) {
		playerDao.removeFromTransferList(id);
	}

	@Override
	@PreAuthorize("@accessManager.hasRole({ 'USER', 'ADMIN' })")
	public List<Player> getTransferList() {
		return playerDao.getTransferList();
	}

	@Override
	@Transactional
	@PreAuthorize("@accessManager.hasRole({ 'USER', 'ADMIN' })")
	public void buyPlayer(int id) {

		String owner = AppUtility.getOwner();

		Player player = playerDao.getPlayersBasedOnId(id);

		// Fetching team to check budget
		Team team = teamDao.getTeam(owner);
		checkTeamBudget(player, team);

		Long notificationId = commonDao.getMaxId("id", "notifications");
		notificationDao.createNotification(notificationId, player);
		teamDao.addPlayerToTeam(player);
		playerDao.buyPlayer(player);
	}

	private void checkTeamBudget(Player player, Team team) {
		if (team.getTeamBudget().intValue() < player.getMarketValue().intValue()) {
			throw new SoccerManagementException("Team budget should be more than player value.");
		}
	}

	@Override
	@Transactional
	@PreAuthorize("@accessManager.hasRole({ 'ADMIN' })")
	public Player createPlayer(Player player) {

		checkNotNull(player.getFirstName());
		checkNotNull(player.getLastName());

		AppUtility.checkMaximumNameLength(player.getFirstName(), player.getLastName());
		AppUtility.checkMinimumNameLength(player.getFirstName(), player.getLastName());
		AppUtility.checkAge(player.getAge());

		playerDao.checkPlayerNameAvailable(player);

		Long playerId = commonDao.getMaxId("id", "player");
		player.setId(playerId + 1);

		/**
		 * Set the owner blank& put the player on transfer list.. As admin
		 * should put any player in the team.
		 */
		player.setOwner("");
		player.setPresentOnTransferList(true);
		return playerDao.createPlayer(player);
	}

}
