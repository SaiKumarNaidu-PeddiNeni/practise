package resources;

import pojo.GoogleMapApi;
import pojo.Location;
import pojo.DeletePayload;

public class TestDataBuild {
	DeletePayload deletePayload;
	
	public GoogleMapApi addPlacePayload(String name, String address, String phone_number, Double lat, Double lng, Integer accuracy, String language, String website, String[] types) {
		GoogleMapApi gma = new GoogleMapApi();
		// Write code here that turns the phrase above into concrete actions
		//String[] types = { "shoes", "school" };
		Location loc = new Location();
		loc.setLat(lat);
		loc.setLng(lng);
		gma.setAccuracy(accuracy);
		gma.setAddress(address);
		gma.setLanguage(language);
		gma.setLocation(loc);
		gma.setName(name);
		gma.setPhone_number(phone_number);
		gma.setWebsite(website);
		gma.setTypes(types);
		return gma;
	}

	public DeletePayload deletePlacePayload(String place_id) {
		deletePayload = new DeletePayload();
		deletePayload.setPlace_id(place_id);
		return deletePayload;
	}

}
