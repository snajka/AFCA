var afcaApp = angular.module('afcaApp.AuthenticationController', []);

afcaApp.controller('AuthenticationController', function($rootScope, $scope, $http, $location){
	
	var authenticate = function(credentials
//			, callback
			) {

		var headers = credentials ? {authorization : "Basic "
				+ btoa(credentials.username + ":" + credentials.password)} : {};
		
		var username = credentials ? credentials.username : null;
		var password = credentials ? credentials.password : null;

	    $http.post('login',	{headers : headers} ,{params:{'username': username, 'password': password}}
	    		)
	    		.success(function(data, status) {
	    			if (status == "302") { 
	    				$rootScope.authenticated = true;
	    				$location.path("/myClassifieds");
	    				console.log('succes');}
//	    			if (data.name) {
//	    				$rootScope.authenticated = true;
//	    			} else {
//	    				$rootScope.authenticated = false;
//	    				callback && callback();
//	    			}
	    			$rootScope.authenticated = true;
	    		})
	    		.error(function(data, status) {
	    			if (status == "302") { 
	    				$rootScope.authenticated = true;
	    				$location.path("/");	
	    				console.log('error')
	    			} else {
	    				$rootScope.authenticated = false;
	    			}
//	    			callback && callback();
	    		});
	}
	
	authenticate();
	$scope.credentials = {};
		  
	$scope.login = function() {
		authenticate($scope.credentials, function() {
			if ($rootScope.authenticated) {
				$location.path("/myClassifieds");
	    		$scope.error = false;
	    	} else {
	    		$location.path("/login");
	    		$scope.error = true;
	        }
		});
	};
		  
	$scope.logout = function() {
		$http.post('logout', {})
				.success(function() {
					$rootScope.authenticated = false;
					$location.path("/");
				})
				.error(function(data) {
					$rootScope.authenticated = false;
				});
	}
	
});
