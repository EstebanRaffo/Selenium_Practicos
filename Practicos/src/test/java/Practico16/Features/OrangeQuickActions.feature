Feature: Organe Quck Actions Tests

  Scenario Outline:
    Given estoy en la pagina de Orange
    When me logeo
    And entro en la landing page
    Then veo la <opcion> de quick actions

    Examples:
      | opcion         |
      | Assign Leave   |
