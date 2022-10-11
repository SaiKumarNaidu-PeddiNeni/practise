package stepDefinition;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.apache.http.ProtocolException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.DeletePayload;
import resources.ApiResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinition extends Utils {
	static RequestSpecification req;
	static ResponseSpecification res;
	static Response response;
	static String place_id;
	TestDataBuild data = new TestDataBuild();
	

	@Given("Add place Payload {string} {string} {string} {double} {double} {int} {string} {string} {string}")
	public void add_place_payload(String name, String address, String phone_number, Double lat, Double lng,
			Integer accuracy, String language, String website, String types) throws IOException {
		// Write code here that turns the phrase above into concrete actions
		req = given().spec(RequestSpecifications()).body(data.addPlacePayload(name, address, phone_number, lat, lng,
				accuracy, language, website, types.split(",")));
	}

	@When("user calls {string} with {string} Http request")
	public void user_calls_add_place_api_with_post_http_request(String resource, String HttpMethod) throws ProtocolException {
		ApiResources apiResource = ApiResources.valueOf(resource);
		switch (HttpMethod.toLowerCase()) {
		case "post":
			response = req.when().post(apiResource.getResource()).then().spec(ResponseSpecfications()).extract()
					.response();
			break;
		case "get":
			response = req.when().get(apiResource.getResource()).then().spec(ResponseSpecfications()).extract()
					.response();
			break;
		case "delete":
			response = req.when().delete(apiResource.getResource()).then().spec(ResponseSpecfications()).extract()
					.response();
			break;
		case "put":
			response = req.when().put(apiResource.getResource()).then().spec(ResponseSpecfications()).extract()
					.response();
			break;
		default:
			System.out.println("Invalid Http Method");
			throw new ProtocolException("Invalid Http Method");
		}
	}

	@Then("Api call is success with status code {int}")
	public void api_call_is_success_with_status_code(Integer statusCode) {

		ResponseSpecfications().expect().statusCode(statusCode);
		// Write code here that turns the phrase above into concrete actions

	}

	@Then("{string} in responce body is {string}")
	public void in_responce_body_is(String key, String expextedValue) {
		// Write code here that turns the phrase above into concrete actions
		String actualValue = getJSONPath(response.asString(),key);

		assertEquals(expextedValue, actualValue);
	}
	@Then("Verify place Id on Newly Added Maps with UserName {string}, Api {string} and Http request {string}")
	public void verify_place_id_on_newly_added_maps_with_user_name_api_and_http_request(String name, String resource, String httpMethod) throws ProtocolException, IOException {
		place_id = getJSONPath(response.asString(), "place_id");
		req = given().spec(RequestSpecifications()).queryParam("place_id", place_id);
		user_calls_add_place_api_with_post_http_request(resource, httpMethod);
		String responseName = getJSONPath(response.asString(),"name");
		assertEquals(responseName, name);
	}
	@Given("Delete payload")
	public void delete_payload() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		DeletePayload js = data.deletePlacePayload(place_id);
	    req=given().spec(RequestSpecifications()).body(js);
	}

}
