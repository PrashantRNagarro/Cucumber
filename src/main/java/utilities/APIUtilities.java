package utilities;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.maven.shared.utils.io.FileUtils;
import org.hamcrest.Matchers;

import com.aventstack.extentreports.model.Test;
import com.google.common.io.Files;

public class APIUtilities {

	public void user_launches_get_request() {

		RestAssured.baseURI = "https://reqres.in/api/users/2";

		RequestSpecification httpRequest = RestAssured.given();

		Response response = httpRequest.request(Method.GET, "");

		System.out.println("Status received => " + response.getStatusLine());
		System.out.println("Response=>" + response.prettyPrint());

		// copy response into another json
		byte[] responsenytes = response.asByteArray();

		try {
			Files.write(responsenytes, new File("src/test/resources/target.json"));
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	// QUERY PARAMETERS

	public void passQueryParm() {

		RestAssured.baseURI = "https://bookstore.toolsqa.com/BookStore/v1";

		RequestSpecification spec = RestAssured.given().queryParam("ISBN", "9781449325862");
		Response res = spec.get();
		
		// parsing json response as test class which could be pojo class.
		
		
		//deserialising the json response
		System.out.println(res.getBody().as(Test.class));

	}

	public void PostMethod() {

		String jsonString = "{\"username\" : \"admin\",\"password\" : \"password123\"}";

		Response response = RestAssured.given().contentType(ContentType.JSON).body(jsonString).when().post();
		
		ValidatableResponse resp = RestAssured.given().contentType(ContentType.JSON).body(jsonString).when().post().then();
		
		
		//resp.body("", Matchers.hasSize())
		
		System.out.println(response.body() + " " + response.getStatusCode());

	}

	public void parseJSON() {
		
		String jsonString = "{\"username\" : \"admin\",\"password\" : \"password123\"}";
		
		
		//we can use file path or object in from method
		JsonPath json = JsonPath.from(jsonString);
		//System.out.println(json.get("$.username"));
		
				
	}
	
	
	
	public void fetchAllKeysFromJSON() {
		
		
		
		

	}

}
