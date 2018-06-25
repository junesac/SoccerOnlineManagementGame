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
	
	this.getNotifications = function(user) {
		var config = CommonService.getConfig();
		return $http.post(baseUrlForUser + '/notifications', user, config);
	};
});

