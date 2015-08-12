var app = angular.module('Supermarket', ['ngRoute']);

app.config(['$routeProvider',
    function($routeProvider) {
        $routeProvider.
            when('/add', {
                templateUrl: 'view/addProduct.html',
                controller: 'ProductController as controller'
            }).
            otherwise({
                redirectTo: '/'
            });
    }]);

app.controller('ProductController', function($http) {
    var controller=this;
    controller.products=[];
    controller.successMessage={};
    controller.showSuccessMessage=false;


    controller.addProduct = function(){
        var product={}
        product.name=controller.name;
        product.price=controller.price;
        $http({
            url: 'rest/product',
            method: "POST",
            data : product
        }).success(function(response)
        {
            controller.showSuccessMessage=true;
            controller.successMessage=response;
            controller.name="";
            controller.price="";
        });
    }


    controller.displayItem= function ()
    {
        $http({
            url: 'rest/products',
            method: "GET"
        }).success(function(response) {
            controller.products = response;
        });
    };
});