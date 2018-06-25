package com.model;

import java.math.BigInteger;
import java.util.List;

public class Team {

	private Long id;
	private String teamName;
	private Country country;
	private BigInteger teamBudget;
	private List<Player> players;
	private String owner;

	public Team() {

	}

	public Team(String teamName, Country country, List<Player> players) {
		this.teamName = teamName;
		this.country = country;
		this.players = players;
		this.teamBudget = new BigInteger("5000");
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	@Override
	public String toString() {
		return "Team [id=" + id + ", teamName=" + teamName + ", country=" + country + ", teamBudget=" + teamBudget
				+ ", players=" + players + ", owner=" + owner + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public BigInteger getTeamBudget() {
		return teamBudget;
	}

	public void setTeamBudget(BigInteger teamBudget) {
		this.teamBudget = teamBudget;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

}
