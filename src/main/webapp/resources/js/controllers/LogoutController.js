'use strict';

var app = angular.module('SoccerManagementApp.Logout', []);

app.controller('LogoutController', function($rootScope, CommonService, $scope,
		$location) {
	$scope.logout = function() {
		$rootScope.authenticated = false;
		CommonService.auth = '';
		$rootScope.admin = false;
		$rootScope.user = false;
		$location.path('/login');
	}
	$scope.logout();
});