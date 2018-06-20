package com.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum PlayerType {

	GK("Goal Keeper"), DF("Defender"), MF("Midddle Fielder"), AT("Attacker");

	private String name;

	// get methods go here
	private PlayerType(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	private static final List<PlayerType> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
	private static final int SIZE = VALUES.size();
	private static final Random RANDOM = new Random();

	public static PlayerType randomPlayer() {
		return VALUES.get(RANDOM.nextInt(SIZE));
	}

}
