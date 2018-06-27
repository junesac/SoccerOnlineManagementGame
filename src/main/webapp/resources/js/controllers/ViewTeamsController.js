'use strict';

var app = angular.module('SoccerManagementApp.ViewTeams', []);

app.controller('ViewTeamsController', function($scope, $rootScope, $location,
		UserService) {
	if (!$rootScope.authenticated) {
		$location.path('/login');
	}
	$scope.header = 'View Teams : ';

});