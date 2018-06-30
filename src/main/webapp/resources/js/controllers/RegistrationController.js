'use strict';

var app = angular.module('SoccerManagementApp.Registration', []);

app.controller('RegistrationController',
				function($scope, $location, UserService) {
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
										swal('Unable to Create user : ' + error.data.message);
										
									});
						}
					}
				});
