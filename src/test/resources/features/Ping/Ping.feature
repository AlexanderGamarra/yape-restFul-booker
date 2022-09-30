# language: en
@PingFeature
Feature: Ping Category

  @RegressionTest
  Scenario: Verificar que los servicios estén desplegados
    When   Consulto la disponibilidad de los servicios
    Then   Verifico que el status de la API sea 201