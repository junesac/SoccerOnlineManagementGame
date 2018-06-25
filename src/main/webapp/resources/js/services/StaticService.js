'use strict';

var app = angular.module('Services.StaticService', []);

app.service('StaticService', function($http, CommonService) {
	var baseUrlForStatic = 'ws/static';

	this.getAllCountries = function() {
		return $http.get(baseUrlForStatic + '/getAllCountries');
	}

	this.getAllPlayerTypes = function() {
		return $http.get(baseUrlForStatic + '/getAllPlayerTypes');
	}

});