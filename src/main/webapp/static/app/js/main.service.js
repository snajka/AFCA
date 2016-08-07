var afcaApp = angular.module('afcaApp.Service', []);

afcaApp.service('service', function($http) {
	
//	this.url = 'api/categories';
	
	this.getOne = function(url, id) {
		return $http.get(url + '/' + id);
	}
	
	this.remove = function(url, id) {
		return $http.delete(url + '/' + id);
	}
	
	this.getAll = function(url, search, page, itemsPerPage, property, direction) {
		return $http.get(url, { params: { 'search': search, 'page': page, 'itemsPerPage': itemsPerPage, 'property': property, 'direction': direction }});
	}
	
	this.save = function(url, activity) {
		if (activity.id) {
			return $http.put(url + '/' + activity.id, activity);
		} else {
			return $http.post(url, activity);
		}
	}
});
