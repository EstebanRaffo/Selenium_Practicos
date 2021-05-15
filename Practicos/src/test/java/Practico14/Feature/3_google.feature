Feature: Cheese Google Search

  Scenario: Find Cheese on Google
    Given I am on Google site
    When I search on "Cheese"
    Then I get information about "Cheese"
