Feature: Products Display

  @displayProducts
  Scenario: As a user I should be able to see a list of products

    Given I open the application
    When The page loads
    Then I should see a list of products