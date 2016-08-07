var afcaApp = angular.module('afcaApp.UsersController', ['ngCookies']);

afcaApp.controller('UsersController', function($scope, $location, $routeParams, $cookies, service){
	
	$scope.url = 'api/users';
	
	$scope.getAll = function() {
		service.getAll($scope.url, $scope.search, $scope.page, $scope.itemsPerPage, $scope.property, $scope.direction)
				.success(function(data, status, headers) {
					$scope.users = data;
					$scope.hideSpinner = true;
					$scope.totalElements = headers('total-elements');
					$scope.totalPages = headers('total-pages');
				})
				.error(function() {
					$scope.hideSpinner = true;
					$scope.showError = true;
				});
	}
	
	$scope.remove = function(id) {
		service.remove($scope.url, id)
				.success(function() {
					$scope.getAll();
				})
				.error(function() {
					alert('Error!');
				});
	}
	
	$scope.init = function() {
		$scope.user = {};
		
		if ($routeParams.id) { 
			service.getOne($scope.url, $routeParams.id)
					.success(function(data) {
						$scope.user = data;
					})
					.error(function() {
						
					});
			
			$scope.editUser = true;
		};
	}
	
	$scope.save = function() {
		service.save($scope.url, $scope.user)
				.success(function() {
					$location.path('/users');
				})
				.error(function() {
					
				});
	}
	
	$scope.currentPage = $scope.page + 1;
	  
	$scope.itemsPerPage = 5;
	
});