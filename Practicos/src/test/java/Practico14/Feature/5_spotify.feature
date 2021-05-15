Feature: Spotify

  Scenario: Spotify Title
    Given me encuentro en el sitio de spotify
    When consulto por el titulo de la pagina
    Then se muestra el titulo "Escuchar es todo - Spotify"

  Scenario: Registro Exitoso
    Given me encuentro en el sitio de spotify
    And hago click en Registrame
    And se muestra el formulario con los campos para el registro
    When completo el email "testselenium@testseleninum.com"
    And completo la contraseña "holamundo"
    And completo el alias "selenium"
    And hago click en el boton de registarme
    Then se registra la cuenta
    And se muestra un mensaje de exito

  Scenario: Registro de email ya registrado
    Given me encuentro en el sitio de spotify
    And hago click en Registrame
    And se muestra el formulario con los campos para el registro
    When completo el email "test@test.com"
    And completo la contraseña "holamundo"
    And completo el alias "selenium"
    And hago click en el boton de registarme
    Then se muestra un mensaje de error "Email ya registrado"


