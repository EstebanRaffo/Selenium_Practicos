Feature: : Leads in Salesforce

  Scenario: Create a lead Scenario
    Given I got the access token and instance url
    And I got a lead
    When I send a request to create the lead
    Then a lead is created

#  Scenario: Update a lead
#    Given I got the access token and instance url
#    And I got a lead
#    And I send a request to create the lead
#    When I want to update the last name
#    Then the lead is updated
#
#  Scenario: Convert a lead
#    Given I got the access token and instance url
#    And I got a lead
#    And I send a request to create the lead
#    When I want to update the lead rating to Hot
#    Then the lead is converted
#    And a contact is created from the lead
#    And the contact name is the same as the lead contact name
#    And an account is created from the lead
#
#  Scenario Outline: Create a leads
#    Given I got the access token and instance url
#    And I got a lead <lastname> and <company>
#    When I send a request to create the lead
#    Then a lead is created
#    Examples:
#      | lastname | company |
#      | "Rodriguez" | "Microsoft" |
#      | "Gonzalez" | "Apple" |