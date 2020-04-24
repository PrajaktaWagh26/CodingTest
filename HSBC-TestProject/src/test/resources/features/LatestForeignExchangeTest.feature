Feature: I want to test api of latest foreign exchange reference rates

  @latestForeignExchangeApi
  Scenario Outline: Validate api of latest foreign exchange reference rates
    Given I have the latest foreign exchange api <api>
    When I request the api
    Then I should receive response code <responsecode>

    Examples: 
      | api                                | responsecode |
      | https://api.ratesapi.io/api/latest |          200 |

  @latestForeignExchangeApiForSpecificCurrencies
  Scenario Outline: Validate api of latest foreign exchange reference rates with specific currencies
    Given I have the latest foreign exchange api <api>
    When I request the api
    And I have given specific currency values <symbols>
    Then I should receive response code <responsecode>
    And response for specific currencies <symbols>

    Examples: 
      | api                                | symbols | responsecode |
      | https://api.ratesapi.io/api/latest | USD,GBP |          200 |
      | https://api.ratesapi.io/api/latest | CZK     |          200 |

  @latestForeignExchangeApiWithSpecificBase
  Scenario Outline: Validate api of latest foreign exchange reference rates with specific base currency
    Given I have the latest foreign exchange api <api>
    When I request the api
    And I have given specific base value <base>
    Then I should receive response code <responsecode>
    And currency rates with given base <base>

    Examples: 
      | api                                | base | responsecode |
      | https://api.ratesapi.io/api/latest | USD  |          200 |

  @latestForeignExchangeApiWithSpecificBaseAndSpecificCurrencies
  Scenario Outline: Validate api of latest foreign exchange reference rates with specific base currency and specific currencies
    Given I have the latest foreign exchange api <api>
    When I request the api
    And I have given specific base value <base>
    And I have given specific currency values <symbols>
    Then I should receive response code <responsecode>
    And currency rates with given base <base>
    And response for specific currencies <symbols>

    Examples: 
      | api                                | base | symbols | responsecode |
      | https://api.ratesapi.io/api/latest | USD  | USD,GBP |          200 |
      | https://api.ratesapi.io/api/latest | USD  | CZK     |          200 |

  @latestForeignExchangeApiWithIncompleteUrl
  Scenario Outline: Validate api of latest foreign exchange reference rates for incorrect or invalid api url
    Given I have the latest foreign exchange api <api>
    When I request the api
    Then I should receive response code <responsecode>

    Examples: 
      | api                         | responsecode |
      | https://api.ratesapi.io/api |          400 |
