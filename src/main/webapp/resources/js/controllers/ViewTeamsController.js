'use strict';

var app = angular.module('SoccerManagementApp.ViewTeams', []);

app.controller('ViewTeamsController', function($scope, $rootScope, $location,
		   UserService) {
	$scope.header = 'User List : ';
	if (!$rootScope.authenticated) {
		$location.path('/login');
	}

	$scope.getAllUsers = function() {
		UserService.getAllUsers().then(function(response) {
			$scope.users = response.data;
		}, function(error) {
			swal('Unable to get users.');
		});
	}
	$scope.getAllUsers();
	
	$scope.markItRead = function(id) {
		UserService.markItRead(userName).then(function(response) {
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