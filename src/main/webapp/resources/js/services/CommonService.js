'use strict';

var app = angular.module('Services.CommonService', []);

app.service('CommonService', function($rootScope) {

	this.getConfig = function() {
		return {
			headers : {
				'Authorization' : 'Basic ' + this.auth,
				'Accept' : 'application/json;odata=verbose',
				"X-Testing" : "testing"
			}
		};
	}

	this.auth = '';
	this.countries = [];
	this.playerTypes = [];
	this.team = {};
	this.users = [];
	this.allTeams = [];

	this.setAllTeams = function(teams) {
		this.allTeams = teams;
	}
	
	this.updateAllTeams = function(team) {
		if(this.allTeams.length > 0) {
			this.allTeams.forEach( t => {
				if(t.id === team.id) {
					for(var i in team) {
						t[i] = team[i];
					}
				}
				
				if(t.id === this.team.id) {
					for(var i in team) {
						this.team[i] = team[i];
					}
				}
			});
		}
	}
	

	this.setTeam = function(team) {
		this.team = team;
		var teamValue = 0;
		this.team.players.forEach((p) => { teamValue += p.marketValue; });
		this.team.teamValue = teamValue;
	}
	
});
