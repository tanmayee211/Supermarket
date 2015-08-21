angular.module('Supermarket')
    .controller('CartController', function(CartDataSharingService) {
        var controller = this;
        controller.cart = CartDataSharingService.getCart();
    });