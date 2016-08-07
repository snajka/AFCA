var afcaApp = angular.module('afcaApp.routes', ['ngRoute', 'ngCookies']);

afcaApp.config(['$routeProvider', function($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl : '/static/app/html/partial/home.html'
        })
        .when('/login', {
            templateUrl : '/static/app/html/partial/login.html',
            controller : 'LoginController'
        })
        .when('/register', {
            templateUrl : '/static/app/html/partial/register.html',
            controller : 'RegisterController'
        })
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
}]);

afca.run.$inject = ['$rootScope', '$location', '$cookies', '$http'];
function run($rootScope, $location, $cookies, $http) {
    // keep user logged in after page refresh
    $rootScope.globals = $cookies.get('globals') || {};
    if ($rootScope.globals.currentUser) {
        $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata;
    }

    $rootScope.$on('$locationChangeStart', function (event, next, current) {
        // redirect to login page if not logged in and trying to access a restricted page
        var restrictedPage = $.inArray($location.path(), ['/login', '/register', '/classifieds', '/categories']) === -1;
        var loggedIn = $rootScope.globals.currentUser;
        if (restrictedPage && !loggedIn) {
            $location.path('/login');
        }
    });
}