'use strict';

var app = angular.module('SoccerManagementApp.Team', []);

app.controller('TeamController', function($scope, $rootScope, $location,
		TeamService, PlayerService) {

	$scope.header = 'Team Details';

	if (!$rootScope.authenticated) {
		$location.path('/login');
	}
	$scope.loadAllUsers = function() {
		TeamService.getTeam($rootScope.auth).then(function(response) {
			$scope.team = response.data;
			console.log(response.data);
		}, function(error) {
			swal('Unable to load Team details.');
		});
	}
	$scope.loadAllUsers();

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