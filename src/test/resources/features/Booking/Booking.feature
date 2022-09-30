# language: en
@AuthFeature
Feature: Booking Category

  @RegressionTest
  Scenario: Consultar todos los id de reservas de la aplicación
    When   El usuario consulta todas las reservas del sistema
      Then  Verifico que el status de la API sea 200
      And   Verifico que no exista un id repetido

  @RegressionTest
  Scenario Outline: Consultar todos los id de reservas por nombres
    When   El usuario consulta todas las reservas del sistema por nombres: "<Nombre>" y "<Apellido>"
      Then  Verifico que el status de la API sea 200
      And   Verifico que no exista un id repetido
    Examples:
      |Nombre  |Apellido |
      |Agustin |Combs    |

  @RegressionTest
  Scenario Outline: Consultar todos los id de reservas por fechas
    When   El usuario consulta todas las reservas del sistema por fechas: "<Fecha de Registro>" y "<Fecha de Salida>"
      Then  Verifico que el status de la API sea 200
        And   Verifico que no exista un id repetido
    Examples:
      |Fecha de Registro  |Fecha de Salida |
      |2014-03-13         |2022-03-13      |

  @RegressionTest @SmokeTest @asa
  Scenario: Consultar reserva por id
    Given Las apis se encuentran disponibles
      And   Se obtiene un ID existente
    When  Consultar el detalle de la reserva con el ID obtenido
      Then  Verifico que el status de la API sea 200
      And   Verifico los datos de la reserva

  @RegressionTest
  Scenario: Consultar reserva para un usuario inexistente
    When  Consultar el detalle de la reserva con el ID 99999
      Then  Verifico que el status de la API sea 404









