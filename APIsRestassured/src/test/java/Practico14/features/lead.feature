Feature: : Leads in Salesforce

  Scenario: Create a lead
    Given I got the access token and instance url
    And I got a lead
    When I send a request to create the lead
    Then a lead is created