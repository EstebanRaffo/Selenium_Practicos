Feature: Airbnb

  Scenario Outline: Escenario 1
    Given estoy en la pagina de Airbnb
    When informo lugar <lugar>
    And informo checkin
    And informo checkout
    And informo huespedes 2 adultos
    And click en Buscar
    Then se muestra el resultado de lugares donde alojarse en <lugar>

    Examples:
      | lugar |
      | Rosario, Santa Fe |
      | Villa Carlos Paz, Cordoba |
      | Villa La Angostura, Neuquén |


  Scenario: Escenario 2
    Given estoy en la pagina de Airbnb
    And selecciono experiencias
    When informo lugar "Montevideo"
    And informo fecha
    And click en Buscar
    Then se muestra el resultado de experiencias


  Scenario: Escenario 3
    Given estoy en la pagina de Airbnb
    Then se muestran lugares cercanos