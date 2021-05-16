Feature: Ejercicios

  Scenario: Persona se encuentra con otra
    Given una persona
    When se encuentra con otra
    Then la saluda

  Scenario: Google Search
    Given estoy en google
    When busco "Queso"
    Then se encuentra "Queso" en el titulo

  Scenario: Calculadora Suma
    Given inicio calculadora
    When ingreso 4 y 5
    Then muestro la suma

  Scenario: Calculadora Resta
    Given inicio calculadora
    When ingreso 9 y 5
    Then muestro la resta

  Scenario: Calculadora Producto
    Given inicio calculadora
    When ingreso 7 y 8
    Then muestro el producto

  Scenario: Mensaje de error de confirmación de email
    Given ingreso en registracion de spotify
    When completo email con "testselenium@testseleninum.com"
    And confirmo email con "test@testseleninum.com"
    Then valido el mensaje de error confirmacion de email

  Scenario: Validar Titulo de sitio Spotify
    Given ingreso en Spotify
    When consulto el titulo
    Then valido el titulo

  Scenario: Mensaje de error de email
    Given ingreso en registracion de spotify
    When dejo email vacio
    Then valido mensaje de error de email obligatorio

  Scenario: Mensaje de error de contraseña vacia
    Given ingreso en registracion de spotify
    When completo email con "testselenium@testseleninum.com"
    And confirmo email con "testselenium@testseleninum.com"
    When dejo contraseña vacia
    Then valido mensaje de error de contraseña vacia