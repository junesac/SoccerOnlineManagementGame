package com.dao;

import java.util.List;

import com.model.Player;

public interface PlayerDao {

	void addToTransferList(int id);

	void removeFromTransferList(int id);

	List<Player> getTransferList();

	List<Player> getPlayersBasedOnOwner(String owner);
}
