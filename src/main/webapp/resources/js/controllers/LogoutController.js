'use strict';

var app = angular.module('SoccerManagementApp.Logout', []);

app.controller('LogoutController', function($rootScope, CommonService, $scope,
		$location) {
	$scope.logout = function() {
		$rootScope.authenticated = false;
		$rootScope.admin = false;
		$rootScope.user = false;
		$rootScope.owner = '' ;

		CommonService.countries = [];
		CommonService.playerTypes = [];
		CommonService.team = {};
		CommonService.users = [];
		CommonService.auth = '';

		$location.path('/login');
	}
	$scope.logout();
});