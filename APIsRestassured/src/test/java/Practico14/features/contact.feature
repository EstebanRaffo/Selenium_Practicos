Feature: Create a Contact

  Scenario: Create a new contact
    Given I got the access token and instance url
    And I got a new contact
    When I send a request to create a contact
    Then a contact has been created
    And the contact last name is the same as the contact sent