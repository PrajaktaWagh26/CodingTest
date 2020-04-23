Feature: I want to test api of latest foreign exchange reference rates

  Scenario Outline: Validate api of latest foreign exchange reference rates
    Given I have requested the foreign exchange api <api>
    When I set header param request content type as <headertype>
    And send <requesttype> request
    Then I should receive response code <responsecode>

    Examples: 
      | api                                | headertype       | requesttype | responsecode |
      | https://api.ratesapi.io/api/latest | application/json | GET         |          200 |
      | http://api.ratesapi.io/api/latest  | application/json | GET         |          200 |
