'use strict';
var app = angular.module('SoccerManagementApp', [ 'ngRoute',
		'SoccerManagementApp.Home', 'SoccerManagementApp.Registration',
		'SoccerManagementApp.Logout', 'SoccerManagementApp.Login',
		'SoccerManagementApp.Team', 'SoccerManagementApp.TransferList',
		'SoccerManagementApp.ViewTeams', 'SoccerManagementApp.ViewUsers',
		'Services.CommonService', 'Services.PlayerService',
		'Services.RoleService', 'Services.StaticService',
		'Services.TeamService', 'Services.UserService',
		'Services.NotificationService' ]);

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
	}).when('/users', {
		templateUrl : 'resources/pages/userInfo.html',
		controller : 'UserInfoController'
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
	}).when('/viewUsers', {
		templateUrl : 'resources/pages/viewUsers.html',
		controller : 'ViewUsersController'
	}).when('/viewTeams', {
		templateUrl : 'resources/pages/viewTeams.html',
		controller : 'ViewTeamsController'
	}).otherwise({
		redirectTo : '/login'
	});
});
