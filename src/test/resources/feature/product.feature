Feature: Product functions

  @addProduct @product
  Scenario: As a vendor/admin I should be able to add a product in the supermarket

    Given The user is on home page
    When vendor clicks on Add product
    And enters product name : "coffee" and price : "31.78"
    Then A success message is displayed

  @displayProduct @product
  Scenario: As a user I should be able to see the available list of products on the home page

    Given The user is on home page
    When the page loads
    Then All products should be shown

  @addToCart
  Scenario: As a user I should be able to add products to the cart.

    Given The user is on home page
    And Empty shopping cart and A product - Dove Soap Unit price - 30 exists
    When User adds 5 Dove Soaps in the shopping cart
    And clicks on the cart icon on nav-bar
    Then The shopping cart contains 5 Dove Soaps of unit price 30 and total price is 150
