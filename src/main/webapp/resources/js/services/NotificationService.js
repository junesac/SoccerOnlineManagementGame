'use strict';

var app = angular.module('Services.NotificationService', []);

app.service('NotificationService', function($http, CommonService) {
	var baseUrlForUser = 'ws/notifications';

	this.getNotifications = function(user) {
		var config = CommonService.getConfig();
		return $http.post(baseUrlForUser + '/get', user, config);
	};

	this.markItRead = function(id) {
		var config = CommonService.getConfig();
		return $http.put(baseUrlForUser + '/markItRead/' + id, config);
	};

	this.markItUnRead = function(id) {
		var config = CommonService.getConfig();
		return $http.put(baseUrlForUser + '/markItUnRead/' + id, config);
	};
});
