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
		return {headers:  {
	        'Authorization': this.auth,
	        'Accept': 'application/json;odata=verbose',
	        "X-Testing" : "testing"
	    	}
		};
	}
	
});

app.service('PlayerService', function($http, CommonService) {
	var baseUrlForPlayer = 'ws/player';
	
	this.addToTransferList = function(id) {
		var config = CommonService.getConfig();
		return $http.put(baseUrlForPlayer + '/addToTransferList/' + id, config);
	}
	
	this.removeFromTransferList = function(id) {
		var config = CommonService.getConfig();
		return $http.put(baseUrlForPlayer + '/removeFromTransferList/' + id, config);
	}
});

app.service('UserService', function($http) {
	var baseUrlForUser = 'ws/user';
	
	this.getAllUsers = function() {
		return $http.get(baseUrlForUser + '/getAllUsers');
	};
	
	this.getUserById = function(id) {
		return $http.get(baseUrlForUser + '/getUserByUserId/' + id);
	};
	
	this.updateUser = function(id, user) {
		return $http.put(baseUrlForUser + '/updateUser/' + id, user);
	};
	
	this.createUser = function(user) {
		return $http.post(baseUrlForUser + '/createUser', user);
	};
	
	this.deleteUser = function(userId) {
		return $http.delete(baseUrlForUser + '/deleteUser/' + userId);
	};
	
	this.login = function(user) {
		return $http.post(baseUrlForUser + '/login', user);
	};
});


app.service('TeamService', function($http, CommonService) {
	var baseUrlForTeam = 'ws/team';
	
	this.getTeam = function() {
		var config = CommonService.getConfig();
		return $http.get(baseUrlForTeam + '/getTeam', config);
	}
	
});