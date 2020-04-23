package steps;

import org.junit.runner.RunWith;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
public class LatestForeignExchangeTest {
	@Given("^I have requested the foreign exchange api (.+)$")
	public void i_have_requested_the_foreign_exchange_api(String api) {
		String apiUrl = api;
	}

	@When("^I set header param request content type as (.+)$")
	public void i_set_header_param_request_content_type_as(String headertype) throws Throwable {
		throw new PendingException();
	}

	@Then("^I should receive response code (.+)$")
	public void i_should_receive_response_code(String responsecode) throws Throwable {
		throw new PendingException();
	}

	@And("^send (.+) request$")
	public void send_request(String requesttype) throws Throwable {
		throw new PendingException();
	}

}
