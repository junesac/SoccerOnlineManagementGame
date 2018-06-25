package com.model;

public class Notification {

	private long id;
	private String previousOwner;
	private String newOwner;
	private boolean seen;
	private String playerName;

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNewOwner() {
		return newOwner;
	}

	public void setNewOwner(String newOwner) {
		this.newOwner = newOwner;
	}

	public String getPreviousOwner() {
		return previousOwner;
	}

	public void setPreviousOwner(String previousOwner) {
		this.previousOwner = previousOwner;
	}

	public boolean isSeen() {
		return seen;
	}

	public void setSeen(boolean seen) {
		this.seen = seen;
	}

	@Override
	public String toString() {
		return "Notification [id=" + id + ", previousOwner=" + previousOwner + ", newOwner=" + newOwner + ", seen="
				+ seen + ", playerName=" + playerName + "]";
	}

}
