Feature: I want to test api of latest foreign exchange reference rates

  @latestForeignExchangeApi
  Scenario Outline: Validate api of latest foreign exchange reference rates
    Given I have requested the foreign exchange api <api>
    When I set header param request <headerkey>  as <headervalue>
    And send <requesttype> request
    Then I should receive response code <responsecode>

    Examples: 
      | api                                | headerkey    | headervalue      | requesttype | responsecode |
      | https://api.ratesapi.io/api/latest | Content-Type | application/json | GET         |          200 |

  @latestForeignExchangeApiForSpecificCurrencies
  Scenario Outline: Validate api of latest foreign exchange reference rates with specific currencies
    Given I have requested the foreign exchange api <api>
    When I set header param request <headerkey>  as <headervalue>
    And I have given specific currency values <symbols>
    And send <requesttype> request
    Then I should receive response code <responsecode>
    And response for specific currencies <symbols>

    Examples: 
      | api                                | symbols | headerkey    | headervalue      | requesttype | responsecode |
      | https://api.ratesapi.io/api/latest | USD,GBP | Content-Type | application/json | GET         |          200 |
      | https://api.ratesapi.io/api/latest | CZK     | Content-Type | application/json | GET         |          200 |

  @latestForeignExchangeApiWithSpecificBase
  Scenario Outline: Validate api of latest foreign exchange reference rates with specific base currency
    Given I have requested the foreign exchange api <api>
    When I set header param request <headerkey>  as <headervalue>
    And I have given specific base value <base>
    And send <requesttype> request
    Then I should receive response code <responsecode>
    And currency rates with given base <base>

    Examples: 
      | api                                | base | headerkey    | headervalue      | requesttype | responsecode |
      | https://api.ratesapi.io/api/latest | USD  | Content-Type | application/json | GET         |          200 |

  @latestForeignExchangeApiWithSpecificBaseAndSpecificCurrencies
  Scenario Outline: Validate api of latest foreign exchange reference rates with specific base currency and specific currencies
    Given I have requested the foreign exchange api <api>
    When I set header param request <headerkey>  as <headervalue>
    And I have given specific base value <base>
    And I have given specific currency values <symbols>
    And send <requesttype> request
    Then I should receive response code <responsecode>
    And currency rates with given base <base>

    Examples: 
      | api                                | base | symbols | headerkey    | headervalue      | requesttype | responsecode |
      | https://api.ratesapi.io/api/latest | USD  | USD,GBP | Content-Type | application/json | GET         |          200 |
      | https://api.ratesapi.io/api/latest | USD  | CZK     | Content-Type | application/json | GET         |          200 |
