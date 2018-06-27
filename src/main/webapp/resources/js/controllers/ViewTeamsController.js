'use strict';

var app = angular.module('SoccerManagementApp.ViewTeams', []);

app.controller('ViewTeamsController', function($scope, $rootScope, $location,
		TeamService, CommonService) {
	if (!$rootScope.authenticated) {
		$location.path('/login');
	}
	$scope.header = 'View Teams : ';
	$scope.admin = $rootScope.admin;
	$scope.countries = CommonService.countries;
	$scope.allTeams = CommonService.allTeams;
	$scope.team = CommonService.team;

});