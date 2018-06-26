'use strict';

var app = angular.module('Services.CommonService', []);

app.service('CommonService', function($rootScope) {

	this.auth = '';
	this.getConfig = function() {
		return {
			headers : {
				'Authorization' : 'Basic ' + this.auth,
				'Accept' : 'application/json;odata=verbose',
				"X-Testing" : "testing"
			}
		};
	}

	this.countries = [];
	this.playerTypes = [];
	this.team = {};
	this.users = [];
});
