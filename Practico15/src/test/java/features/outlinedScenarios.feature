Feature: Practico 15 Test

  @basic
  Scenario Outline: Calculadora
    Given tengo una calculadora
    When ingreso <num1> y <num2>
    Then la suma es <suma>

    Examples:
      | num1 | num2 | suma |
      | 5    | 7    | 12   |
      | 4    | 10   | 14   |
      | 6    | 7    | 13   |

  @basic
  Scenario Outline: Calculadora de Gastos
    Given monto inicial de <monto_inicial>
    When gasto de <gasto>
    Then el saldo restante es <saldo>

    Examples:
      | monto_inicial | gasto | saldo|
      | 100           | 50    | 50   |
      | 200           | 50    | 150  |
      | 300           | 150   | 150  |

  Scenario Outline: Verificar Usernames
    Given estoy logeado en el sitio web
    And estoy en la pagina principal
    When busco el usuario <username>
    Then obtengo el mensaje <respuesta>

    Examples:
    | username |            respuesta           |
    | Marcus   |  No se encuentra en el sistema |
    | Rox123   |  Nombre de usuario invalido    |
    | Matt     |  Nombre de usuario inactivo    |


  Scenario Outline: Validar Quick Access
    Given estoy en el sitio Orange
    When me logueo
    And valido que estoy en la landing page
    Then valido que aparece <quick_action> en Quick Access

    Examples:
    | quick_action |
    | Assign Leave |
    | Leave List   |
    | Leave Calendar |


  Scenario: Validar Leave Request to approve
    Given estoy en el sitio Orange
    When me logueo
    And obtengo la cantidad de leave request to approve
    And ingreso a la seccion de leave request
    Then valido que coincide la cantidad de elementos