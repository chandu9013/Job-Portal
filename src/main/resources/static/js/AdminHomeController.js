/**
 * 
 */
app.controller('AdminHomeController', [ '$scope', 'sharedProperties',
		'$location', '$http',
		function($scope, sharedProperties, $location, $http) {
			console.log("Hello ");
			$scope.session = sharedProperties.getSession();
			if ($scope.session.name == '') {
				$location.path("/");
				console.log("Empty :(");
				return;
			}
			$scope.page = 0;
			$scope.session = sharedProperties.getSession();
			console.log("In admin - " + $scope.session.name);

			$scope.welcome = "Hello Admin";
			var http = $http;
			$scope.showAdd = true;
			
			$scope.listJobs = function() {
				console.log("called listJobs");
				http.get("/jobs").success(function(data) {
					console.log("got jobs  - " + data[0].jId);
					$scope.page = 1;
					$scope.jobs = data;
				}).error(function() {
					console.log("didnt get jobs");
				});
			};
			
			$scope.viewAddJob = function() {
				http.get("/categories").success(function(data) {
					console.log("Got categories ");
					$scope.categories = data;

					http.get("/teams").success(function(data) {
						console.log("Got teams  - ");
						$scope.teams = data;
						$scope.page = 2;

						// Empty the add form
						$scope.categModel = null;
						$scope.teamModel = null;
						$scope.jobDescription = null;
					}).error(function() {
						console.log("didnt get teams");
					});
				}).error(function() {
					console.log("didnt get categories");
				});
			};
			
			$scope.viewUpdateJob = function($index) {
				$scope.page = 3;
				$scope.index = $index;
				console.log("Update $index - " + $index);
				console.log("Update $index - " + $scope.jobs[$index].jId);
				
				http.get("/categories").success(function(data) {
					console.log("Got categories ");
					$scope.categories = data;

					http.get("/teams").success(function(data) {
						console.log("Got teams  - ");
						$scope.teams = data;
						$scope.page = 3;

						// Empty the add form
						$scope.categModel = $scope.jobs[$index].category;
						$scope.teamModel = $scope.jobs[$index].team;
						$scope.jobDescription = $scope.jobs[$index].description;
						$scope.modifyIndex=$index;
					}).error(function() {
						console.log("didnt get teams");
					});
				}).error(function() {
					console.log("didnt get categories");
				});

			}
			
			$scope.viewApplications=function($index){
				var jobId=$scope.jobs[$index].jId;
				console.log("Fetch job applications - "+jobId);
				
				http.get("/jobs/"+jobId+"/applications").success(function(data){
					$scope.applications=data;
					console.log($scope.applications[0].jAppId);
				}).error(function(){
					
				});
			};

			$scope.deleteJob = function($index) {
				// $scope.jobs.
				console.log("Delete $index - " + $index);
				console.log("Delete $index - " + $scope.jobs[$index].jId);
				http.delete("/jobs/"+$scope.jobs[$index].jId).success(function(status){
					console.log("Deleted job");
					// $scope.jobs.splice($index, 1);
					$scope.listJobs();
				}).error(function() {
					console.log("didnt delete job");
				});
			}

			$scope.cancel = function() {
				$scope.page = 1;
				$scope.listJobs();
			}

			$scope.publish = function() {
				var data = {};
				data.categoryId = $scope.categModel.cid;
				data.description = $scope.jobDescription;
				data.teamId = $scope.teamModel.id;
				data.postedBy = 12;

				http.post("/jobs", data).success(function(data) {
					console.log("Job added  - ");
					$scope.listJobs();
				}).error(function() {
					console.log("Adding job failed");
				});

			};
			
			$scope.update = function() {
				var data = {};
				data.categoryId = $scope.categModel.cid;
				data.description = $scope.jobDescription;
				data.teamId = $scope.teamModel.id;
				data.postedBy = 12;

				http.patch("/jobs/"+$scope.jobs[$scope.modifyIndex].jId, data).success(function(data) {
					console.log("Job updated  - ");
					$scope.listJobs();
				}).error(function() {
					console.log("Updating job failed");
				});

			};

			$scope.categModel = null;
			$scope.teamModel = null;
			$scope.jobDescription = null;
		} ]);