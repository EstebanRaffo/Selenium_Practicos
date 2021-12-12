Feature: Create a case

  Scenario: Create a new case
    Given I got access to Salesforce
    And I got the access token and instance url
    When I send a request to create a case
    Then a case is created
