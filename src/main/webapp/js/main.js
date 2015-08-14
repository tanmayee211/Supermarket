var app = angular.module('Supermarket', ['ngRoute']);

app.config(['$routeProvider',
    function($routeProvider) {
        $routeProvider.
            when('/add', {
                templateUrl: 'view/addProduct.html',
                controller: 'AddProductController as controller'
            }).
            when('/', {
                templateUrl: 'view/home.html',
                controller: 'HomeController as controller'
            }).
            /*when('/', {
                templateUrl: 'view/viewCart.html',
                controller: 'ViewCartController as controller'
            }).*/
            otherwise({
                redirectTo: '/'
            });
    }]);






