Feature: Member API Happy Path
 
  @regression
  Scenario: Validate status code for GET /member endpoint
    Given the client has a valid OAuth token generated using client credentials
    When the client sends a GET request to "/api/member"
    Then the response status code should be 200
    And the API response should match the database response

    @regression
    Scenario: Validate status code for GET/member/id endpoint 
    Given the client has a valid OAuth token generated using client credentials
    When the client sends a GET request to "/api/member/93948263"
    Then the response status code should be 200
    And the API response should match the database record for member ID "93948263"
    
  
  