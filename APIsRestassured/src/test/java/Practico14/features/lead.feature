Feature: Create a Lead

  Scenario: Create a new lead
    Given I got the access token and instance url
    And I got a lead
    When I send a request to create the lead
    Then a lead is created
    And new lead information expected is the same as lead sent

  Scenario: Create a lead with only LastName
    Given I got the access token and instance url
    And I got a lead with last name
    When I send a request to create the lead with lastname
    Then I got an error message

  Scenario: Create a lead without body
    Given I got the access token and instance url
    And I got a lead
    When I send a request to create the lead without body
    Then I got an error message
