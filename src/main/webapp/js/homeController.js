angular.module('Supermarket')
.controller('HomeController', function($http,CartDataSharingService) {
    var controller = this;
    $http({
        url: 'rest/products',
        method: "GET"
    }).success(function(response) {
        controller.products = response;
    });

        controller.addToCart=function(product)
        {
            var cartItem={};
            cartItem.name=product.name;
            cartItem.price=product.price;
            cartItem.quantity=product.quantity;
            CartDataSharingService.addToCart(cartItem)
            product.quantity=0;

        };

});
