'use strict';

var app = angular.module('SoccerManagementApp.Home', []);

app.controller('HomeController', function($scope, $rootScope, $location) {
	$scope.header = 'Welcome to Home Page';
	if (!$rootScope.authenticated) {
		$location.path('/login');
	}
});