package stepsDefinitions;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import RequestsPOJO.Users;

import org.hamcrest.Matchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.google.common.io.Files;
import com.sun.org.apache.xerces.internal.impl.xs.identity.Selector.Matcher;

import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class GetStepsDefinitions {

	Users user;

	@Given("User can create POJO class into json.")
	public void user_can_create_pojo_class_into_json() {

		RestAssured.baseURI = "https://reqres.in/api/users";

		user = new Users();
		user.setJob("QA");
		user.setName("John");

		RequestSpecification spec = RestAssured.given();
		spec.body(user);

		Response response = spec.post();
		System.out.println(response.asString() +" "+ "POJO practice");
		
		System.out.println("Headers: "+response.headers());

	}

	@Given("User can deserialize json into pojo class.")
	public void user_can_deserialize_json_into_pojo_class() {
		
		
		

	}

	@Given("User launches GET request.")
	public void user_launches_get_request() {

		RestAssured.baseURI = "https://reqres.in/api/users/2";

		RequestSpecification httpRequest = RestAssured.given();

		Response response = httpRequest.request(Method.GET, "");

		// System.out.println("Status received => " + response.getStatusLine());
		System.out.println("Response=>" + response.prettyPrint());
		System.out.println("Practice matchers: ");
		response.then().body("data.id", Matchers.greaterThan(0));
		// find response time

		long responsetime = response.getTime();

		// System.out.println(responsetime + " :Response time in miliseconds by
		// default");

		long responsetimeinSeconds = response.getTimeIn(TimeUnit.SECONDS);
		// System.out.println(responsetimeinSeconds + " :Response time in seconds.");

		// using below we can compare the response time without fetching it.
		ValidatableResponse res = response.then().time(Matchers.greaterThanOrEqualTo(400L));

		String jsonArrayString = "{\r\n" + "  \"firstName\": \"Amod\",\r\n" + "  \"lastName\": \"Mahajan\",\r\n"
				+ "  \"address\": [\r\n" + "    {\r\n" + "      \"type\": \"permanent\",\r\n"
				+ "      \"city\": \"Bengaluru\",\r\n" + "      \"state\": \"KA\"\r\n" + "    },\r\n" + "    {\r\n"
				+ "      \"type\": \"temp\",\r\n" + "      \"city\": \"Bhopal\",\r\n" + "      \"state\": \"MP\"\r\n"
				+ "    }\r\n" + "  ]\r\n" + "}";
		;
		// use jsonpath class
		JsonPath json = JsonPath.from(jsonArrayString);

		// to get first name
		System.out.println(" First name is: " + json.get("firstName"));

		// find address types
		List<String> addresstypes = json.getList("address.type");
		System.out.println(addresstypes);

		// find all addresses with type permanent.
		// nodename.findAll{it -> it.fieldname == 'cond'}

		// List<String> pertype = json.getList("address.findAll{it->it.type ==
		// 'permanent' & it.city == 'Bengal'}");
		// System.out.println(pertype);

		// find all the keys in json
		ObjectMapper map = new ObjectMapper();
		try {

			JsonNode nodes = map.readTree(jsonArrayString);
			Iterator<String> allkeys = nodes.fieldNames();
			allkeys.forEachRemaining(k -> {

				Object value = nodes.get(k);
				if (value instanceof TextNode)
					System.out.print(k);

				else if (value instanceof ObjectNode) {
					Iterator<String> keyss = ((ObjectNode) value).fieldNames();
					keyss.forEachRemaining(ke -> System.out.println(ke));
				}

			});

		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
