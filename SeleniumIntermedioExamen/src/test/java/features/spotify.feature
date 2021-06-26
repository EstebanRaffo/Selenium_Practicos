Feature: Spotify

  Scenario Outline: Escenario 1
    Given estoy en la pagina de spotify
    When ingreso en premium
    Then está el plan <plan>

    Examples:
      | plan |
      | Individual |
      | Duo |
      | Familiar |


  Scenario Outline: Escenario 2
    Given estoy en la pagina de spotify
    And ingreso en registrarse
    When ingreso el email <email>
    Then se presenta el mensaje de error <error>

    Examples:
      | email | error |
      |       | Es necesario que introduzcas tu correo electrónico |
      | ffff | Este correo electrónico no es válido. Asegúrate de que tenga un formato como este: ejemplo@email.com |
      | test@test.com | Este correo electrónico ya está conectado a una cuenta. |


  Scenario Outline: Escenario 3
    Given estoy en la pagina de spotify
    When navego a terminos y condiciones
    Then contiene <termino>

    Examples:
      | termino |
      | Disfrutando Spotify |
      | Pagos, cancelaciones y periodo de reflexión |
      | Uso de nuestro servicio |