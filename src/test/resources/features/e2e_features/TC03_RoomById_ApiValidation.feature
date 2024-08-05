@api @e2e
Feature: RoomById_ApiValidation
  Scenario: RoomById_ApiValidation
    Given Admin sends GET request for a room by its id
    Then verify response body of the created room
