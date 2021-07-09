Feature: Calculadora

  Scenario: quiero realizar una suma de dos numeros
    Given tengo una calculadora
    When sumo 4 y 6
    Then la suma es 10