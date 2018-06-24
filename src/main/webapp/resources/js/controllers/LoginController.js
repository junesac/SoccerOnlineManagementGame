'use strict';

var app = angular.module('SoccerManagementApp.Login', []);

app.controller('LoginController', function($rootScope, $scope, $location, CommonService, 
		UserService) {
	$scope.header = 'Please login';
	$scope.login = function() {
		CommonService.auth = btoa($scope.user.userName + ":"
				+ $scope.user.password);
		UserService.login($scope.user).then(
				function(response) {
					if (response.data.userName != null) {
						$rootScope.authenticated = true;
						$rootScope.owner = response.data.userName;
						$rootScope.admin = response.data.roles
								.indexOf("ROLE_ADMIN") > -1;
						$rootScope.user = response.data.roles
								.indexOf("ROLE_USER") > -1;
						$location.path('/home');
					} else {
						$scope.handleError();
					}
				}, function(error) {
					$scope.error = true;
					$rootScope.authenticated = false;
					CommonService.auth = '';
					$rootScope.admin = false;
					$rootScope.user = false;
					$location.path('/');
				});
	};
});
