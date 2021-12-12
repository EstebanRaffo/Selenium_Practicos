Feature: Create a case

  Scenario: Create a new case
    Given I got the access token and instance url
    And I got a new case
    When I send a request to create a case
    Then a case has been created
