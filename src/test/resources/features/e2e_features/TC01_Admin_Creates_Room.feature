@ui @e2e
Feature: Admin_creates_room

  Background:
  Given Admin goes to "https://www.medunna.com/" homepage
  And Admin clicks on userIcon
  And Admin clicks on sign in option
  And Admin enters username in Username input field
  And Admin enters password in password input field
  And Admin clicks on Sign In button


  Scenario Outline: Admin_creates_room
    When Admin clicks on Items&Titles button
    And clicks on room option from the dropdown
    And clicks on Create a New Room button
    And enters room number in the Room Number input field
    And chooses "SUITE" from the dropdown
    And clicks on status box
    And enters the "<price>" in Price input field
    And enters a "<description>" in description field
    And clicks on Save button
    Then verify that room is created

    Examples:
      | price | description |
      | 1250 | SeaView |





