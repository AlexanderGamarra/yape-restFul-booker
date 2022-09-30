# language: en
@AuthFeature
Feature: Auth Category - Token

  @RegressionTest
  Scenario: Consultar el token de un usuario existente
    Given  Existe un usuario registrado en el sistema
    When   Consulto el acceso para el usuario: "admin" y contraseña "password123"
    Then   Verifico que el status de la API sea 200
      And  Verifico que se muestre el token en el body response

  @RegressionTest
  Scenario: Consultar el token de un usuario inexistente
    Given  El usuario no esta registrado en el sistema
    When   Consulto el acceso para el usuario: "admin" y contraseña "password123xx"
    Then   Verifico que el status de la API sea 401



