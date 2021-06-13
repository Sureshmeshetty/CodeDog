package Hacienda;

import com.automation.BaseTest.BaseTest;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.io.output.ProxyOutputStream;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.hamcrest.Matchers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import static io.restassured.RestAssured.given;

public class AuthorizeTest extends BaseTest {
    RequestSpecification requestSpecification;


    @Test
    public void validateAuthoriseStatus() {
        Response resp = requestSpecification.
                when().post("/authorize");

        int statusCode = resp.getStatusCode();

        System.out.println("Response status code is :" + statusCode);
        System.out.println("Response body \n********************************************\n" + resp.getBody().asString() + "\n******************************");
        resp.then().assertThat().statusCode(200);
    }

    @Test
    public void validateAuthoriseResponseBody() {
        Response resp = requestSpecification.
                when().post("/authorize");

        int statusCode = resp.getStatusCode();

        System.out.println("Return status code is :" + statusCode);
        System.out.println("Return response body \n******************************\n" + resp.getBody().asString() + "\n******************************");
        if (statusCode == 200) {
            resp.then().assertThat().
                    body("successful", Matchers.equalTo(true)).
                    body("customerExists", Matchers.equalTo("true")).
                    body("approved", Matchers.equalTo(true)).
                    body("customerKey", Matchers.equalTo(1234567890)).
                    body("errorMessage", Matchers.equalTo(""));
        } else if (statusCode == 500) {
            resp.then().assertThat().
                    body("successful", Matchers.equalTo(false)).
                    body("errorMessage", Matchers.equalTo("Unable to complete request at this time. Please try again later. If problem persists contact the Department of Hacienda"));
        }
    }
}
