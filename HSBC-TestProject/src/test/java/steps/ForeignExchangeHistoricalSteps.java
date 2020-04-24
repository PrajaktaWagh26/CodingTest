package steps;

import commons.ApiRequestAbstraction;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.time.LocalDate;
import java.time.ZoneId;

import org.apache.log4j.Logger;
import org.junit.Assert;

public class ForeignExchangeHistoricalSteps extends ApiRequestAbstraction {
	private static String apiRequest;
	RequestSpecification request;
	Response response;
	JsonPath jsonPath;
	private String error;
	private static final Logger log = Logger.getLogger(ForeignExchangeHistoricalSteps.class.getName());

	@Given("^I have requested the historical exchange rates api (.+)$")
	public void setForeignExchangeApi(String api) {
		apiRequest = api;
	}

	@Then("^I should receive response for given date (.+)$")
	public void validateApiResponse(String date) {
		response = getResponse(apiRequest);
		jsonPath = getJsonPath(apiRequest);
		if (response.getStatusCode() == 200) {
			Assert.assertEquals(date, jsonPath.getString("date"));
		} else {
			error = jsonPath.get("error");
			log.error("Incorrect api url provided : " + apiRequest);
			log.error(error);
		}
	}

	@And("^I have given date from past (.+)$")
	public void setDate(String date) {
		apiRequest = apiRequest + "/" + date;
	}

	@And("^response code (.+)$")
	public void validateResponseCode(int responsecode) {
		Assert.assertEquals(responsecode, response.getStatusCode());
	}

	@And("^I have given date from future (.+)$")
	public void setFutureDate(String date) {
		apiRequest = apiRequest + "/" + date;
	}

	@Then("^I should receive response for current date$")
	public void validateResponseWhenFutureDateIsGiven() {
		response = getResponse(apiRequest);
		jsonPath = getJsonPath(apiRequest);
		Assert.assertEquals(LocalDate.now(ZoneId.of("UTC")).toString(), jsonPath.getString("date"));
	}

}
