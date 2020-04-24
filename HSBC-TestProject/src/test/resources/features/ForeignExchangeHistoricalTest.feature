Feature: I want to test api of foreign exchange reference rates for specific dates in past

  @ForeignExchangeApiForDates
  Scenario Outline: Validate api of foreign exchange reference rates with given dates
    Given I have requested the historical exchange rates api <api>
    And I have given date from past <date>
    When I request the api
    Then I should receive response for given date <date>
    And response code <responsecode>

    Examples: 
      | api                         | date       | responsecode |
      | https://api.ratesapi.io/api | 2010-01-12 |          200 |

  @ForeignExchangeApiForDatesandSpecificCurrencies
  Scenario Outline: Validate api of foreign exchange reference rates with given dates for specific currencies
    Given I have requested the historical exchange rates api <api>
    And I have given date from past <date>
    When I request the api
    And I have given specific currency values <symbols>
    Then I should receive response for given date <date>
    And response code <responsecode>
    And response for specific currencies <symbols>

    Examples: 
      | api                         | date       | symbols | responsecode |
      | https://api.ratesapi.io/api | 2012-01-25 | USD,GBP |          200 |
      | https://api.ratesapi.io/api | 2009-05-13 | CZK     |          200 |

  @ForeignExchangeApiForDateswithSpecificBase
  Scenario Outline: Validate api of foreign exchange reference rates with given dates and specific base currency
    Given I have requested the historical exchange rates api <api>
    And I have given date from past <date>
    When I request the api
    And I have given specific base value <base>
    Then I should receive response for given date <date>
    And response code <responsecode>
    And currency rates with given base <base>

    Examples: 
      | api                         | date       | base | responsecode |
      | https://api.ratesapi.io/api | 2019-12-31 | USD  |          200 |

  @ForeignExchangeApiForDatesWithSpecificBaseAndSpecificCurrencies
  Scenario Outline: Validate api of foreign exchange reference rates with given dates and specific base currency for specific currencies
    Given I have requested the historical exchange rates api <api>
    And I have given date from past <date>
    When I request the api
    And I have given specific base value <base>
    And I have given specific currency values <symbols>
    Then I should receive response for given date <date>
    And response code <responsecode>
    And currency rates with given base <base>
    And response for specific currencies <symbols>

    Examples: 
      | api                         | date       | base | symbols | responsecode |
      | https://api.ratesapi.io/api | 2016-07-13 | USD  | USD,GBP |          200 |
      | https://api.ratesapi.io/api | 1999-10-15 | USD  | CZK     |          200 |

  @ForeignExchangeApiForFutureDate
  Scenario Outline: Validate api of foreign exchange reference rates with given future date
    Given I have requested the historical exchange rates api <api>
    And I have given date from future <date>
    When I request the api
    Then I should receive response for current date
    And response code <responsecode>

    Examples: 
      | api                         | date       | responsecode |
      | https://api.ratesapi.io/api | 2020-05-12 |          200 |
