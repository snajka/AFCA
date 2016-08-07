var afcaApp = angular.module('afcaApp.CategoriesController', []);

afcaApp.controller('CategoriesController', function($scope, $location, $routeParams, service){
	
	$scope.url = 'api/categories';
	
	$scope.getAll = function() {
		
		service.getAll($scope.url, $scope.search, $scope.page, $scope.itemsPerPage, $scope.property, $scope.direction)  
				.success(function(data, status, headers) {
					$scope.categories = data;
					$scope.hideSpinner = true;
					$scope.totalElements = headers('total-elements');
					$scope.totalPages = headers('total-pages');
				})
				.error(function() {
					$scope.showError = true;
					$scope.hideSpinner = true;
				});
	}
	
	$scope.remove = function(category) {
		$scope.deleteMessage = true;
		$scope.category = category;
		$scope.confirmDelete = false;
		service.remove($scope.url, category.id)
				.success(function() {
					$scope.getAll();
					$scope.categoryDeleted = true;
					$location.path('/categories')
				})
				.error(function() {
					$scope.categoryDeleted = false;
				});
	}
	
	$scope.init = function() {
		$scope.category = {};
		
		if ($routeParams.id) { 
			service.getOne($scope.url, $routeParams.id)
					.success(function(data) {
						$scope.category = data;
					})
					.error(function() {
						
					});
			$scope.editCategory = true;
		}
	}
	
	$scope.save = function() {
		service.save($scope.url, $scope.category)
				.success(function() {
					$location.path('/categories');
				})
				.error(function() {
					
				});
	}
	  
	$scope.currentPage = $scope.page + 1;
	  
	$scope.itemsPerPage = 5;
	
});