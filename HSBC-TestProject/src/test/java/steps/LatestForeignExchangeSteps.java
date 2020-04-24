package steps;

import java.time.LocalDate;
import java.util.Map;

import org.junit.Assert;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import modules.JsonResponse;

public class LatestForeignExchangeSteps {
	private static String apiRequest;
	private RequestSpecification request;
	private JsonResponse jsonResponse;
	private JsonPath jsonPath;
	private Map<Object, Object> ratesList;

	@Given("^I have requested the foreign exchange api (.+)$")
	public static void setLatestForeignExchangeApi(String api) {
		apiRequest = api;
	}

	@When("^I set header param request (.+)  as (.+)$")
	public void setRequestHeaderValue(String headerkey, String headervalue) {
		request = RestAssured.given();
		request.header(headerkey, headervalue);
	}

	@Then("^I should receive response code (.+)$")
	public void validateResponse(int responsecode) {
		Assert.assertEquals(responsecode, jsonResponse.getResponse().getStatusCode());
		jsonPath = jsonResponse.getResponse().jsonPath();
		ratesList = jsonPath.getMap("rates");
		Assert.assertEquals(LocalDate.now().minusDays(1).toString(), jsonPath.get("date"));
	}

	@And("^send (.+) request$")
	public void setRequest(String requesttype) {
		if (requesttype.equalsIgnoreCase("GET")) {
			Response response = request.get(apiRequest);
			jsonResponse = new JsonResponse(response);
		}
	}

	@And("^response for specific currencies (.+)$")
	public void validateResponseForSpecificCurrencies(String symbols) throws Throwable {
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
	public void setSymbolsValue(String symbols) throws Throwable {
		request.queryParam("symbols", symbols);
	}

	@And("^I have given specific base value (.+)$")
	public void setBaseValue(String base) throws Throwable {
		request.queryParam("base", base);
	}

	@And("^currency rates with given base (.+)$")
	public void validateResponseForSpecificBase(String base) throws Throwable {
		Assert.assertEquals(base, jsonPath.get("base"));
	}
}
