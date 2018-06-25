'use strict';

var app = angular.module('Services.RoleService', []);

app.service('RoleService', function($http) {
	var baseUrlForRoles = 'http://localhost:9999/UserActivityTracker/ws/role';

	this.getRoles = function() {
		var response = $http.get(baseUrlForRoles + '/getAllRoles');
		return response;
	};
});