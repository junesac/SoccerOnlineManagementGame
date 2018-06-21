'use strict';

var app = angular.module('controllers', []);

app.controller('LogoutController', function($rootScope, $scope, $location) {
    $scope.logout = function() {
        $rootScope.authenticated = false;
        $rootScope.roles = [];
        $rootScope.auth = '';
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
                $rootScope.owner = response.data.userName;
                $rootScope.roles = response.data.roles;
                $rootScope.auth = btoa($scope.user.userName + ":" + $scope.user.password);
                $location.path('/home');
            } else {
                $scope.handleError();
            }
        }, function(error) {
            $scope.error = true;
            $rootScope.authenticated = false;
            $rootScope.auth = '';
            $rootScope.roles = [];
            $location.path('/');
        });
    };
});

app.controller('RegistrationController',
        function($scope, $location, RoleService, UserService) {
            $scope.header = 'Registeration  form';
            
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

app.controller('UserActivitiesController', function($scope, $rootScope,
    $location, UserActivityService, UserService) {

	if (!$rootScope.authenticated) {
        $location.path('/login');
    }
	
	UserService.getUserById($rootScope.userId).then(function(response) {
    	$rootScope.userSetting = response.data.userSetting;
    	$rootScope.role = response.data.roleName;
        console.log('response.data' + response.data);
        
        if ($rootScope.role === 'Manager') {
            $scope.header = 'You dont have access to the link';
            $scope.authorized = false;
        } else {
            $scope.authorized = true;
            $scope.header = 'Manage your Activities';

            $scope.remove = function(uaId) {
                UserActivityService.deleteUserActivity(uaId).then(
                    function(response) {
                        swal('Activity deleted Successfully!!!');
                        $scope.loadAllActivities();
                    },
                    function(error) {
                        swal('Unable to delete activity with id : ' + uaId);
                    });
            };

            $scope.loadAllActivities = function() {
                var filter = {};
                UserActivityService.getAllUserActivities($rootScope.userId, filter)
                    .then(function(response) {
                        $scope.userActivities = response.data;
                        $scope.calculateUserSettings();
                    }, function(error) {
                        swal('Unable to load User Activities');
                    });
            }
            $scope.loadAllActivities();

            $scope.calculateUserSettings = function() {
                var today = new Date();
                var dd = today.getDate();
                var mm = today.getMonth() + 1; // January is 0!

                var yyyy = today.getFullYear();
                if (dd < 10) {
                    dd = '0' + dd;
                }
                if (mm < 10) {
                    mm = '0' + mm;
                }
                var today = yyyy + '-' + mm + '-' + dd;
                var sum = 0;
                $scope.userActivities.forEach((activity) => {
                    if (today === activity.startDate) {
                        sum += activity.calories;
                    }
                });
                $scope.actualGoalDone = sum;
            }

            $scope.filterData = function(filter) {

                if (filter.fromDate == '') {
                    filter.fromDate = null;
                }

                if (filter.toDate == '') {
                    filter.toDate = null;
                }

                if (filter.fromTime == '') {
                    filter.fromTime = null;
                }

                if (filter.toTime == '') {
                    filter.toTime = null;
                }

                UserActivityService.getAllUserActivities($rootScope.userId,
                    filter).then(function(response) {
                    $scope.userActivities = response.data;
                    $scope.clearFields();
                }, function(error) {
                    swal('Unable to load User Activities');
                });

            }

            $scope.addUserActivity = function(userActivity) {
                console.log('adding user activity : ' + userActivity.uaName + ' ' +
                    userActivity.calories);

                if (userActivity.uaName == null || userActivity.uaName === '') {
                    swal('Please enter The activity name.');
                } else if (userActivity.calories == null || userActivity.calories === '') {
                    swal('Please enter calories.');
                } else if (userActivity.startDate == null || userActivity.startDate === '') {
                    swal('Please enter the valid date.');
                } else {

                    userActivity.userId = $rootScope.userId;

                    UserActivityService.createUserActivity($rootScope.userId,
                        userActivity).then(function(response) {
                        swal('Acivity Added Successfully!!!');
                        $scope.clearFields();
                        $scope.loadAllActivities();
                    }, function(error) {
                        swal('Unable to load User Activities');	
                    });
                }
            };
            
            $scope.clearFields = function() {
            	var fields = document.querySelectorAll('.edit');
            	fields.forEach((inputField) => { inputField.value = '';});
            }
        }
        
    }, function(error) {
        swal('Unable to load User with id : ' + id);
    });
});

app
    .controller(
        'UserActivityUpdateCtrl',
        function($scope, $routeParams, $location, $rootScope,
            UserActivityService) {

            if (!$rootScope.authenticated) {
                $location.path('/login');
            }

            if ($rootScope.role === 'Manager') {
                $scope.header = 'You dont have access to the link';
                $scope.authorized = false;
            } else {

                $scope.authorized = true;
                var baseUrl = 'http://localhost:9999/UserActivityTracker/ws/userActivity';
                var id = $routeParams.userActivityId;

                UserActivityService
                    .getUserActivitiesByUserId(id)
                    .then(
                        function(response) {
                            $scope.userActivity = response.data;
                        },
                        function(error) {
                            swal('Unable to get User activity wit id : ' +
                                id);
                        });

                console.log('id to update : ' + id);
                $scope.update = function() {
                    UserActivityService
                        .updateUserActivity(id, $scope.userActivity)
                        .then(
                            function(response) {
                                swal('Acivity Updated Successfully!!!');
                                $location
                                    .path('/userActivities');
                            },
                            function(error) {
                                swal('Unable to get User activity wit id : ' +
                                    id);
                            });
                };
            }
        });

/**
 * 
 * User Info started here
 * 
 */

app.controller('UserInfoController', function($scope, $rootScope, $location,
    RoleService, UserService) {

    if (!$rootScope.authenticated) {
        $location.path('/login');
    }

    

	UserService.getUserById($rootScope.userId).then(function(response) {
    	$rootScope.userSetting = response.data.userSetting;
    	$rootScope.role = response.data.roleName;
        console.log('response.data' + response.data);
        
        if ($rootScope.role === 'User') {
            $scope.header = 'You dont have access to the link';
            $scope.authorized = false;
        } else {
            $scope.authorized = true;
            $scope.header = 'Manage Users';

            $scope.loadAllUsers = function() {
                UserService.getAllUsers().then(function(response) {
                    $scope.users = response.data;
                }, function(error) {
                    swal('Unable to load Users');
                });
            };
            $scope.loadAllUsers();

            RoleService.getRoles().then(function(response) {
                $scope.roles = response.data;
            }, function(error) {
                swal('Unable to load roles');
            });

            $scope.remove = function(userId) {
                UserService.deleteUser(userId).then(function(response) {
                    swal('User deleted Successfully!!!');
                    $scope.loadAllUsers();
                }, function(error) {
                    swal('Unable to delete User with id : ' + id);
                });
            };

            $scope.addUser = function(user) {
                console.log('adding user with : ' + $scope.userName + ' ' +
                    $scope.password);

                if (user.userName == null || user.userName === '') {
                    swal('Please enter The username name.');
                } else if (user.password == null || user.password === '') {
                    swal('Please enter password.');
                } else if (user.userSetting == null || user.userSetting === '') {
                    swal('Please enter user setting.');
                } else if (user.rId == null || user.rId === '') {
                    swal('Please Select a Role.');
                } else {
                    UserService.createUser(user).then(function(response) {
                        swal('User Created Successfully!!!');
                        $scope.loadAllUsers();
                        $scope.clearFields();
                    }, function(error) {
                        swal('Exception: user already exist.');
                    });
                }
            }
            
            $scope.clearFields = function() {
            	var fields = document.querySelectorAll('.edit');
            	fields.forEach((inputField) => { inputField.value = '';});
            }
        };
        
	});
    
    
    
});

app.controller('UserUpdateControlller', function($scope, $routeParams,
    $location, $rootScope, UserService, RoleService) {

    if (!$rootScope.authenticated) {
        $location.path('/login');
    }

    if ($rootScope.role === 'User') {
        $scope.header = 'You dont have access to the link';
        $scope.authorized = false;
    } else {
        $scope.authorized = true;
        var id = $routeParams.userId;

        RoleService.getRoles().then(function(response) {
            $scope.roles = response.data;
            console.log('Roles: ' + response.data);
        }, function(error) {
            swal('Unable to load roles');
        });

        UserService.getUserById(id).then(function(response) {
            $scope.user = response.data;
            $scope.selectedRole = $scope.user.rId;
            console.log('response.data' + response.data);
        }, function(error) {
            swal('Unable to load User with id : ' + id);
        });

        $scope.update = function() {
            $scope.user.rId = $scope.selectedRole;
            UserService.updateUser(id, $scope.user).then(
                function(response) {
                    swal('User Updated Successfully!!!');
                    
                    if(id === $rootScope.userId) {
                    	$rootScope.userSetting = $scope.user.userSetting;
                    }
                    
                    $location.path('/users');
                },
                function(error) {
                    swal('Unable to update User with id : ' + id);
                });
        };
    }
});