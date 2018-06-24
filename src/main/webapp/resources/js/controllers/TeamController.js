'use strict';

var app = angular.module('SoccerManagementApp.Team', []);

app.controller('TeamController', function($scope, $rootScope, $location,
		TeamService, PlayerService, CommonService) {

	$scope.header = 'Team Details';

	if (!$rootScope.authenticated) {
		$location.path('/login');
	}
	$scope.loadTeam = function() {
		TeamService.getTeam().then(function(response) {
			$scope.team = response.data;
			console.log(response.data);
		}, function(error) {
			swal('Unable to load Team details.');
		});
	}
	$scope.loadTeam();
	
	$scope.countries = CommonService.countries;
	$scope.playerTypes = CommonService.playerTypes;
	
	$scope.saveChanges = function(team) {
		TeamService.saveChanges(team).then(function(response) {
			$scope.team = response.data;
			console.log(response.data);
		}, function(error) {
			swal('Unable to Save changes.');
		});
	}

	$scope.addToTransferList = function(id) {
		$scope.team.players.find(o => o.id === id).presentOnTransferList = true;
		PlayerService.addToTransferList(id).then(function(response) {
			console.log('updated Player details');
		}, function(error) {
		});
	}
	
	$scope.removeFromTransferList = function(id) {
		$scope.team.players.find(o => o.id === id).presentOnTransferList = false;
		PlayerService.removeFromTransferList(id).then(function(response) {
			console.log('updated Player details');
		}, function(error) {
		});
	}
	
	
	
});
