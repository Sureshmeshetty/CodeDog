package Assume;

import com.automation.BaseTest.BaseTest;
import com.automation.utils.ExtentReport;
import com.relevantcodes.extentreports.LogStatus;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

import static io.restassured.RestAssured.given;

public class certificacionesIndividuoGetParticipantStatus extends BaseTest{
    RequestSpecification requestSpecification;

    @BeforeClass
    public void setupRequestSpecification() {
        String filePath = System.getProperty("user.dir") + "/TestData/Hacienda/Compliance.json";
        System.out.println(filePath);
        File file = new File(filePath);
        requestSpecification = given().
                baseUri("https://serviciosenlinea.asume.pr.gov/asume.api_test/api").
                auth().basic("ideal_dev", "q?3:t>5bi1J~>6~").
                contentType(ContentType.JSON);
    }

    @Test
    public void validateParticipantStatus() throws IOException, ParseException {
        String filePath = System.getProperty("user.dir") + "/TestData/Assume/Token.json";
        JSONArray jsonArray = BaseTest.getJsonArray(filePath);
        for(int i=0;i<jsonArray.size();i++) {
            String payload = jsonArray.get(0).toString();
            System.out.println(payload);
            Response resp = requestSpecification.
                    body(payload).
                    when().post("/certificaciones/individuo/getparticipantstatus");

            int statusCode = resp.getStatusCode();
            ExtentReport.extentlog.log(LogStatus.INFO,"Response status code is :" + statusCode);
            ExtentReport.extentlog.log(LogStatus.INFO,"Response body \n********************************************\n" + resp.getBody().asString() + "\n******************************");
            resp.then().assertThat().statusCode(200);
        }
    }
}
