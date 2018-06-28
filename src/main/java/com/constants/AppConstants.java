package com.constants;

public interface AppConstants {

	int TEAM_SIZE = 20;

	int GOAL_KEEPER = 3;
	int DEFENDERS = 6;
	int MIDDLE_FIELDERS = 6;
	int ATTACKERS = 5;

	String UPDATE_NOTIFICATION = "update notifications set seen = ? where id = ?";
	String GET_PLAYER = "select p.id, first_name, last_name, p.country, age, market_value,"
			+ " present_on_transfer_list, player_type, p.owner, t.team_name as teamname "
			+ "from team t, player p where t.owner=p.owner";

	String UPDATE_PLAYER = "update player set PRESENT_ON_TRANSFER_LIST = ? where id = ?";

	String GET_TEAM = "select t.id as id,t.team_name as teamName, t.country as country, "
			+ "t.TEAM_BUDGET as teamBudget, t.owner as owner from team t";

}
