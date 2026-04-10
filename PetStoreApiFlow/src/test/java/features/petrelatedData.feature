Feature: Pet related API activity Pet Status, create, update and deleted

  Scenario: verify Available pet status with petID and pet Name
    Given All Pet status data provided
    When  User call Get request for "Available pet"
    Then "Available pet" shown with status  "200"
    And status body is Ok