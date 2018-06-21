'use strict';

var app = angular.module('controllers', []);

app.controller('LogoutController', function($rootScope, $scope, $location) {
    $scope.logout = function() {
        $rootScope.authenticated = false;
        $rootScope.auth = '';
        $rootScope.admin = false;
        $rootScope.user = false;
        $location.path('/login');
    }
    $scope.logout();
});

app.controller('LoginController', function($rootScope, $scope, $location,
    UserService) {
    $scope.header = 'Please login';
    $scope.login = function() {
        UserService.login($scope.user).then(function(response) {
            if (response.data.userName != null) {
                $rootScope.authenticated = true;
                $rootScope.auth = btoa($scope.user.userName + ":" + $scope.user.password);
                $rootScope.owner = response.data.userName;
                $rootScope.admin = response.data.roles.indexOf("ROLE_ADMIN") > -1;
                $rootScope.user = response.data.roles.indexOf("ROLE_USER") > -1;
                $location.path('/home');
            } else {
                $scope.handleError();
            }
        }, function(error) {
            $scope.error = true;
            $rootScope.authenticated = false;
            $rootScope.auth = '';
            $rootScope.admin = false;
            $rootScope.user = false;
            $location.path('/');
        });
    };
});

app.controller('RegistrationController',
        function($scope, $location, RoleService, UserService) {
            $scope.header = 'Registration  form';
            
            $scope.register = function() {
                console.log('user to register : ' + $scope.user);

                if ($scope.user.userName == null ||
                    $scope.user.userName === '') {
                    swal('Please enter The username name.');
                } else if ($scope.user.password == null ||
                    $scope.user.password === '') {
                    swal('Please enter password.');
                } else if ($scope.user.password.length < 6) {
                    swal('Password should be atleast 6 character long.');
                } else {
                    UserService.createUser($scope.user).then(
                        function(response) {
                            swal('User Created Successfully!!!');
                            $location.path('/login');
                        },
                        function(error) {
                            swal('Exception: user already exist.');
                        });
                }
            }
        });

app.controller('HomeController', function($scope, $rootScope, $location) {
    $scope.header = 'Welcome to Home Page';
    if (!$rootScope.authenticated) {
        $location.path('/login');
    }
});

app.controller('TeamController', function($scope, $rootScope,
    $location, TeamService) {

		$scope.header = 'Team Details';
		
		if (!$rootScope.authenticated) {
		    $location.path('/login');
		}
		
		TeamService.getTeam($rootScope.auth).then(
                function(response) {
                    console.log(response.data);
                },
                function(error) {
                    swal('Unable to load Team details.');
                });

});
