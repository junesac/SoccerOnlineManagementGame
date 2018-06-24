'use strict';

var app = angular.module('SoccerManagementApp.Home', []);

app.controller('HomeController', function($scope, $rootScope, $location,
		StaticService, CommonService) {
	$scope.header = 'Welcome to Home Page';
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

});