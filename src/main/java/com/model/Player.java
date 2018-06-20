package com.model;

import java.math.BigInteger;

public class Player {

	private Long id;

	private String firstName;
	private String lastName;
	private Country country;
	private int age;
	private BigInteger marketValue;
	private boolean presentOnTransferList;
	private PlayerType playerType;
	private Team team;

	public Player() {

	}

	public Player(String firstName, String lastName, Country country, int age, PlayerType playerType) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.country = country;
		this.age = age;
		marketValue = new BigInteger("1000");
		presentOnTransferList = false;
		this.playerType = playerType;
	}

	@Override
	public String toString() {
		return "Player [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", country=" + country
				+ ", age=" + age + ", marketValue=" + marketValue + ", presentOnTransferList=" + presentOnTransferList
				+ ", playerType=" + playerType + ", team=" + team + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public BigInteger getMarketValue() {
		return marketValue;
	}

	public void setMarketValue(BigInteger marketValue) {
		this.marketValue = marketValue;
	}

	public boolean isPresentOnTransferList() {
		return presentOnTransferList;
	}

	public void setPresentOnTransferList(boolean presentOnTransferList) {
		this.presentOnTransferList = presentOnTransferList;
	}

	public PlayerType getPlayerType() {
		return playerType;
	}

	public void setPlayerType(PlayerType playerType) {
		this.playerType = playerType;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

}
