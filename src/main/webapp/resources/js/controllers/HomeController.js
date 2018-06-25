'use strict';

var app = angular.module('SoccerManagementApp.Home', []);

app.controller('HomeController', function($scope, $rootScope, $location,
		StaticService, CommonService, TeamService, NotificationService) {
	$scope.header = 'Welcome to Home Page.';
	if (!$rootScope.authenticated) {
		$location.path('/login');
	}

	$scope.getAllCountries = function() {
		StaticService.getAllCountries().then(function(response) {
			CommonService.countries = response.data;
			console.log(response.data);
		}, function(error) {
			swal('Unable to load Countries.');
		});
	}
	$scope.getAllCountries();

	$scope.getAllPlayerTypes = function() {
		StaticService.getAllPlayerTypes().then(function(response) {
			CommonService.playerTypes = response.data;
			console.log(response.data);
		}, function(error) {
			swal('Unable to load PlayerTypes.');
		});
	}
	$scope.getAllPlayerTypes();

	$scope.loadTeam = function() {
		TeamService.getTeam().then(function(response) {
			CommonService.team = response.data;
			var teamValue = 0;
			CommonService.team.players.forEach((p) => { teamValue += p.marketValue; });
			CommonService.team.teamValue = teamValue;
		}, function(error) {
			swal('Unable to load Team details.');
		});
	}
	$scope.loadTeam();
	
	$scope.getNotifications = function() {
		NotificationService.getNotifications().then(function(response) {
			$scope.notifications = response.data;
		}, function(error) {
			swal('Unable to get notifications.');
		});
	}
	$scope.getNotifications();

	$scope.markItRead = function(id) {
		NotificationService.markItRead(id).then(function(response) {
			var notification = $scope.notifications.filter(x => x.id === id);
			notification[0].seen = true;
		}, function(error) {
			swal('Unable to mark notification read.');
		});
	}
	
	$scope.markItUnRead = function(id) {
		NotificationService.markItUnRead(id).then(function(response) {
			var notification = $scope.notifications.filter(x => x.id === id);
			notification[0].seen = false;
		}, function(error) {
			swal('Unable to mark notification unread.');
		});
	}
	
});