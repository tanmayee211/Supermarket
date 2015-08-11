Feature: Add product

  @addProduct
  Scenario: As a vendor/admin I should be able to add a product in the supermarket

    Given I open the website
    When vendor clicks on Add product
    And enters product name : "coffee" and price : "31.78"
    Then A success message is displayed