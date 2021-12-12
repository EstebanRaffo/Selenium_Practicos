Feature: Contacts in Salesforce

  Scenario: Create a new contact
    Given I got the access token and instance url
    And I got a new contact
    When I send a request to create an contact
    Then an contact has been created
    And the contact last name is the same as the contact sent