'use strict';

var app = angular.module('services', []);

app.service('RoleService', function($http) {
	var baseUrlForRoles = 'http://localhost:9999/UserActivityTracker/ws/role';
	
	this.getRoles = function() {
		var response = $http.get(baseUrlForRoles + '/getAllRoles');
		return response;
	};
});

app.service('UserInfoService', function($http) {
	var baseUrlForUserInfo = 'http://localhost:9999/UserActivityTracker/ws/userInfo';
	
	this.getAllUsers = function() {
		return $http.get(baseUrlForUserInfo + '/getAllUsers');
	};
	
	this.getUserById = function(id) {
		return $http.get(baseUrlForUserInfo + '/getUserByUserId/' + id);
	};
	
	this.updateUser = function(id, user) {
		return $http.put(baseUrlForUserInfo + '/updateUser/' + id, user);
	};
	
	this.createUser = function(user) {
		return $http.post(baseUrlForUserInfo + '/createUser', user);
	};
	
	this.deleteUser = function(userId) {
		return $http.delete(baseUrlForUserInfo + '/deleteUser/' + userId);
	};
	
	this.login = function(credential) {
		return $http.post(baseUrlForUserInfo + '/login', credential);
	};
});


app.service('UserActivityService', function($http) {
	var baseUrlForUserActivity = 'http://localhost:9999/UserActivityTracker/ws/userActivity';
	
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