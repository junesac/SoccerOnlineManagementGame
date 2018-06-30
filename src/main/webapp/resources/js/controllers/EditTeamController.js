'use strict';

var app = angular.module('SoccerManagementApp.EditTeam', []);

app.controller('EditTeamController', function($scope, $rootScope, $location,
		$routeParams, StaticService, CommonService, TeamService,
		NotificationService) {
	$scope.header = 'Edit Team : ';
	if (!$rootScope.authenticated) {
		$location.path('/login');
	}
	$scope.admin = $rootScope.admin;
	$scope.countries = CommonService.countries;
	$scope.playerTypes = CommonService.playerTypes;
	$scope.teamId = $routeParams.teamId;

	$scope.getTeamById = function() {
		TeamService.getTeamById($scope.teamId).then(function(response) {
			$scope.team = response.data;
			$scope.updateScore();
		}, function(error) {
			swal('Unbale to getTeamById :  ' + error.data.message);
		});
	}
	$scope.getTeamById();

	$scope.updateScore = function() {
		var teamValue = 0;
		$scope.team.players.forEach((p) => { teamValue += parseInt(p.marketValue); });
		$scope.team.teamValue = teamValue;
	}
	
	$scope.saveChanges = function(team) {
		TeamService.saveChanges(team).then(function(response) {
			swal('Changes saved.');
			$scope.updateScore();
			CommonService.updateAllTeams($scope.team);
		}, function(error) {
			swal('Unable to save changes : ' + error.data.message);
		});
	}

});