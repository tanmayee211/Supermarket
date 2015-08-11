var app = angular.module('Supermarket', []);
app.controller('ProductController', function($http) {
    var controler=this;
    controler.products=[];
    controler.displayItem= function ()
    {
        $http({
            url: 'rest/products',
            method: "GET"
        }).success(function(response) {controler.products = response;});
    };
});