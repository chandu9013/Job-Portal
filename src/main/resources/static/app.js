/**
 * 
 */
var app = angular.module("myApp", [ 'ngRoute',
		'angularUtils.directives.dirPagination' ]);
app.config(function($routeProvider) {
	$routeProvider.when('/', {
		controller : "LoginController",
		templateUrl : "views/login.html"
	}).when('/admin', {
		controller : 'AdminHomeController',
		templateUrl : "views/adminhome.html"
	}).when('/employee', {
		controller : 'EmployeeHomeController',
		templateUrl : "views/employeeHome.html"
	}).otherwise({
		redirectTo : '/'
	});
});

app.service('sharedProperties', function() {
	var stringValue = 'test string value';
	var session = {
		name : '',
		email : '',
		picUrl : '',
		role : {

		},
		authenticated : false
	};

	return {
		getString : function() {
			return stringValue;
		},
		setString : function(value) {
			stringValue = value;
		},
		getSession : function() {
			return session;
		},
		setSession : function(value) {
			session = value;
		}
	}
});

app.service('logout', function($http, $location) {

	return logout = function() {
		$http.post('/logout', {}).success(function() {
			self.authenticated = false;
			$location.path("/");
			console.log("logout success");
		}).error(function(data) {
			console.log("Logout failed");
			self.authenticated = false;
		});
	}
});