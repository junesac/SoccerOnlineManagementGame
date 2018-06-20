package com.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;

import com.constants.AppConstants;
import com.model.Country;
import com.model.Player;
import com.model.PlayerType;
import com.model.Team;

public class PlayersUtility {

	public static Team getTeam() {

		String teamName = generateString();
		Country teamCountry = Country.randomCountry();

		List<Player> players = new ArrayList<>();
		for (int i = 1; i <= AppConstants.TEAM_SIZE; i++) {
			Player player = generatePlayer();
			players.add(player);
		}

		return new Team(teamName, teamCountry, players);
	}

	private static Player generatePlayer() {
		String firstName = generateString();
		String lastName = generateString();
		Country country = Country.randomCountry();
		int age = generateAge(18, 41);
		PlayerType playerType = PlayerType.randomPlayer();
		return new Player(firstName, lastName, country, age, playerType);
	}

	private static String generateString() {

		// 6 digits , Use letters & no numbers
		String generatedString = RandomStringUtils.random(6, true, false);
		return generatedString;
	}

	private static int generateAge(int low, int high) {
		Random r = new Random();
		return r.nextInt(high - low) + low;
	}

}
