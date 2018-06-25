create table team (
	id number(15),
	team_name varchar2(10 char),
	country varchar2(255 char),
	team_budget number(15),
	owner varchar2(15 char)
);

create table player(
	id number(15),
	first_name varchar2(10 char),
	last_name varchar2(10 char),
	country varchar2(255 char),
	age number(4),
	market_value number(15),
	present_On_Transfer_List number(1),
	player_type varchar2(15 char),
	owner varchar2(15 char)
);
