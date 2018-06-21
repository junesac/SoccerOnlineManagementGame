'use strict';

var app = angular.module('services', []);

app.service('RoleService', function($http) {
	var baseUrlForRoles = 'http://localhost:9999/UserActivityTracker/ws/role';
	
	this.getRoles = function() {
		var response = $http.get(baseUrlForRoles + '/getAllRoles');
		return response;
	};
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


app.service('TeamService', function($http) {
	var baseUrlForTeam = 'ws/team';
	
	this.getTeam = function(auth) {
		var config = {headers:  {
	        'Authorization': auth,
	        'Accept': 'application/json;odata=verbose',
	        "X-Testing" : "testing"
	    	}
		};
		return $http.get(baseUrlForTeam + '/getTeam', config);
	}
	
	this.deleteUserActivity = function(uaId) {
		return $http.delete(baseUrlForUserActivity + '/deleteUserActivity/' + uaId)
	};
	
	this.getAllUserActivities = function(userId, filter) {
		return $http.post(baseUrlForUserActivity + '/getUserActivities/' + userId , filter);
	};
	
	this.createUserActivity = function(userId, userActivity) {
		return $http.post(baseUrlForUserActivity + '/createUserActivity/' + userId, userActivity);
	}
	
	this.getUserActivitiesByUserId = function(id) {
		return $http.get(baseUrlForUserActivity + '/getUserActivitiesByUserId/' + id);
	}
	
	this.updateUserActivity = function(id, userActivity) {
		return $http.put(baseUrlForUserActivity + '/updateUserActivity/' + id, userActivity);
	}
});