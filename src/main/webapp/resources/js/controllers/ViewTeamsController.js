'use strict';

var app = angular.module('SoccerManagementApp.ViewTeams', []);

app.controller('ViewTeamsController', function($scope, $rootScope, $location,
		TeamService, CommonService) {
	if (!$rootScope.authenticated) {
		$location.path('/login');
	}
	$scope.header = 'View Teams : ';
	$scope.admin = $rootScope.admin;
	$scope.countries = CommonService.countries;
	
	$scope.getAllTeams = function() {
		TeamService.getAllTeams().then(function(response) {
			CommonService.setAllTeams(response.data); 
			$scope.allTeams = CommonService.allTeams;
		}, function(error) {
			swal('Unable to load Team details.');
		});
	}
	$scope.getAllTeams();

});