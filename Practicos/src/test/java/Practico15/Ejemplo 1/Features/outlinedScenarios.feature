Feature: Outlined Scenarios Test

  Scenario Outline: Compras
    Given tengo <valor_inicial> dolares
    When si gasto <valor_compra> dolares
    Then me sobran <valor_final> dolares

    Examples:
      | valor_inicial | valor_compra | valor_final |
      | 10            | 5            | 5           |
      | 20            | 8            | 12          |
      | 120           | 20           | 100         |

  Scenario Outline: Verificar Usernames
    Given que estoy logeado en el sitio web
    And me encuentro en la pagina prinicipal
    When busco el nombre de usuario <username>
    Then recibio un mensaje indicando <mensaje>

    Examples:
      | username   | mensaje                       |
      | Marcus     | No se encuentra en el sistema |
      | Rox123     | Nombre de usuario invalido    |
      | Mariangela | Nombre de usuario inactivo    |

