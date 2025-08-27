Feature: OAuth Token Validation

  @regression 
  Scenario: Validate OAuth token
  Given the client has a valid OAuth token generated using client credentials
  Then the response status code should be 200
        

