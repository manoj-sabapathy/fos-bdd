Feature: Provider API Happy Path

  @regression
  Scenario: Validate status code for GET /provider endpoint
    Given the client has a valid OAuth token generated using client credentials
    When the client sends a GET request to "/api/provider"
    Then the response status code should be 200
    And the provider API and db total count should match

  @regression
  Scenario: Validate status code for GET/provider/id endpoint
    Given the client has a valid OAuth token generated using client credentials
    When the client sends a GET request to "/api/provider/42"
    Then the response status code should be 200
    And the API response response should match the database record for provider ID "42"


