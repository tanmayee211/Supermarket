Feature: Opening The Web Page

  Scenario: See if the correct page opens upon hitting the URL
  Given I write the URL in the browser
  When I hit "enter"
  Then I should go to the "supermarket website"