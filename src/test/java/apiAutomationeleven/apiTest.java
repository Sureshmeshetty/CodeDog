package apiAutomationeleven;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import io.restassured.response.Response;
import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;
import org.json.simple.parser.*;

public class apiTest {
@Test
public static void verifyGetStatusCode() {
	Response resp = given().queryParam("page", "2").
			when().get("https://reqres.in/api/users");
	int statusCode=resp.getStatusCode();
	System.out.println("Return status code is :" + statusCode);
	System.out.println("Return response body ********************************************"+resp.getBody().asString()+"******************************");
	assertEquals(statusCode,200);
	
}

@Test(description="Validate total for page 2 is 12")
public static void verifyGetStatusCodewithoutqp() {
	Response resp = given().
			when().get("https://reqres.in/api/users?page=2");
	int statusCode=resp.getStatusCode();
	System.out.println("Return status code is :" + statusCode);
	System.out.println("Return response body ********************************************"+resp.getBody().asString()+"******************************");
	int i=resp.path("total");
	System.out.println("The value of total is: "+ i);
	assertEquals(statusCode,200);
	assertEquals(i,12);
	
}
@Test(description="Validate total for page 2 is 12")
public static void verifyurl() {
	Response resp = given().
			when().get("https://reqres.in/api/users?page=2");
	String expected="https://reqres.in/#support-heading";
	int statusCode=resp.getStatusCode();
	System.out.println("Return status code is :" + statusCode);
	System.out.println("Return response body ********************************************"+resp.getBody().asString()+"******************************");
	String i=resp.path("support.url");
	System.out.println("The value of URL is: "+ i);
	assertEquals(statusCode,200);
	assertEquals(i,expected);
	
}

@Test
public static void auth() {
	//basic
	Response resp = given()
			.auth()
			.basic("sid", "sid").when().get("https://reqres.in/api/users/2");
	String authtoken= resp.path("accessToken");
	//preemptive
	Response resp1 = given()
			.auth()
			.preemptive().basic("sid", "sid").when().get("https://reqres.in/api/users/2");
	//digest
	Response resp2 = given()
			.auth()
			.digest("sid", "sid").when().get("https://reqres.in/api/users/2");
	//oauth2
	Response resp3 = given()
			.auth()
			.oauth2("tretares65qw6d5fwedy8887846h").when().get("https://reqres.in/api/users/2");
	//oauth1
	Response resp4 = given()
			.auth()
			.oauth("consumerKey", "consumerSecret", "accessToken", "secretToken").when().get("https://reqres.in/api/users/2");
	//using header
	Response resp5 = given().header("Authorization","tretares65qw6d5fwedy8887846h")
				.when().get("https://reqres.in/api/users/2");
			 
}
}
