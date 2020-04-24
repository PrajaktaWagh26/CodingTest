package modules;

import io.restassured.response.Response;

public class JsonResponse {

	private Response response;

	public JsonResponse(Response response) {
		this.response = response;
	}

	public Response getResponse() {
		return response;
	}

}
