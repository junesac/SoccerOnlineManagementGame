package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.model.Player;
import com.service.PlayerService;

@RestController
@RequestMapping("/player")
public class PlayerController {

	@Autowired
	private PlayerService playerService;

	@RequestMapping(value = "/addToTransferList/{id}", method = RequestMethod.PUT)
	public void addToTransferList(@PathVariable("id") int id) {
		playerService.addToTransferList(id);
	}

	@RequestMapping(value = "/removeFromTransferList/{id}", method = RequestMethod.PUT)
	public void removeFromTransferList(@PathVariable("id") int id) {
		playerService.removeFromTransferList(id);
	}

	@RequestMapping(value = "/buyPlayer/{id}", method = RequestMethod.PUT)
	public void buyPlayer(@PathVariable("id") int id) {
		playerService.buyPlayer(id);
	}

	@RequestMapping("/getTransferList")
	public List<Player> getTransferList() {
		return playerService.getTransferList();
	}

	@RequestMapping(value = "/createPlayer", method = RequestMethod.POST)
	public Player createPlayer(@RequestBody Player player) {
		return playerService.createPlayer(player);
	}

}
