package com.dao;

import java.util.List;

import com.model.Player;

public interface PlayerDao {

	void addToTransferList(int id);

	void removeFromTransferList(int id);

	List<Player> getTransferList();

	List<Player> getPlayersBasedOnOwner(String owner);

	void updatePlayers(List<Player> players);

	void buyPlayer(Player player);

	Player getPlayersBasedOnId(int id);

	Player createPlayer(Player player);

	int checkPlayerNameAvailable(Player player);
}
