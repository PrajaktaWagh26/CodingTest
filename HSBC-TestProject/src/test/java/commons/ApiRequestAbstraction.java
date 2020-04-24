package commons;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiRequestAbstraction {
	protected static RequestSpecification request;
	protected static Response response;
	protected static JsonPath jsonPath;

	protected RequestSpecification getRequest() {
		request = RestAssured.given();
		request.header("Content-Type", "application/json");
		return request;
	}

	protected Response getResponse(String apiUrl) {
		response = request.get(apiUrl);
		return response;
	}

	protected JsonPath getJsonPath(String apiUrl) {
		jsonPath = response.jsonPath();
		return jsonPath;
	}
}
