'use strict';

var app = angular.module('SoccerManagementApp.CreatePlayer', []);

app.controller('CreatePlayerController', function($scope, $rootScope,
		$location, $routeParams, StaticService, CommonService, TeamService,
		PlayerService, NotificationService) {
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
		PlayerService.createPlayer($scope.player).then(function(response) {
			swal('Player created successfully!!!.');
			console.log(response.data);
			$scope.player = {};
		}, function(error) {
			swal('Unable to create player : ' + error.data.message);
		});
	}

});