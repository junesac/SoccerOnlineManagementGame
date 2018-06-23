'use strict';

var app = angular.module('SoccerManagementApp.TransferList', []);

app.controller('TransferListController', function($rootScope, CommonService, $scope,
		$location, PlayerService) {
	$scope.header = 'Transfer List';

	if (!$rootScope.authenticated) {
		$location.path('/login');
	}
	$scope.loadTransferList = function() {
		PlayerService.getTransferList().then(function(response) {
			$scope.players = response.data;
			console.log(response.data);
		}, function(error) {
			swal('Unable to load Transfer List.');
		});
	}
	$scope.loadTransferList();

});