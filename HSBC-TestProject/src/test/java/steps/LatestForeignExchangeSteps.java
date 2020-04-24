package steps;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Assert;

import commons.ApiRequestAbstraction;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class LatestForeignExchangeSteps extends ApiRequestAbstraction {
	private static String apiRequest;
	private Map<Object, Object> ratesList;
	RequestSpecification request;
	Response response;
	JsonPath jsonPath;
	private Object error;
	private static final Logger log = Logger.getLogger(LatestForeignExchangeSteps.class.getName());

	@Given("^I have the latest foreign exchange api (.+)$")
	public static void setLatestForeignExchangeApi(String api) {
		apiRequest = api;
	}

	@When("^I request the api$")
	public void setRequestHeaderValue() {
		request = getRequest();
	}

	@Then("^I should receive response code (.+)$")
	public void validateResponse(int responsecode) {
		response = getResponse(apiRequest);
		jsonPath = getJsonPath(apiRequest);
		if (response.statusCode() == 200) {
			Assert.assertEquals(responsecode, response.getStatusCode());
			Assert.assertEquals(LocalDate.now(ZoneId.of("UTC")).toString(), jsonPath.get("date"));
		} else {
			Assert.assertEquals(responsecode, response.getStatusCode());
			error = jsonPath.get("error");
			log.error("Incorrect api url provided : " + apiRequest);
			log.error(error);
		}
	}

	@And("^response for specific currencies (.+)$")
	public void validateResponseForSpecificCurrencies(String symbols) {
		jsonPath = getJsonPath(apiRequest);
		ratesList = jsonPath.getMap("rates");
		if (symbols.contains(",")) {
			String[] symbolsArray = symbols.split(",");
			for (String symbol : symbolsArray) {
				Assert.assertTrue(ratesList.containsKey(symbol));
				Assert.assertTrue(Float.parseFloat(ratesList.get(symbol).toString()) > 0);
			}
		} else {
			Assert.assertTrue(ratesList.containsKey(symbols));
			Assert.assertTrue(Float.parseFloat(ratesList.get(symbols).toString()) > 0);
		}
	}

	@And("^I have given specific currency values (.+)$")
	public void setSymbolsValue(String symbols) {
		request.queryParam("symbols", symbols);
	}

	@And("^I have given specific base value (.+)$")
	public void setBaseValue(String base) {
		request.queryParam("base", base);
	}

	@And("^currency rates with given base (.+)$")
	public void validateResponseForSpecificBase(String base) throws Throwable {
		jsonPath = getJsonPath(apiRequest);
		Assert.assertEquals(base, jsonPath.get("base"));

	}

}
