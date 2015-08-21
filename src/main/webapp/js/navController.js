angular.module('Supermarket')
    .controller('NavController', function(CartDataSharingService) {
        var controller = this;
        controller.cart = CartDataSharingService.getCart();
    });