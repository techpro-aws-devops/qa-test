@api @e2e
Feature: Room_Created_Api
  Scenario: Get_All_Rooms
    Given Admin sends GET request for all rooms
    Then verify the room is created

