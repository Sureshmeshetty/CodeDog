package Assume;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.hamcrest.Matchers;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.automation.BaseTest.BaseTest;
import com.automation.utils.ExtentReport;
import com.relevantcodes.extentreports.LogStatus;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TokenTest extends BaseTest {
	RequestSpecification requestSpecification;
	Response resp;
	static String access_token;

	@BeforeClass
	public void setupRequestSpecification() {
		requestSpecification = given().urlEncodingEnabled(true)
				.baseUri("https://serviciosenlinea.asume.pr.gov/asume.api_test")
				.contentType(ContentType.URLENC.withCharset("UTF-8")).formParam("password", "aldf@23901FTOPasd23")
				.formParam("grant_type", "password").formParam("client_secret", "5&t%$xZHpr9800")
				.formParam("client_id", "OGP20180821&*990899XX").formParam("username", "dToplicenciaS");
	}

	@Test
	public void validateTokenStatus() throws IOException, ParseException {
		resp = requestSpecification.when().post("/token");

		System.out.println(resp.getStatusCode());
		System.out.println(resp.getBody().jsonPath().getString("access_token"));
		System.out.println(resp.getBody().asString());
		access_token = resp.getBody().jsonPath().getString("access_token");
		ExtentReport.extentlog.log(LogStatus.INFO, "Response status code is :" + resp.getStatusCode());
		ExtentReport.extentlog.log(LogStatus.INFO,
				"Access_Token: " + resp.getBody().jsonPath().getString("access_token"));
		ExtentReport.extentlog.log(LogStatus.INFO, "Response body \n********************************************\n"
				+ resp.getBody().asString() + "\n******************************");
		resp.then().assertThat().statusCode(200);
	}

	@Test (dependsOnMethods = "validateTokenStatus")
	public void validateComplianceResponse() throws IOException, ParseException {
		int statusCode = resp.getStatusCode();
		ExtentReport.extentlog.log(LogStatus.INFO, "Response status code is :" + statusCode);
		ExtentReport.extentlog.log(LogStatus.INFO, "Response body \n********************************************\n"
				+ resp.getBody().asString() + "\n******************************");
		if (statusCode == 200) {
			resp.then().assertThat().body("token_type", Matchers.equalTo("bearer"))
					.body("expires_in", Matchers.equalTo(599)).body("userName", Matchers.equalTo("dToplicenciaS"));
		} else if (statusCode == 400) {
			resp.then().assertThat().body("error", Matchers.equalTo("invalid_gran")).body("error_description",
					Matchers.equalTo("Logon failure: unknown user name or bad password.\r\n"));
		}
	}
}
