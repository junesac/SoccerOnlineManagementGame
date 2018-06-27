'use strict';

var app = angular.module('Services.PlayerService', []);

app
		.service('PlayerService',
				function($http, CommonService) {
					var baseUrlForPlayer = 'ws/player';

					this.addToTransferList = function(id) {
						var config = CommonService.getConfig();
						return $http.put(baseUrlForPlayer
								+ '/addToTransferList/' + id, config);
					}

					this.removeFromTransferList = function(id) {
						var config = CommonService.getConfig();
						return $http.put(baseUrlForPlayer
								+ '/removeFromTransferList/' + id, config);
					}

					this.getTransferList = function() {
						var config = CommonService.getConfig();
						return $http.get(baseUrlForPlayer + '/getTransferList',
								config);
					}

					this.buyPlayer = function(id) {
						var config = CommonService.getConfig();
						return $http.put(baseUrlForPlayer + '/buyPlayer/' + id,
								config);
					}

					this.createPlayer = function(player) {
						var config = CommonService.getConfig();
						return $http.post(baseUrlForPlayer + '/createPlayer',
								player, config);
					}
				});
