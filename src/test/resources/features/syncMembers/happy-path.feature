Feature: Sync members API Happy Path

  @regression
  Scenario: Validate sync members - updating address
    Given the client has a valid OAuth token generated using client credentials for member service
    When the client sends a PUT request to "https://memberservice-qa-lumen.matrixmedical.cloud/v1/api/member/81181571/address/72682112" to update address
      | primary     | true                 |
      | addressType | Physical Address     |
      | dataSource  | Client               |
      | street1     | 150 E TRAVELLERS TRL |
      | street2     |                      |
      | city        | BURNSVILLE           |
      | state       | MN                   |
      | zip         | 55337                |
    Then the response status code should be 200
    And validate street2 column in address table in field operations database for member ID "81181571"
    Given the client has a valid OAuth token generated using client credentials
    When the client sends a POST call with request body as "[81181571]" to "/api/sync/members"
    Then the response status code should be 200
    And validate street2 column in address table in field operations database for member ID "81181571" should matches member service database

  @regression
  Scenario: Validate sync members - updating email address
    Given the client has a valid OAuth token generated using client credentials for member service
    When the client sends a PUT request to "https://memberservice-qa-lumen.matrixmedical.cloud/v1/api/member/79887903/email/8816765" to update email
      | emailType        | Work   |
      | dataSource       | client |
      | emailAddress     |        |
      | primary          | true   |
      | telehealth       | true   |
      | emailGrade       | test   |
      | emailConsentType | true   |

    Then the response status code should be 200
    And validate address column in email table in field operations database for member ID "79887903" should matches member service database

  @regression
  Scenario: Validate sync members - updating phone details
    Given the client has a valid OAuth token generated using client credentials for member service
    When the client sends a PUT request to "https://memberservice-qa-lumen.matrixmedical.cloud/v1/api/member/79887903/phone/106011196" to update phone number
      | number            |        |
      | telephoneType     | Home   |
      | dataSource        | Client |
      | extension         | true   |
      | primary           | true   |
      | consentToCall     | true   |
      | consentToSms      | true   |
      | textable          | true   |
      | clientSuppliedDno | true   |
      | telehealth        | true   |

    Then the response status code should be 200
    And validate dial number column in phone table in field operations database for member ID "79887903" should matches member service database

  @regression
  Scenario: Validate sync members - updating appointment details
    Given the client has a valid OAuth token generated using client credentials for member service
    When the client sends a PUT request to "https://memberservice-qa-lumen.matrixmedical.cloud/v1/api/scheduling/appointment/" to update appointment details
      | appointmentId     |                             |
      | cohabMemberId     | 0                           |
      | activityType      | call - Inbound              |
      | disposition       | canceled                    |
      | dispositionReason | Weather                     |
      | subject           | Record update               |
      | notes             | Cancel Appointment from API |
      | dataSource        | Carenet                     |
    Then the response status code should be 200
    And validate notes column in member appointment table in field operations database requested member ID should matches member service database
