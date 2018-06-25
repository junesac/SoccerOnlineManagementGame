'use strict';

var app = angular.module('Services.TeamService', []);

app.service('TeamService', function($http, CommonService) {
	var baseUrlForTeam = 'ws/team';

	this.getTeam = function() {
		var config = CommonService.getConfig();
		return $http.get(baseUrlForTeam + '/getTeam', config);
	}

	this.saveChanges = function(team) {
		var config = CommonService.getConfig();
		return $http.put(baseUrlForTeam + '/saveTeam', team, config);
	}

});
