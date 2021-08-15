Feature: Linkedin Login

  @success
  Scenario: Linkedin Successfully Login
    Given estoy en la pagina de login de linkedin
    When ingreso mi email y contraseña
    Then entro a mi cuenta

  @error
  Scenario: Linkedin Invalid Email Login
    Given estoy en la pagina de login de linkedin
    When ingreso mi email incorrectamente
    And ingreso mi contraseña correctamente
    Then se despliega un error de login

  @error
  Scenario: Linkedin Invalid Email and Password Login
    Given estoy en la pagina de login de linkedin
    When ingreso mi email incorrectamente
    And ingreso mi contraseña incorrectamente
    Then se despliega un error de login

