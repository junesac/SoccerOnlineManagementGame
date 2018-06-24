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

public class TeamUtility {

	public static Team getTeam() {

		String teamName = generateString();
		Country teamCountry = Country.randomCountry();

		List<Player> players = new ArrayList<>();
		players.addAll(generatePlayer(AppConstants.GOAL_KEEPER, PlayerType.GK));
		players.addAll(generatePlayer(AppConstants.DEFENDERS, PlayerType.DF));
		players.addAll(generatePlayer(AppConstants.MIDDLE_FIELDERS, PlayerType.MF));
		players.addAll(generatePlayer(AppConstants.ATTACKERS, PlayerType.AT));

		return new Team(teamName, teamCountry, players);
	}

	private static List<Player> generatePlayer(int number, PlayerType playerType) {
		List<Player> players = new ArrayList<>();
		for (int i = 1; i <= number; i++) {
			Player player = generatePlayer(playerType);
			players.add(player);
		}
		return players;
	}

	private static Player generatePlayer(PlayerType playerType) {
		String firstName = generateString();
		String lastName = generateString();
		Country country = Country.randomCountry();
		int age = generateAge(18, 41);
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

	public static void main(String[] args) {

		Team team = getTeam();

		int GK = 0;
		int MF = 0;
		int AT = 0;
		int DF = 0;

		for (Player p : team.getPlayers()) {
			// System.out.println(p.getPlayerType());
			if (p.getPlayerType().toString().equals("GK")) {
				GK++;
			} else if (p.getPlayerType().toString().equals("MF")) {
				MF++;
			} else if (p.getPlayerType().toString().equals("AT")) {
				AT++;
			} else if (p.getPlayerType().toString().equals("DF")) {
				DF++;
			}
		}
		System.out.println(GK + " : " + (GK == AppConstants.GOAL_KEEPER));
		System.out.println(MF + " : " + (GK == AppConstants.MIDDLE_FIELDERS));
		System.out.println(AT + " : " + (GK == AppConstants.ATTACKERS));
		System.out.println(DF + " : " + (GK == AppConstants.DEFENDERS));

	}

}
