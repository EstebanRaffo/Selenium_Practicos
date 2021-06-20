Feature: Orange Tests

  Scenario Outline:
    Given estoy en la pagina de Orange
    When me logeo
    And entro en la landing page
    Then veo la <opcion> de quick actions

    Examples:
      | opcion         |
      | Assign Leave   |

  Scenario:
    Given estoy en la pagina de Orange
    When me logeo
    And obtengo la cantidad de leave request to approve
    And ingreso a la seccion de leave request
    Then la cantidad de elementos a deplegarse debe coincidir


