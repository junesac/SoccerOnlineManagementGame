 <!DOCTYPE html>
<html ng-app="SoccerManagementApp">
<head>
<meta charset="UTF-8">
<title>Soccer Online Management Game</title>

<link rel="stylesheet" type="text/css" href="resources/css/bootstrap.min.css">
<link rel="stylesheet" href="resources/js/lib/sweetalert.min.css">

<script src="resources/js/lib/angular.min.js"></script>
<script src="resources/js/lib/angular-route.min.js"></script>
<script src="resources/js/lib/angular-resource.min.js"></script>
<script src="resources/js/lib/sweetalert.min.js"></script>

<script type="text/javascript" src="resources/js/app.js"></script>
<script type="text/javascript" src="resources/js/controllers/HomeController.js"></script>
<script type="text/javascript" src="resources/js/controllers/LoginController.js"></script>
<script type="text/javascript" src="resources/js/controllers/LogoutController.js"></script>
<script type="text/javascript" src="resources/js/controllers/RegistrationController.js"></script>
<script type="text/javascript" src="resources/js/controllers/TeamController.js"></script>
<script type="text/javascript" src="resources/js/services.js"></script>

</head>
<body>

	<h1>Soccer Online Management Game</h1>
	<div ng-view></div>

</body>
</html>