package Hacienda;

import com.automation.BaseTest.BaseTest;
import com.automation.utils.ExtentReport;
import com.relevantcodes.extentreports.LogStatus;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

import static io.restassured.RestAssured.given;

public class ComplianceDocuments extends BaseTest {
    RequestSpecification requestSpecification;

    //complianceDocuments
    @BeforeClass
    public void setupRequestSpecification() {
        String filePath = System.getProperty("user.dir") + "/TestData/Hacienda/ComplianceDocuments.json";
        System.out.println(filePath);
        File file = new File(filePath);
        requestSpecification = given().
                baseUri("https://tapstaging.hacienda.pr.gov/TAS/Services/External").
                auth().basic("ideal_dev", "q?3:t>5bi1J~>6~").
                contentType(ContentType.JSON);
    }

    @Test
    public void validateComplianceDocumentsStatus() throws IOException, ParseException {

        String filePath = System.getProperty("user.dir") + "/TestData/Hacienda/Compliance.json";
        JSONArray jsonArray = BaseTest.getJsonArray(filePath);
        for (int i = 0; i < jsonArray.size(); i++) {
            String payload = jsonArray.get(0).toString();
            System.out.println(payload);
            Response resp = requestSpecification.
                    body(payload).
                    when().post("/complianceDocuments");

            ExtentReport.extentlog.log(LogStatus.INFO, "Response status code is :" + resp.getStatusCode());
            ExtentReport.extentlog.log(LogStatus.INFO, "Response body \n********************************************\n" + resp.getBody().asString() + "\n******************************");
            resp.then().assertThat().statusCode(200);
        }
    }

    @Test
    public void validateComplianceDocumentsResponse() throws IOException, ParseException {

        String filePath = System.getProperty("user.dir") + "/TestData/Hacienda/Compliance.json";
        JSONArray jsonArray = BaseTest.getJsonArray(filePath);
        for (int i = 0; i < jsonArray.size(); i++) {
            String payload = jsonArray.get(0).toString();
            System.out.println(payload);
            Response resp = requestSpecification.
                    body(payload).
                    when().post("/complianceDocuments");

            int statusCode = resp.getStatusCode();

            ExtentReport.extentlog.log(LogStatus.INFO, "Response status code is :" + resp.getStatusCode());
            ExtentReport.extentlog.log(LogStatus.INFO, "Response body \n********************************************\n" + resp.getBody().asString() + "\n******************************");
            if (statusCode == 200) {
                resp.then().assertThat().
                        body("successful", Matchers.equalTo(true)).
                        body("customerExists", Matchers.equalTo(true)).
                        body("nameMatch", Matchers.equalTo(1)).
                        body("customerKey", Matchers.equalTo(1234567890)).
                        body("merchantDetail", Matchers.equalTo(1234567890)).
                        body("complianceDetail", Matchers.equalTo(1234567890)).

                        //merchantDetail
                        body("merchantMatch", Matchers.equalTo(1234567890)).
                        body("merchantActive", Matchers.equalTo(true)).
                        body("merchantCompliant", Matchers.equalTo(true)).

                        //complianceDetail
                        body("debtCompliant", Matchers.equalTo(true)).
                        body("filingCompliant", Matchers.equalTo(true)).
                        body("filingCompliantSUT", Matchers.equalTo(true)).
                        body("PaymentPlan", Matchers.equalTo(true)).
                        body("errorMessage",Matchers.equalTo(""));
            }
        }
    }
}
