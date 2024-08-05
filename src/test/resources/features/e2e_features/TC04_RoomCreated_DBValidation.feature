@e2e
Feature: RoomCreated_DBValidation
  Scenario: RoomCreated_DBValidation
    Given Admin connects to the database
    When admin executes query for the room
    Then validate created room from the resultSet
    And close the connection