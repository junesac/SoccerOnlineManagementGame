package com.service;

import java.util.List;

import com.model.Player;

public interface PlayerService {

	void addToTransferList(int id);

	void removeFromTransferList(int id);

	List<Player> getTransferList();

	void buyPlayer(int id);

	Player createPlayer(Player player);

}
