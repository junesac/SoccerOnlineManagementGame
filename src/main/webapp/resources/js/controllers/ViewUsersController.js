'use strict';

var app = angular.module('SoccerManagementApp.ViewUsers', []);

app.controller('ViewUsersController', function($scope, $rootScope, $location,
		UserService, CommonService) {
	
	
	
	if (!$rootScope.authenticated) {
		$location.path('/login');
	}
	$scope.header = 'User List : ';
	$scope.admin = $rootScope.admin;
	$scope.getAllUsers = function() {
		UserService.getAllUsers().then(function(response) {
			CommonService.users = response.data;
			$scope.users = CommonService.users;
		}, function(error) {
			swal('Unable to get users.');
		});
	}
	$scope.getAllUsers();

	$scope.makeAdmin = function(userId) {
		UserService.makeAdmin(userId).then(function(response) {
			console.log('abc', CommonService.users);
			CommonService.users.forEach((u) => {
				if(u.userId === userId && u.roles.indexOf('Admin') == -1) {
					u.roles.push('Admin');
				}
			});
			
		}, function(error) {
			swal('Unable to make admin.');
		});
	}

	$scope.makeUser = function(userId) {
		UserService.makeUser(userId).then(function(response) {
			CommonService.users.forEach((u) => {
				if(u.userId === userId) {
					u.roles.splice( u.roles.indexOf('Admin'), 1 );
				}
			});
		}, function(error) {
			swal('Unable to make admin.');
		});
	}

	$scope.checkIfAdmin = function(roles) {
		return roles.indexOf('Admin') > -1;
	}


});