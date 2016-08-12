var afcaApp = angular.module('afcaApp.routes', ['ngRoute']);

afcaApp.config(['$routeProvider', '$httpProvider', function($routeProvider, $httpProvider) {
    $routeProvider
        .when('/', {
            templateUrl : '/static/app/html/partial/home.html'
        })
        .when('/login', {
            templateUrl : '/static/app/html/partial/login.html',
            controller : 'AuthenticationController'
        })
        /*
        .when('/register', {
            templateUrl : '/static/app/html/partial/register.html',
            controller : 'RegisterController'
        })*/
        .when('/classifieds', {
            templateUrl : '/static/app/html/partial/classifieds.html',
            controller : 'ClassifiedsController'
        })
        .when('/classifieds/add', {
            templateUrl : '/static/app/html/partial/addEditClassifiedAd.html',
            controller : 'ClassifiedsController'
        })
        .when('/classifieds/edit/:id', {
            templateUrl : '/static/app/html/partial/addEditClassifiedAd.html',
            controller : 'ClassifiedsController'
        })
        .when('/myClassifieds', {
            templateUrl : '/static/app/html/partial/myClassifieds.html',
            controller : 'ClassifiedsController'
        })         
        .when('/users', {
            templateUrl : '/static/app/html/partial/users.html',
            controller: 'UsersController'
        })
        .when('/users/add', {
            templateUrl : '/static/app/html/partial/addEditUser.html',
            controller: 'UsersController'
        })
        .when('/users/edit/:id', {
            templateUrl : '/static/app/html/partial/addEditUser.html',
            controller: 'UsersController'
        })
        .when('/categories', {
            templateUrl : '/static/app/html/partial/categories.html',
            controller: 'CategoriesController'
        })
        .when('/categories/add', {
            templateUrl : '/static/app/html/partial/addEditCategory.html',
            controller: 'CategoriesController'
        })
        .when('/categories/edit/:id', {
            templateUrl : '/static/app/html/partial/addEditCategory.html',
            controller: 'CategoriesController'
        })
        .otherwise({
            redirectTo: '/'
        });
    
    $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
    
}]);
