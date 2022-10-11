package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils {
	static RequestSpecification req;
	static ResponseSpecification res;
	static PrintStream stream;
	static String BaseUri;
	static File file = new File("");

	public RequestSpecification RequestSpecifications() throws IOException {
		if (req == null) {
			globalPropertiesValue();
			stream = new PrintStream(new FileOutputStream("log.text"));
			req = new RequestSpecBuilder().setContentType(ContentType.JSON).setBaseUri(BaseUri)
					.addQueryParam("key", "qaclick123").addFilter(RequestLoggingFilter.logRequestTo(stream))
					.addFilter(ResponseLoggingFilter.logResponseTo(stream)).build();
		}
		return req;
	}

	public ResponseSpecification ResponseSpecfications() {
		res = new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();
		return res;
	}

	public static Properties globalPropertiesValue() throws IOException {
		Properties property = new Properties();
		FileInputStream input = new FileInputStream(
				file.getAbsolutePath() + "\\src\\test\\java\\resources\\global.properties");
		property.load(input);
		BaseUri = (String) property.get("BaseUri");
		return property;
	}

	public String getJSONPath(String response, String key) {
		JsonPath json = new JsonPath(response);
		return json.getString(key);
	}
}
