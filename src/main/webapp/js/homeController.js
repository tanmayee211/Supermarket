angular.module('Supermarket')
.controller('HomeController', function($http) {
    var controller = this;
    $http({
        url: 'rest/products',
        method: "GET"
    }).success(function(response) {
        controller.products = response;
    });
});
