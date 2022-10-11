package stepDefinition;

import java.io.IOException;

import org.apache.http.ProtocolException;

import io.cucumber.java.Before;
import resources.Utils;

public class Hooks {

	@Before("@deletePlaceApi")
	public void beforeScenario() throws IOException, ProtocolException {
		if (StepDefinition.place_id == null) {
			StepDefinition sd = new StepDefinition();
			Utils util = new Utils();
			sd.add_place_payload("sai", "tir", "124", 12.2, 123.3, 30, "ENG", "google", "a,b");
			sd.user_calls_add_place_api_with_post_http_request("addPlaceApi", "post");
			StepDefinition.place_id = util.getJSONPath((StepDefinition.response).asString(), "place_id");
		}
	}
}
