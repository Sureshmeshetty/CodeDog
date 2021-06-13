package apiTest;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.automation.BaseTest.BaseTest;
import com.automation.utils.ExtentReport;

import io.restassured.response.Response;
import pojo.pojoClass;

public class testApi extends BaseTest{
	
	
	@Test(description="Validate 200 as status code with wrong base URI")
	public void Validate404ForUsers()
	{
		ExtentReport.extentlog = ExtentReport.extentreport.startTest("Validate404ForUsers",
				"status code should be 404");
////		Response resp = given().
//				when().get("https://reqres.in/api/users/876");
		
		Response resp = given().when().get("https://reqres.in/api/users?page=2");
		int statuscode = resp.getStatusCode();
		assertEquals(statuscode,200);
		System.out.println(" Status code for API is:" + statuscode);
	}

	@Test(groups="RegressionSuite")
	public static void putmethodpojo() {
		ExtentReport.extentlog = ExtentReport.extentreport.startTest("putmethodpojo",
				"Validate the job is sdet or not");
		
		   pojoClass pjc = new pojoClass("tester", "sdet");
		
		   Response resp = given().header("Content-Type","application/json").
		            body(pjc).
		            when().
		            post("https://reqres.in/api/users");
		    System.out.println(resp.getBody().asString());
		    assertEquals(resp.path("job"),"sdet");
		    
	}
//	
//	@Test(description="Verify status code for GET method-users/2 as 200")
//	 public static void verifyStatusCodeGET() throws IOException, org.json.simple.parser.ParseException {
//		ExtentReport.extentlog = ExtentReport.extentreport.startTest("Execute post method",
//				" validate post method");
//	     Response resp=given().when().get(ReadTestData.getTestData("urigetuser"));
//	     assertEquals(resp.getStatusCode(),200);
//	     assertEquals(resp.path("support.url"),ReadTestData.getTestData("supportURL"));
//	}
//	
//	@Test
//    public void validateQueryParamInGiven() {
//		ExtentReport.extentlog = ExtentReport.extentreport.startTest("Execute post method",
//				" validate post method");
//        Response resp = given().queryParam("page", "2").queryParam("page", "2").
//                when().get("https://reqres.in/api/users");
//
//        assertEquals(resp.getStatusCode(),200);
//
//        System.out.println(resp.getBody().asString());
//    }
//   
//	
////	@Test
////	public static void getUserDetails() {
////	String baseUrl = Helper.propertyReader("C:\\Users\\gs-2181\\eclipse-workspace\\APIG6\\common.properties", "baserurl");
////
////	Helper.loginfo("Hitting API URL :- ", baseUrl);
////	
////	Response resp = null;
////		
////    resp = given()
////    		.when().get(baseUrl);
////    System.out.println(resp.getBody().asString());
////    }
////	
//	@Test(description="B_Ussers:Validate 200 status code for /users GET API Call")
//	public void TC_001verifyGetUserStatusCode() throws IOException, org.json.simple.parser.ParseException {
//		ExtentReport.extentlog = ExtentReport.extentreport.startTest("Execute post method",
//				" validate post method");
//		Response resp = given()
//				.when().get(ReadTestData.getTestData("urigetuser"));
//		
//		int actualstatuscode = resp.getStatusCode();
//		assertEquals(actualstatuscode,200);
//		System.out.println("Status Code of the GET Request is: " + actualstatuscode);
//		System.out.println(resp.getBody().asString());
//		}
////		
////	
//	@Test(priority=2,groups="Auth_OAUTH")
//	public static void auth() {
//		Response resp = given()
//				.auth()
//				.basic("postman", "password").when().get("https://postman-echo.com/digest-auth");
//		Response resp1 = given()
//				.auth()
//				.preemptive().basic("sid", "sid").when().get("https://reqres.in/api/users/2");
//		Response resp2 = given()
//				.auth()
//				.digest("sid", "sid").when().get("https://reqres.in/api/users/2");
//		Response resp3 = given()
//				.auth()
//				.oauth2("accesstoken").when().get("https://reqres.in/api/users/2");
//		Response resp4 = given()
//				.auth()
//				.oauth("consumerKey", "consumerSecret", "accessToken", "secretToken").when().get("https://reqres.in/api/users/2");
//		Response resp5 = given().header("Authorization","Bearer ywtfuytweHNWIuy8r7y6r8764gh")
//					.when().get("https://reqres.in/api/users/2");
//				 
//	}
////	
////	@Test(description="putpost", groups="RegressionSuite")
////	public static void postputwithpojo() throws Exception {
////		ExtentReport.extentlog = ExtentReport.extentreport.startTest("Execute post method",
////				" validate post method");
////		pojoClass pjcpost = new pojoClass("morpheus", "tester");
////		pojoClass pjcput = new pojoClass("morpheus", "sdet");
////		
////		Response resppost = given().header("Content-Type","application/json").
////		             body(pjcpost).when().post(ReadTestData.getTestData("uri"));
////	    System.out.println(resppost.getBody().asString());
////	    assertEquals(resppost.getStatusCode(),201);
////	    Thread.sleep(2000);
////		Response respput = given().header("Content-Type","application/json").
////	             body(pjcput).when().put(ReadTestData.getTestData("putUserUri"));
////		 System.out.println(respput.getBody().asString());
////		 assertEquals(respput.getStatusCode(),200);
////	}
////	
//	@Test(groups="RegressionSuite")
//	public static void putmethodpojo() {
//		ExtentReport.extentlog = ExtentReport.extentreport.startTest("Execute post method",
//				" validate post method");
//		String jobname= "zion resient";
//		pojoClass pjc = new pojoClass("morpheus", jobname);
//		
//		Response resp = given().header("Content-Type","application/json").
//		           // body(requestParams.toString()).
//		             body(pjc).
//		           
//		    when().
//		            put("https://reqres.in/api/users/2");
//		    System.out.println(resp.getBody().asString());
//		    assertEquals(resp.path("job"),jobname);
//		    
//	}
//	@Test(groups="RegressionSuite")
//	public static void patchmethodpojo() {
//		ExtentReport.extentlog = ExtentReport.extentreport.startTest("Execute post method",
//				" validate post method");
//		String jobname= "zion resient";
//		pojoClass pjc = new pojoClass("morpheus", jobname);
//		
//		Response resp = given().header("Content-Type","application/json").
//		           // body(requestParams.toString()).
//		             body(pjc).
//		           
//		    when().
//		            patch("https://reqres.in/api/users/2");
//		    System.out.println(resp.getBody().asString());
//		    assertEquals(resp.path("job"),jobname);
//		    
//	}
//	@Test(description="Verify status code for GET method-users/2 as 200",groups="SmokeSuite")
//	 public static void verifyResponsejson() throws IOException, org.json.simple.parser.ParseException {
//		ExtentReport.extentlog = ExtentReport.extentreport.startTest("Execute post method",
//				" validate post method");
//		 Response resp=given().when().get("https://reqres.in/api/users?page=2");
//		 System.out.println(resp.path("total").toString());
//		 assertEquals(resp.getStatusCode(),200);
//		 
//		 assertEquals(resp.path("total").toString(),"12");
//		
//		 
//		 List<String> expected = new ArrayList<String>();
//		 
//		 List<String> jsonResponse = resp.jsonPath().getList("data");
//	     System.out.println(jsonResponse.size());
//	     System.out.println("The number of data in the list is : " + jsonResponse.size());
//	     assertEquals(jsonResponse.size(),6);
//	     String usernames = resp.jsonPath().getString("data[1].email");
//	     System.out.println("Email is : " + usernames);
//	//   //  List<String> jsonResponses = resp.jsonPath().getList("data");
//	//   //  System.out.println(jsonResponses.get(0));
//	     Map<String, String> company = resp.jsonPath().getMap("data[2]");
//	     System.out.println(company);
//	     List<String> tset = new ArrayList<>(company.keySet());
//	     for (int j=0;j<tset.size();j++) {
//	    	 System.out.println("The keys for map in data is : "+ tset.get(j));
//	     }
//	     System.out.println("Fetch firtsname using map and get: " + company.get("first_name"));
//	   //  List<Map<String, String>> companies = resp.jsonPath().getList("data");
//	   //  System.out.println(companies.get(0).get("first_name"));
//	    List<String> ids = resp.jsonPath().getList("data.email");
////	    Collections.sort(jsonResponse);
////	    Collections.sort(expected);
////	    assertEquals(jsonResponse,expected);
//	     for(String i:ids)
//	     {
//	    	 System.out.println(i);
//	        if (i.contentEquals("lindsay.ferguson@reqres.in")) {
//	        	System.out.println("lindsay.ferguson is present in the response body");
//	        }
//	     }
//	}
//	@Test(description="validate 204 for Delete user",groups= {"RegressionSuite","B_User"})
//	public void verifyStatusCodeDelete() {
//		
//		Response resp = given().delete("https://reqres.in/api/users/2");
//		assertEquals(resp.getStatusCode(),204);
//		System.out.println("***********************************PASS*******************************");
//		
//	}
////	
//	@Test(description="Verify email for User with id=2",groups= {"SmokeSuite","RegressionSuite","Payment"})
//	public void verifyEmailUIDPathParamGet() throws IOException, ParseException, org.json.simple.parser.ParseException {
//		ExtentReport.extentlog = ExtentReport.extentreport.startTest("Execute post method",
//				" validate post method");
//		String str ="2017";
//		Response resp = given().pathParam("raceSeason", str).
//				when().get("http://ergast.com/api/f1/{raceSeason}/circuits.json");
//		assertEquals(resp.path("MRData.CircuitTable.season"),str);
//		//assertEquals(resp.path("MRData.total"),ReadTestData.getTestData("total"));
//		System.out.println(resp.getBody().asString());
//		
//	}
//	
////	@Test(description="Verify email for User with id=2")
////	public void verifyEmailUIDheaders() {
////		ExtentReport.extentlog = ExtentReport.extentreport.startTest("Execute post method",
////				" validate post method");
////		Response resp = given().pathParam("raceSeason", "2018").header("Content-Type","application/json").
////				when().get("http://ergast.com/api/f1/{raceSeason}/circuits.json");
////		assertEquals(resp.path("MRData.total"),"21");
////		System.out.println(resp.getBody().asString());
////		
////	}
////
////	@Test(description="Verify email for User with id=2")
////	public void verifyEmailUID2() {
////		ExtentReport.extentlog = ExtentReport.extentreport.startTest("Execute post method",
////				" validate post method");
////		Response resp = given().queryParam("page", "2").
////				when().get("https://reqres.in/api/users");
////		assertEquals(resp.path("per_page").toString(),"6");
////		
////	}
////	@Test(description="validate with jsonpath and json object and pass post body as json file")
////	public void postMethodValidationDataFile() throws IOException, ParseException, org.json.simple.parser.ParseException {
////		FileInputStream file = new FileInputStream(new File (System.getProperty("user.dir")+"/Resources/TestData/postuser.json"));
////		
////		String url = ReadTestData.getTestData("uri");
////		String tailuri = ReadTestData.getTestData("tailURL");
////		//String uril = ReadTestData.getdatafromjson("$.uri");
////		System.out.println(url);
////		
////		io.restassured.response.Response resp = 
////				given().headers("Content-Type","application/json").
////		            body(IOUtils.toString(file,"UTF-8")).
////		            
////		    when().
////		            post("https://reqres.in/api/users");
////		   // Response resp = postUserDetails(tailuri);
////		
////		    assertEquals(resp.getStatusCode(),201);
////		    System.out.println(resp.getBody().asString());
////	   
////	}	
////	
//	@Test(description= "Automate post method for users")
//	public static void postmethodjson() throws IOException, ParseException, org.json.simple.parser.ParseException {
//		ExtentReport.extentlog = ExtentReport.extentreport.startTest("CreateUserPostMethod",
//				" validate post method");
//		FileInputStream file = new FileInputStream(new File (System.getProperty("user.dir")+"/Resources/TestData/postrequest.json"));
//		
//		Response resp = given().header(ReadTestData.getTestData("headertypeContent"),ReadTestData.getTestData("contenttypeValue")).
//				body(IOUtils.toString(file, ReadTestData.getTestData("encodingType"))).
//				when().post(ReadTestData.getTestData("uri"));
//		assertEquals(resp.getStatusCode(),201);
//		System.out.println("The status code for post method is : " + resp.getStatusCode() );
//		System.out.println("The response body of the post method is : " + resp.getBody().asString());
//		//assertEquals(resp.path("job"),ReadTestData.getTestData("expectedJob"));
//		
//	}
//	@Test(description= "Automate post method for users")
//	public static void postAutoExtFile() throws IOException, ParseException, org.json.simple.parser.ParseException {
//		ExtentReport.extentlog = ExtentReport.extentreport.startTest("CreateUserPostMethod",
//				" validate post method");
//		File filerb = new File("/Users/sishukla/Downloads/apiAutomationeleven 2/Resources/TestData/postmethodrequestbody.json");
//		
//		FileInputStream file = new FileInputStream(filerb);
//		List<String> jobss= new ArrayList<String>();
//		jobss.add("tester");
//		jobss.add("developer");
//		jobss.add("support");
//		
//		cityModel cityBan=new cityModel();
//		cityBan.setName("bangalore");
//		cityBan.setTemperature("30");
//		cityModel cityDelhi=new cityModel();
//		cityDelhi.setName("delhi");
//		cityDelhi.setTemperature("40");
//		List<cityModel> jobsss= new ArrayList<cityModel>();
//		jobsss.add(cityBan);
//		jobsss.add(cityDelhi);
//		
//		
//		pojoTry pojorb = new pojoTry();
//		pojorb.setName("morpheus");
//		pojorb.setJob("leader");
//		pojorb.setJobs(jobss);
//		pojorb.setCitymodels(jobsss);
//		
//		Response resp = given().header("Content-Type","application/json").
//				//body(IOUtils.toString(file, "UTF-8")).
//				body(pojorb).
//				when().post("https://reqres.in/api/users");
//		
//		assertEquals(resp.getStatusCode(),201);
//		//System.out.println("The status code for post method is : " + resp.getStatusCode() );
//		System.out.println("The response body of the post method is : " + resp.getBody().asString());
//		//assertEquals(resp.path("job"),ReadTestData.getTestData("expectedJob"));
//	}
////	
////	@Test(description= "Automate post method for users")
////	public static void putmethodjson() throws IOException, ParseException, org.json.simple.parser.ParseException {
////		ExtentReport.extentlog = ExtentReport.extentreport.startTest("Execute post method",
////				" validate post method");
////		FileInputStream file = new FileInputStream(new File (System.getProperty("user.dir")+"\\Resources\\TestData\\testdata.json"));
////		
////		Response resp = given().header(ReadTestData.getTestData("headertypeContent"),ReadTestData.getTestData("contenttypeValue")).
////				body(IOUtils.toString(file, ReadTestData.getTestData("encodingType"))).
////				when().put(ReadTestData.getTestData("putUserUri"));
////		
////		assertEquals(resp.getStatusCode(),200);
////		System.out.println("The status code for post method is : " + resp.getStatusCode() );
////		System.out.println("The response body of the post method is : " + resp.getBody().asString());
////		assertEquals(resp.path("job"),ReadTestData.getTestData("expectedJob"));
////		
////	}
////	
//	@Test
//	public static void postmethodwithpojoclass() {
//		ExtentReport.extentlog = ExtentReport.extentreport.startTest("Execute post method",
//				" validate post method");
//		String job = RandomUtils.generateRandomString(5);
//		String name = RandomUtils.generateRandomNumericString(8);
//		pojoClass obj = new pojoClass(name,job);
//		Response resp = given().headers("Content-Type","application/json").
//				body(obj).
//				when().post("https://reqres.in/api/users");
//		assertEquals(resp.getStatusCode(),201);
//		System.out.println("The status code for post method is : " + resp.getStatusCode() );
//		System.out.println("The response body of the post method is : " + resp.getBody().asString());
//		assertEquals(resp.path("job"),job);
//		assertEquals(resp.path("name"),name);
//	}
//   @Test
//	public static void listcheck() {
//	   ExtentReport.extentlog = ExtentReport.extentreport.startTest("Execute post method",
//				" validate post method");
//
//   List<String> lst=new ArrayList<String>();
//   lst.add("tester");
//   lst.add("developer");
//   for(int i=0;i<lst.size();i++) {
//	   System.out.println(lst.get(i));
//   }
//	
//   Map<String,String> hmap= new HashMap<String,String>();
//   hmap.put("urigetuser","https://reqres.in/api/users?page=2");
//   hmap.put("first_name","janet");
//   
//   Set<String> hset = new HashSet<String>();
//   Set<Entry<String, String>> esets = hmap.entrySet();
//   for (Map.Entry<String, String> ent : esets) {
//	   System.out.print(ent.getKey() + ent.getValue());
//   }
//   
// }
//   @Test
//	public static void postmethodwithpojoclasss() {
//		ExtentReport.extentlog = ExtentReport.extentreport.startTest("Execute post method",
//				" validate post method");
//		String job = "tester";
//		String name = "automation";
//		List<String> city = new ArrayList<String>();
//		city.add(RandomUtils.generateRandomString(5));
//		city.add(RandomUtils.generateRandomString(4));
//		city.add("ctc");
//		city.add("bbsr");
//		city.add("blr");
//		
//		cityModel cm = new cityModel();
//		cm.setName("ctc");
//		cm.setTemperature("20%");
//		cityModel cm1 = new cityModel();
//		cm.setName("blr");
//		cm.setTemperature("30%");
//		cityModel cm2 = new cityModel();
//		cm.setName("bbsr");
//		cm.setTemperature("10%");
//		List<cityModel> cities = new ArrayList<cityModel>();
//		cities.add(cm);
//		cities.add(cm1);
//		cities.add(cm2);
//		
//		
//		pojoClass obj = new pojoClass("automation","tester");
//		pojoClass obj2 = new pojoClass("automation","developer");
//		//pojoClass obj = new pojoClass("automation","tester",ctc,cities);
////		pojoClass obj1 = new pojoClass();
////       obj1.setJob("tester");
////       obj1.setName("automation");
////       obj1.setCity(city);
////       obj1.setCitymodels(cities);
//		Response resp = given().headers("Content-Type","application/json").
//				body(obj).
//				when().post("https://reqres.in/api/users");
////		Response resp1 = given().headers("Content-Type","application/json").
////				body(obj2).
////				when().post("https://reqres.in/api/users");
//		assertEquals(resp.getStatusCode(),201);
//		System.out.println("The status code for post method is : " + resp.getStatusCode() );
//		System.out.println("The response body of the post method is : " + resp.getBody().asString());
//		assertEquals(resp.path("job"),job);
//		//assertEquals(resp1.path("job"),"developer");
//		assertEquals(resp.path("name"),name);
//	}
////   @Test(groups="SanitySuite")
////	public void getCircuitsPathParam() {
////	   ExtentReport.extentlog = ExtentReport.extentreport.startTest("TC-001:getHeader",
////				"Validate the status code for GET request");
////		Response resp = given().pathParam("circuitSeason", "2017")
////				        .when().get("http://ergast.com/api/f1/{circuitSeason}/circuits.json");
////		System.out.println(resp.getBody().asString());
////		System.out.println("STATUS-CODE: "+resp.getStatusCode());
////		assertEquals(resp.getStatusCode(),200);
////	}
////	@Test
////	public void getCircuitsPathParamBadRequest() throws IOException, ParseException, org.json.simple.parser.ParseException {
////		
////		Response resp = given().queryParam("circuitSeason", "202029292")
////				        .when().get(ReadTestData.getTestData("uri"));
////		System.out.println(resp.getBody().asString());
////		System.out.println("STATUS-CODE: "+resp.getStatusCode());
////		assertEquals(resp.getStatusCode(),400);
////	}
////	
////	@Test(groups="SmokeSuite")
////	public void getformParam() {
////		
////		Response resp = given().formParam("Username", "TestUser1","Password","pwd123")
////				        .when().get("http://automationReinvented/login");
////		
////		System.out.println(resp.getBody().asString());
////		System.out.println("STATUS-CODE: "+resp.getStatusCode());
////		assertEquals(resp.getStatusCode(),200);
////	}
////	
//	@Test(description="TC-001:Validate the status code for GET request", groups= {"SmokeSuite","UserComponent"},enabled=true)
//	public void getHeadercookies() {
//		ExtentReport.extentlog = ExtentReport.extentreport.startTest("TC-001:getHeader",
//				"Validate the status code for GET request");
//		Response resp = given().header("Content-Type", "application/json")
//				        .when().get("https://reqres.in/api/users");
//		System.out.println(resp.getBody().asString());
//		System.out.println("STATUS-CODE: "+resp.getStatusCode());
//		assertEquals(resp.getStatusCode(),200);
//		//assertEquals(resp.getHeader("Content-Type"),"application/json; charset=utf-8");
//		System.out.println("The value for header Content type is :" + resp.getHeader("Content-Type"));
//		System.out.println(resp.getCookie("__cfduid"));
//		
//		Map<String,String> hmap=resp.getCookies();
//		
//		
//		for (Entry<String, String> eset:hmap.entrySet()) {
//			
//			System.out.println(eset.getKey().toString());
//			if (eset.getKey().contentEquals("__cfduid")) {
//			System.out.println("The value for cookie with Key as __cfduid is : " + eset.getValue().toString());}
//		}
//
//		 Headers headersCollection = resp.headers();
//		 //List<String> lst = 
//		 String[] arr= {"Date","Content-Type"};
//		 // Iterate over all the Headers
//		 for(Header header : headersCollection)
//		 {
//		 //System.out.println("Key is: " + header.getName() + " Value is: " + header.getValue());
//		 if (header.getName().contentEquals(arr[0])) {
//			 System.out.println("Header Date is present in the api response headers and the value is: "+ header.getValue());
//			
//		  }
//		  if (header.getValue().contentEquals("chunked")) {
//			 System.out.println("Header name for value chunked is: " + header.getName());
//		  }
//		 }
//	}
//	
//	@Test
//	public static void postauto() throws IOException {
//		File newfile = new File("C:\\Users\\gs-2181\\eclipse-workspace\\apiAutomationeleven\\Resources\\TestData\\requestBody.json");
//		FileInputStream fis = new FileInputStream(new File("C:\\Users\\gs-2181\\eclipse-workspace\\apiAutomationeleven\\Resources\\TestData\\requestBody.json"));
//		Response resp = given().header("Content-Type","application/json")
//				.body(IOUtils.toString(fis)).when().post("https://reqres.in/api/users");
//		pojoTest pjt = new pojoTest("sdet","job");
////		pjt.setJob("sdet");
////		pjt.setName("james");
//		pojoClass pjc = new pojoClass("sde3","steve");
//		Response resp2 = given().header("Content-Type","application/json")
//				.body(pjt).when().patch("https://reqres.in/api/users");
//		Response resp3 = given().header("Content-Type","application/json")
//				.body(pjc).when().put("https://reqres.in/api/users");
//	}



}
