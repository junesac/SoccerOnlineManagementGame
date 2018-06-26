'use strict';

var app = angular.module('Services.UserService', []);

app.service('UserService', function($http, CommonService) {
	var baseUrlForUser = 'ws/user';

	this.createUser = function(user) {
		return $http.post(baseUrlForUser + '/createUser', user);
	};

	this.login = function(user) {
		var config = CommonService.getConfig();
		return $http.post(baseUrlForUser + '/login', user, config);
	};

	this.getAllUsers = function() {
		var config = CommonService.getConfig();
		return $http.get(baseUrlForUser + '/getAllUsers', config);
	}
	
	this.makeAdmin = function(userId) {
		var config = CommonService.getConfig();
		return $http.put(baseUrlForUser + '/makeAdmin/'+ userId, config);
	}
	
	this.makeUser = function(userId) {
		var config = CommonService.getConfig();
		return $http.put(baseUrlForUser + '/makeUser/' + userId , config);
	}
});
