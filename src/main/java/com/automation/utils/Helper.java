package com.automation.utils;

//import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.IOUtils;

//import io.restassured.http.ContentType;
//import io.restassured.response.Response;


public class Helper {

    public static final String commonFilePath = System.getProperty("user.dir") + "/common.properties";

    /*
     *  To print logs  file
     */
    public static void loginfo(String value, String message) {
        final Logging log = Logging.getInstance();
        log.info(value, message);
    }

    /*
     * create folder
     */
    public static void CreateFolder(String path) {
        //File is a class inside java.io package
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();//mkdir is used to create folder
        } else
            System.out.println("Folder already created");
    }

    /*
     * Return current time stamp
     */
    public static String Timestamp() {

        Date now = new Date();
        String Timestamp = now.toString().replace(":", "-");
        return Timestamp;

    }


    /*
     * @param filepath key
     * @return value of the key
     */
    public static String propertyReader(String filePath, String key) {
        String value = null;
        //Inputstream is required while loading into properties
        try (InputStream input = new FileInputStream(filePath)) {

            Properties prop = new Properties();

            // load a properties file
            prop.load(input);

            //getProperty will fetch the value according to the key
            value = prop.getProperty(key);


        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("Config file not able to fetch the value for key");
        }
        return value;

    }


    /*
     * params tailUrl - tail part of API token - Authorization token
     *
     * return - response of the API
     */
//	public static Response getReponse(String tailUrl, String token) {
//
//		String baseUrl = Helper.propertyReader(Helper.commonFilePath, "baserurl");
//
//		Helper.loginfo("Hitting API URL :- ", baseUrl + tailUrl);
//		ExtentReport.ExtentReportInfoLog("Hitting API URL :- "+ baseUrl + tailUrl);
//		Response resp = null;
//
//		try {
//
//			resp = given().when().contentType(ContentType.JSON).header("Authorization", "Bearer " + token)
//					.get(baseUrl + tailUrl);
//
//			String responseTime = String.valueOf(resp.getTime());
//			String statusCode = String.valueOf(resp.getStatusCode());
//
//			// Adding logs
//			Helper.loginfo("Response :- ", resp.body().asString());
//			Helper.loginfo("Response time :- ", responseTime);
//			Helper.loginfo("Status code :- ", statusCode);
//
//			// adding logs to html report
//			ExtentReport.ExtentReportInfoLog("Response time:     " + responseTime);
//			ExtentReport.ExtentReportInfoLog("Status code:     " + statusCode);
//
//		} catch (Exception e) {
//			Helper.loginfo("Error :- ", e.getMessage());
//		}
//
//		return resp;
//	}
//	public static Response postUserDetails(String tailUrl) throws FileNotFoundException {
//		
//		FileInputStream file = new FileInputStream(new File (System.getProperty("user.dir")+"\\TestData\\testdata.json"));
//		
//			String baseUrl = Helper.propertyReader(Helper.commonFilePath, "baserurl");
//
//			Helper.loginfo("Hitting API URL :- ", baseUrl + tailUrl);
//			
//			Response resp = null;
//			
//			try{
//				
//		    resp = given().header(ReadTestData.getTestData("content"),ReadTestData.getTestData("content-type")).
//		            body(IOUtils.toString(file,"UTF-8")).
//		            
//		    when().
//		            post(baseUrl + tailUrl);
//			
//			//Adding logs to log and html report
//			Helper.loginfo("Response :- ", resp.body().asString());
//			Helper.loginfo("Response time :- ", String.valueOf(resp.getTime()));
//			ExtentReport.ExtentReportInfoLog("Response time:     " + String.valueOf(resp.getTime()));
//			}
//			catch (Exception e) {
//				Helper.loginfo("Error :- ", e.getMessage());
//			}
//			
//			return resp;
//		}


}
