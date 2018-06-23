'use strict';
var app = angular.module('SoccerManagementApp', [ 'ngRoute',
		'SoccerManagementApp.Home', 'SoccerManagementApp.Registration',
		'SoccerManagementApp.Logout', 'SoccerManagementApp.Login',
		'SoccerManagementApp.Team', 'SoccerManagementApp.TransferList',
		'Services' ]);

app.directive('myHeader', function() {
	return {
		restrict : 'E',
		templateUrl : 'resources/pages/header.html'
	};
});

app.config(function($routeProvider) {
	$routeProvider.when('/viewTeam', {
		templateUrl : 'resources/pages/viewTeam.html',
		controller : 'TeamController'
	}).when('/viewTransferList', {
		templateUrl : 'resources/pages/viewTransferList.html',
		controller : 'TransferListController'
	}).when('/updateUserActivities/:userActivityId', {
		templateUrl : 'resources/pages/userActivityUpdate.html',
		controller : 'UserActivityUpdateCtrl'
	}).when('/users', {
		templateUrl : 'resources/pages/userInfo.html',
		controller : 'UserInfoController'
	}).when('/updateUserInfo/:userId', {
		templateUrl : 'resources/pages/userInfoUpdate.html',
		controller : 'UserUpdateControlller'
	}).when('/home', {
		templateUrl : 'resources/pages/homePage.html',
		controller : 'HomeController'
	}).when('/logout', {
		templateUrl : 'resources/pages/homePage.html',
		controller : 'LogoutController'
	}).when('/register', {
		templateUrl : 'resources/pages/registrationPage.html',
		controller : 'RegistrationController'
	}).when('/login', {
		templateUrl : 'resources/pages/loginPage.html',
		controller : 'LoginController'
	}).otherwise({
		redirectTo : '/login'
	});
});
