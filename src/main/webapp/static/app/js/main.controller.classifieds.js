var afcaApp = angular.module('afcaApp.ClassifiedsController', []);

afcaApp.controller('ClassifiedsController', function($scope, $location, $routeParams, service) {
	
	$scope.url = 'api/classifieds';
	
	$scope.getAll = function() {
		
		service.getAll($scope.url, $scope.search, $scope.page, $scope.itemsPerPage, $scope.property, $scope.direction)  
				.success(function(data, status, headers) {
					$scope.ads = data;
					$scope.hideSpinner = true;
					$scope.totalElements = headers('total-elements');
					$scope.totalPages = headers('total-pages');
				})
				.error(function() {
					$scope.showError = true;
					$scope.hideSpinner = true;
				});
	}
	
	$scope.remove = function(ad) {
		$scope.deleteMessage = true;
		$scope.ad = ad;
		$scope.confirmDelete = false;
		service.remove($scope.url, ad.id)
				.success(function() {
					$scope.getAll();
					$scope.adDeleted = true;
					$location.path('/classifieds')
				})
				.error(function() {
					$scope.adDeleted = false;
				});
	}
	
	$scope.init = function() {
		$scope.ad = {};
		
		if ($routeParams.id) { 
			service.getOne($scope.url, $routeParams.id)
					.success(function(data) {
						$scope.ad = data;
					})
					.error(function() {
						
					});
			$scope.editAd = true;
		}
	}
	
	$scope.save = function() {
		$scope.ad.author={"id":1,"email":"email1@example.com","username":"user 1","telephoneNumber":"11111","role":null};
		service.save($scope.url, $scope.ad)
				.success(function() {
					$location.path('/classifieds');
				})
				.error(function() {
					
				});
	}
	
	$scope.getAllCategories = function() {
		
		service.getAll('api/categories', $scope.search, -1, $scope.itemsPerPage, $scope.property, $scope.direction)  // HTTP GET api/activities
				.success(function(data) {
					$scope.allCategories = data;
					$scope.hideSpinner = true;		
				})
				.error(function() {
					$scope.showError = true;
					$scope.hideSpinner = true;
				});

	}	
	  
	$scope.currentPage = $scope.page + 1;
	  
	$scope.itemsPerPage = 5;
	
});