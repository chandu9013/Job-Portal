app
		.config(
				function($httpProvider) {
					$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
				}).controller('LoginController',
				[ '$scope', '$http', '$location', 'sharedProperties', 'logout',

				function($scope, $http, $location, sharedProperties, logout) {
					console.log("Hello");
					http = $http;
					$http.get("/user").success(function(data) {
						console.log("success login");
						this.session = sharedProperties.getSession();
						console.log(session.name);
						console.log(data);
						session.name = data.name;
						session.email = data.email;
						session.role = data.roleModel;
						session.authenticated = true;
						session.picUrl = data.picUrl;
						sharedProperties.setSession(session);
						console.log(session.role);

						if (session.role.roleName == 'Admin')
							$location.path("/admin");
						else if (session.role.roleName == 'Employee')
							$location.path("/employee");
						else {
							console.log("No idea what to do");
						}
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
