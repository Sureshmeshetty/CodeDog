package DTOP;

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

public class GetLicenseDetails extends BaseTest {
    RequestSpecification requestSpecification;
    int licenseId ;

    @BeforeClass
    public void setupRequestSpecification() {
        licenseId = 7006144;
        String filePath = System.getProperty("user.dir") + "/TestData/Hacienda/Compliance.json";
        System.out.println(filePath);
        File file = new File(filePath);
        requestSpecification = given().
                baseUri("https://api1.test.cescodigital.dev/api").
                basePath("/license").
                //auth().basic("ideal_dev", "q?3:t>5bi1J~>6~").
                contentType(ContentType.JSON);
    }

    @Test
    public void validateLicenseStatusCode() throws IOException, ParseException {
        Response resp = requestSpecification.
                pathParam("id",licenseId).
                when().get("/{id}");

        int statusCode = resp.getStatusCode();
        ExtentReport.extentlog.log(LogStatus.INFO, "Response status code is :" + statusCode);
        ExtentReport.extentlog.log(LogStatus.INFO, "Response body \n********************************************\n" + resp.getBody().asString() + "\n******************************");
        resp.then().assertThat().statusCode(200);
    }

    @Test
    public void validateLicenseResponse() throws IOException, ParseException {
        Response resp = requestSpecification.
                pathParam("id",licenseId).
                when().get("/{id}");

        int statusCode = resp.getStatusCode();
        ExtentReport.extentlog.log(LogStatus.INFO, "Response status code is :" + statusCode);
        ExtentReport.extentlog.log(LogStatus.INFO, "Response body \n********************************************\n" + resp.getBody().asString() + "\n******************************");
        if(statusCode==200){
            resp.then().assertThat().
                    //License Info
                    body("license.id", Matchers.equalTo(licenseId)).
                    body("license.category", Matchers.equalTo("3")).
                    body("license.type", Matchers.equalTo("LICENSE")).
                    body("license.realId", Matchers.equalTo(false)).
                    body("license.expeditionDate", Matchers.equalTo(1508769753000L)).
                    body("license.expirationDate", Matchers.equalTo(1847937600000L)).
                    body("license.dateOfBirth", Matchers.equalTo(869630400000L)).
                    body("license.address.line1", Matchers.equalTo("PORTAL DE LA REINA ")).
                    body("license.address.line2", Matchers.equalTo("12 3")).
                    body("license.address.city", Matchers.equalTo("SAN LORENZO")).
                    body("license.address.state", Matchers.equalTo("PR")).
                    body("license.address.zipcode", Matchers.equalTo("00754")).
                    body("license.unpaidFines", Matchers.equalTo(0)).
                    body("license.points", Matchers.equalTo(0)).
                    body("license.pointStep", Matchers.equalTo(null)).
                    body("license.suspended", Matchers.equalTo(false)).
                    body("license.restrictions.code", Matchers.equalTo("7")).
                    body("license.restrictions.description", Matchers.equalTo("Espejuelos o lentes de contacto")).
                    body("license.underAge", Matchers.equalTo(false)).
                    body("license.under21", Matchers.equalTo(false)).
                    body("license.renewalInProgress", Matchers.equalTo(false)).
                    body("license.alerts", Matchers.equalTo(null)).
                    body("license.expired", Matchers.equalTo(false)).
                    //Entity Info
                    body("entity.firstName", Matchers.equalTo("JUANA MARIANA ")).
                    body("entity.lastName", Matchers.equalTo("DEL CAMPO FLORIDO ")).
                    body("entity.fatherLastName", Matchers.equalTo("DEL CAMPO")).
                    body("entity.motherLastName", Matchers.equalTo("FLORIDO")).
                    body("entity.sex", Matchers.equalTo("F")).
                    body("entity.height", Matchers.equalTo("508")).
                    body("entity.weight", Matchers.equalTo("170")).
                    body("entity.eyeColor", Matchers.equalTo("BRO")).
                    body("entity.hairColor", Matchers.equalTo("BRO")).
                    body("entity.skinColor", Matchers.equalTo("MBR")).
                    body("entity.bloodType", Matchers.equalTo("AB")).
                    body("entity.donor", Matchers.equalTo(false)).
                    body("entity.selectiveService", Matchers.equalTo(false)).
                    body("entity.veteran", Matchers.equalTo(true));
        }
        else if(statusCode==404){
            resp.then().assertThat().
                    body("timestamp",Matchers.equalTo(1616084104590L)).
                    body("status",Matchers.equalTo(404)).
                    body("error",Matchers.equalTo("Not Found")).
                    body("message",Matchers.equalTo("")).
                    body("path",Matchers.equalTo("/api/license/"));
        }
    }

}
