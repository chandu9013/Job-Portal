app
		.config(
				function($httpProvider) {
					$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
				}).controller('LoginController',
				[ '$scope', '$http', '$location', 'sharedProperties', 'logout',

				function($scope, $http, $location, sharedProperties, logout) {
					console.log("Hello");
					$scope.bye = "bye world";
					http = $http;
					console.log("Hello");
					$http.get("/user").success(function(data) {
						console.log("success login");
						this.session = sharedProperties.getSession();
						console.log(session.name);
						console.log(data);
						session.name = data.name;
						session.email = data.email;
						session.role = data.roleModel;
						session.authenticated = true;
						sharedProperties.setSession(session);

						// $cookie.put("name", data.name)
						console.log(session.role);

						if (session.role.rid == 1)
							$location.path("/admin");
						else
							console.log("Not admin");
					}).error(function(status, data) {
						console.log("Login failed");
						$scope.user = "N/A";
						$scope.authenticated = false;
						$scope.status = status;
						if (data == 401) {
							logout();
						}

					});
				} ]);
