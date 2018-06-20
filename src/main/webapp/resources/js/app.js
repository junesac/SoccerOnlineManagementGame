'use strict';
var app = angular.module('SoccerManagementApp', [ 'ngRoute',
		'controllers', 'services' ]);

app.directive('myHeader', function() {
	return {
		restrict : 'E',
		templateUrl : 'resources/pages/header.html'
	};
});

app.config(function($routeProvider) {
	$routeProvider.when('/userActivities', {
		templateUrl : 'resources/pages/userActivities.html',
		controller : 'UserActivitiesController'
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
	}).when('/', {
		templateUrl : 'resources/pages/loginPage.html',
		controller : 'LoginController'
	}).otherwise({
		redirectTo : '/'
	});
});
