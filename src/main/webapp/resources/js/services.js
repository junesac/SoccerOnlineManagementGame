'use strict';

var app = angular.module('Services', []);

app.service('RoleService', function($http) {
	var baseUrlForRoles = 'http://localhost:9999/UserActivityTracker/ws/role';

	this.getRoles = function() {
		var response = $http.get(baseUrlForRoles + '/getAllRoles');
		return response;
	};
});

app.service('CommonService', function($rootScope) {

	this.auth = '';
	this.getConfig = function() {
		return {
			headers : {
				'Authorization' : 'Basic ' + this.auth,
				'Accept' : 'application/json;odata=verbose',
				"X-Testing" : "testing"
			}
		};
	}

	this.countries = [];
	this.playerTypes = [];

});

app
		.service('PlayerService',
				function($http, CommonService) {
					var baseUrlForPlayer = 'ws/player';

					this.addToTransferList = function(id) {
						var config = CommonService.getConfig();
						return $http.put(baseUrlForPlayer
								+ '/addToTransferList/' + id, config);
					}

					this.removeFromTransferList = function(id) {
						var config = CommonService.getConfig();
						return $http.put(baseUrlForPlayer
								+ '/removeFromTransferList/' + id, config);
					}

					this.getTransferList = function() {
						var config = CommonService.getConfig();
						return $http.get(baseUrlForPlayer + '/getTransferList',
								config);
					}
				});

app.service('StaticService', function($http, CommonService) {
	var baseUrlForStatic = 'ws/static';

	this.getAllCountries = function() {
		return $http.get(baseUrlForStatic + '/getAllCountries');
	}

	this.getAllPlayerTypes = function() {
		return $http.get(baseUrlForStatic + '/getAllPlayerTypes');
	}

});

app.service('UserService', function($http, CommonService) {
	var baseUrlForUser = 'ws/user';

	this.createUser = function(user) {
		return $http.post(baseUrlForUser + '/createUser', user);
	};

	this.login = function(user) {
		var config = CommonService.getConfig();
		return $http.post(baseUrlForUser + '/login', user, config);
	};
});

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
