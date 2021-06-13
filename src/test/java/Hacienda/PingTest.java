package Hacienda;

import com.automation.BaseTest.BaseTest;
import com.automation.utils.ExtentReport;
import com.relevantcodes.extentreports.LogStatus;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class PingTest extends BaseTest {
    RequestSpecification requestSpecification;

    @BeforeClass
    public void setupRequestSpecification()
    {
        requestSpecification = given().
                baseUri("https://tapstaging.hacienda.pr.gov/TAS/Services/External").
                auth().basic("ideal_dev","q?3:t>5bi1J~>6~");
    }

    @Test
    public void getPingStatus() {
        Response resp = requestSpecification.
                when().get("/ping");

        int statusCode=resp.getStatusCode();

        ExtentReport.extentlog.log(LogStatus.INFO,"Return status code is :" + statusCode);
        ExtentReport.extentlog.log(LogStatus.INFO,"Return response body \n******************************\n"+resp.getBody().asString()+"\n******************************");
        resp.then().assertThat().statusCode(200);
    }
}
