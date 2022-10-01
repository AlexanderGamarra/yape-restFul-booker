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

  @RegressionTest @SmokeTest
  Scenario: Consultar reserva por id
    Given Las apis se encuentran disponibles
      And   Se obtiene un ID existente
    When  Consultar el detalle de la reserva con el ID obtenido
      Then  Verifico que el status de la API sea 200
      And   Verifico los datos de la reserva

  @RegressionTest
  Scenario: Consultar reserva para un usuario inexistente
    When  Consultar el detalle de la reserva con el ID "99999"
      Then  Verifico que el status de la API sea 404

  @RegressionTest @SmokeTest
  Scenario: El usuario puede crear una reserva exitosamente
    Given Se creará un usuario con la siguiente información
           |{Nombre}  |{Apellido}|{Precio} |{Deposito pagado?}|{Fecha de registro}|{Fecha de salida}|{Adicional} |
           |Alexander |Gamarra   |99       |true              |2022-01-01         |2025-01-01       |yape        |
    When  Cuando se ingresa la información en la api de registro
    Then  Verifico que el status de la API sea 200
    And   Verifico que la reserva se haya creado exitosamente

  @RegressionTest
  Scenario: El usuario no deberia poder crear una reserva con datos de una anterior reserva
    Given Se creará un usuario con la siguiente información
          |{Nombre}  |{Apellido}|{Precio} |{Deposito pagado?}|{Fecha de registro}|{Fecha de salida}|{Adicional} |
          |Alexander |Gamarra   |99       |true              |2022-01-01         |2025-01-01       |yape        |
    When  Cuando se ingresa la información en la api de registro
    Then  Verifico que el status de la API sea 403


  @RegressionTest @SmokeTest
  Scenario: Actualizar información de una reserva
    Given Las apis se encuentran disponibles
        And  Se obtiene el token
        And  Se obtiene un ID existente
    When  Se actualiza la reserva con la siguiente información
      |{Nombre}        |{Apellido}     |{Precio}|{Pagado?}|{Fecha de registro}|{Fecha de salida}|{Adicional} |
      |firstNameUpdated|lastNameUpdated|888     |true     |2019-01-01         |2021-01-01       |test        |
    Then  Verifico que el status de la API sea 200
        And  Verifico que la reserva se haya actualizado exitosamente


  @RegressionTest @SmokeTest
  Scenario: Actualizar solo los nombres de la reserva
    Given Las apis se encuentran disponibles
        And  Se obtiene el token
        And  Se obtiene un ID existente
    When  Se actualiza los nombres de la reserva con la siguiente información
            |{Nombre}           |{Apellido}       |
            |firstNameUpdatedx2 |lastNameUpdatedx2|
    Then  Verifico que el status de la API sea 200

  @RegressionTest @SmokeTest
  Scenario: Eliminar una reserva por ID
    Given Las apis se encuentran disponibles
        And  Se obtiene el token
        And  Se obtiene un ID existente
    When  Se elimina la reserva del ID obtenido
        Then  Verifico que el status de la API sea 201
        And   Verifico que la reserva se haya eliminado exitosamente





