'use strict';

var app = angular.module('SoccerManagementApp.CreatePlayer', []);

app.controller('CreatePlayerController', function($scope, $rootScope, $location,
		$routeParams, StaticService, CommonService, TeamService,
		NotificationService) {
	$scope.header = 'Create Player ';
	if (!$rootScope.authenticated) {
		$location.path('/login');
	}
	$scope.admin = $rootScope.admin;
	$scope.countries = CommonService.countries;
	$scope.playerTypes = CommonService.playerTypes;
	$scope.allTeams = CommonService.allTeams;
	
	$scope.createPlayer = function() {
		console.log($scope.player);
		PlayerService.createPlayer($scope.player)
	}
	
});