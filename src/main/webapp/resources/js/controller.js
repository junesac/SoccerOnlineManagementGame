'use strict';

var app = angular.module('controllers', []);

app.controller('LogoutController', function($rootScope, CommonService, $scope, $location) {
	$scope.logout = function() {
		$rootScope.authenticated = false;
		CommonService.auth = '';
		$rootScope.admin = false;
		$rootScope.user = false;
		$location.path('/login');
	}
	$scope.logout();
});

app.controller('LoginController', function($rootScope, $scope, $location, CommonService, 
		UserService) {
	$scope.header = 'Please login';
	$scope.login = function() {
		UserService.login($scope.user).then(
				function(response) {
					if (response.data.userName != null) {
						$rootScope.authenticated = true;
						CommonService.auth = btoa($scope.user.userName + ":"
								+ $scope.user.password);
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

app
		.controller(
				'RegistrationController',
				function($scope, $location, RoleService, UserService) {
					$scope.header = 'Registration  form';

					$scope.register = function() {
						console.log('user to register : ' + $scope.user);

						if ($scope.user.userName == null
								|| $scope.user.userName === '') {
							swal('Please enter The username name.');
						} else if ($scope.user.password == null
								|| $scope.user.password === '') {
							swal('Please enter password.');
						} else if ($scope.user.password.length < 6) {
							swal('Password should be atleast 6 character long.');
						} else {
							UserService.createUser($scope.user).then(
									function(response) {
										swal('User Created Successfully!!!');
										$location.path('/login');
									}, function(error) {
										swal('Exception: user already exist.');
									});
						}
					}
				});

app.controller('HomeController', function($scope, $rootScope, $location) {
	$scope.header = 'Welcome to Home Page';
	if (!$rootScope.authenticated) {
		$location.path('/login');
	}
});

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
