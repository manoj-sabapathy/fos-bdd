Feature: Work-order API Happy Path

  @regression
  Scenario: Validate status code for GET /work-order endpoint
    Given the client has a valid OAuth token generated using client credentials
    When the client sends a GET request to "api/work-order"
    Then the response status code should be 200
    And the work-order API and db total count should match

  @regression
  Scenario: Validate status code for GET/work-order/id endpoint
    Given the client has a valid OAuth token generated using client credentials
    When the client sends a GET request to "/api/member/6"
    Then the response status code should be 200


