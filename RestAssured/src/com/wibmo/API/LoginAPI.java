package com.wibmo.API;

import org.apache.logging.log4j.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import library.Log;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import library.Generic;
import library.ELog;


import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;



public class LoginAPI {
	private static Logger log = LogManager.getLogger();
	JsonPath js;
	String salt , DC , pwd, tempToken,userId,otp,pcAcNumber, userAuthToken,userID=null;
	/***
	 * 
	 * This method individually calls several sub APIs which is required for login
	 * This method is improved to send Response List that contains responses of sub APIs
	 */
	public List<Response> Login(String mob , String pin) {
		List<Response> listOfLoginSubApis= new ArrayList<Response>();
		Response getSaltResponse = getSalt(mob);
		ELog.info("The salt response is "+getSaltResponse.asString());
		listOfLoginSubApis.add(getSaltResponse);
		DC = Generic.getJsonPathValue(getSaltResponse, "clusterInfo.urls.api");
		//DC = getSaltResponse.jsonPath().getString("clusterInfo.urls.api");
		ELog.info("The data centre is "+DC);
		salt = Generic.getJsonPathValue(getSaltResponse,"salt");
		ELog.info("The salt for "+mob+" is "+salt);
		Response generatePasswordResponse = generatePassword(pin, salt);
		ELog.info("The generate encrypted pin response is "+generatePasswordResponse.asString());
		listOfLoginSubApis.add(generatePasswordResponse);
		pwd=Generic.getJsonPathValue(generatePasswordResponse ,"pwd");
		ELog.info("The encrypted PIN is "+pwd);
		Response startLoginResponse = startLogin(mob , pwd , salt , DC);
		Log.info("The start login response is "+startLoginResponse.asString());
		listOfLoginSubApis.add(startLoginResponse);

		tempToken=Generic.getJsonPathValue(startLoginResponse ,"tempToken");
		ELog.info("The temp token for "+mob+" logging in is "+tempToken);

		userId=Generic.getJsonPathValue(startLoginResponse ,"userId");
		ELog.info("The userId of "+mob+" is "+userId);

		otp=getOtp(mob, 6019, 5);
		ELog.info("The DVC for "+mob+" logging in is "+otp);
		Response completeLoginResponse = completeLogin(mob, DC, otp, userId, tempToken);
		listOfLoginSubApis.add(completeLoginResponse);

		
		return listOfLoginSubApis;
	}
	
	

	static String response;
	
	/***
	 * Identifies which data center the request should be served from.
	 * Comes from the getSalt response
	 */
	public  Response getSalt(String mobileNo) {
		RestAssured.baseURI = Generic.getPropetyValues("RESTDOMAIN");//"https://api.pc.enstage-sas.com";
		ELog.info("=== Starting getSalt ===");
		ELog.info(RestAssured.baseURI);
		Response res=null;
		//try {
		res = given()
						.param("username", mobileNo)
						.log().all()
						.when()
						.post("/in/user/auth/6019/salt");

		response = res.asString();
		js = new JsonPath(response);
		//salt = js.getString("salt");
		//DC = js.getString("clusterInfo.urls.api");
		/*System.out.println(salt);
		System.out.println("=== Salt is "+salt+" ===");*/
		ELog.info("Salt of "+mobileNo+" is "+Generic.getJsonPathValue(res, "salt"));
		//Assert.assertNotNull(Generic.getJsonPathValue(res, "salt"), "Didn't get salt value");
		
		/*}
		catch(Exception e) {
			Assert.fail("Unable to get salt "+e.getMessage());
		}*/
		return(res);
	}

	
	/***
	 * Method to create password using salt.
	 * @param String pin String salt
	 */
	public Response generatePassword(String pin , String salt)
	{
		RestAssured.baseURI = Generic.getPropetyValues("WALLETDOMAIN");
		Response res=null;
		//try {
		 res = given()
					   .param("password", pin)
					   .param("key", salt)
					   .log().all()
					   .when()
					   .post("/sampleMerchant/passwordEncrypt.jsp");
		response=res.asString();
		//System.out.println(response);
		js = new JsonPath(response);
		pwd = js.getString("pwd");
		Log.info(response);
		Assert.assertNotNull(pwd, "Didn't receive desired encrypted password");
		//System.out.println("=== The encryp password is "+pwd+" ===");
		/*}
		catch(Exception e) {
			Assert.fail("Unable to generate password "+e.getMessage());
		}*/
		return res;
				
	}
	
	/***
	 * This method mocks the entering pin & clicking on submit
	 * @param mobileNo
	 * @return startLogin json response
	 */
	public Response startLogin(String mobileNo , String pwd , String salt , String DC) {
		RestAssured.baseURI=DC;
		Response res=null;
		//try {
			
		
			res = given()
						.header("Content-Type", "application/json")
						.log().all()
						.body("{"+  
								   "\"clientInfo\":{"+  
								      "\"clientDeviceName\":\"AndriodApp @ motorola XT1225 - Vodafone IN\","+
								      "\"clientMaker\":\"motorola\","+
								      "\"clientModel\":\"XT1225\","+
								      "\"clientOsName\":\"Android\","+
								      "\"clientOsVersion\":\"5.0.2\","+
								      "\"clientDeviceType\":3"+
								   "},"+
								   "\"gpsInfo\":{"+  
								      "\"accuracy\":0.0,"+
								      "\"gpsTime\":0,"+
								      "\"latitude\":0.0,"+
								      "\"longitude\":0.0"+
								   "},"+
								   "\"password\":\""+pwd+"\","+
								   "\"salt\":\""+salt+"\","+
								   "\"simAndDeviceData\":{"+  
								      "\"deviceData1\":\"zoklBWqgx5agU+s5I7mDMiCFqIKbHgszRuUuJAQ8CXs=\","+
								      "\"deviceData2\":\"DES+n3lIlX2wc6HyTCZrB1CLEn7AMLMoJprQUtchOqc=\","+
								      "\"deviceData3\":\"NeYujrnWV1k3N+dm11rz4OdhI0fSU7T3ejJkgqjsSf8=\","+
								      "\"deviceData4\":\"3uZIZQX5wO9c0ZG+UsyKRYDUri+KHA+zPh5go4yrp4M=\","+
								      "\"deviceId\":\"tdid:62Zm0zrjP80kOILv2YEE7xthhp1UhjWFpNbzm3PhcT4=:4900\","+
								      "\"deviceIdProof\":\"UFV4wtZ/gAZ1cQq0j9gzFgi47T1M/NUhPxTNTt5fFgyCV56clBFY1IDkv6tT6JcQGjcpR/Ij+54FO9Wde47IAQ==\","+
								      "\"simId\":\"FL41kzjvztwh6TzyugVKRiLrFUEfFQrE+LTZgEa5wB0=:4555\","+
								      "\"simOperator\":\"Vodafone IN\","+
								      "\"deviceIdType\":3"+
								   "},"+
								   "\"username\":\""+mobileNo+"\","+
								   "\"triggerDvcIfReq\":false,"+
								   "\"appVersion\":3060202"+
								"}")
						.when()
						.post("/in/user/auth/6019/login");
			//Assert.assertNotNull(Generic.getJsonPathValue(res ,"tempToken"), "Didn't receive tempToken");
		/*}
		catch(Exception e) {
			Assert.fail("Unable to start loging");
		}*/
		/*String response=res.asString();
		System.out.println(response);*/
		
		return res;
	}
	
	/***
	 * Method to get OTP for a particular event
	 * @param mobileNo
	 * @param pgmId
	 * @param eventId
	 */
	public  String getOtp(String mobileNo , int pgmId , int eventId) {
		RestAssured.baseURI=Generic.getPropetyValues("WALLETDOMAIN");
		Response res=null;
		try {
		res = given().param("accessData", mobileNo)
				.log().all()
				.param("programId", "6019").param("eventId", "5").when()
				.post("/sampleMerchant/getOtp.jsp");
		otp = res.asString().split("\n")[1];
		Log.info("OTP is "+otp);
		Assert.assertNotNull(otp, "OTP is null");
		System.out.println("=== DVC is "+otp+" ===");
		}
		catch(Exception e) {
			Assert.fail("Unable to get otp "+e.getMessage());
		}
		return otp;
						
	}
	
	/***
	 * Method to complete login with DVC
	 * @param mobileNo
	 * @return
	 */
	public Response completeLogin(String mobileNo , String DC , String otp , String userId , String tempToken) {

		RestAssured.baseURI=DC;
		Response res=null;
		//try {
			
		
		 res = given()
						.header("Content-Type","application/json")
						.log().all()
						.body("{"+  
										   "\"clientInfo\":{"+  
										      "\"clientDeviceName\":\"AndriodApp @ motorola XT1225 - Vodafone IN\","+
										      "\"clientMaker\":\"motorola\","+
										      "\"clientModel\":\"XT1225\","+
										      "\"clientOsName\":\"Android\","+
										      "\"clientOsVersion\":\"5.0.2\","+
										      "\"clientDeviceType\":3"+
										   "},"+
										   "\"dvc\":\""+otp+"\","+
										   "\"gpsInfo\":{"+  
										      "\"accuracy\":0.0,"+
										      "\"gpsTime\":0,"+
										      "\"latitude\":0.0,"+
										      "\"longitude\":0.0"+
										   "},"+
										   "\"simAndDeviceData\":{"+  
										      "\"deviceData1\":\"zoklBWqgx5agU+s5I7mDMiCFqIKbHgszRuUuJAQ8CXs=\","+
										      "\"deviceData2\":\"DES+n3lIlX2wc6HyTCZrB1CLEn7AMLMoJprQUtchOqc=\","+
										      "\"deviceData3\":\"NeYujrnWV1k3N+dm11rz4OdhI0fSU7T3ejJkgqjsSf8=\","+
										      "\"deviceData4\":\"3uZIZQX5wO9c0ZG+UsyKRYDUri+KHA+zPh5go4yrp4M=\","+
										      "\"deviceId\":\"tdid:62Zm0zrjP80kOILv2YEE7xthhp1UhjWFpNbzm3PhcT4=:4900\","+
										      "\"simId\":\"FL41kzjvztwh6TzyugVKRiLrFUEfFQrE+LTZgEa5wB0=:4555\","+
										      "\"simOperator\":\"Vodafone IN\","+
										      "\"deviceIdType\":3"+
										   "},"+
										   "\"tempToken\":\""+tempToken+"\","+
										   "\"userId\":\""+userId+"\","+
										   "\"username\":\""+mobileNo+"\","+
										   "\"trustThisDevice\":true"+
										"}")
						.when()
						.post("/in/user/auth/6019/completeLoginFromNewDevice");
		
		response=res.asString();
		Log.info("The complete login response : "+response);
		//Assert.assertTrue(Generic.getJsonPathValue(res, "resDesc").equals("SUCCESS"), "Complete login didn't complete successfully");
		System.out.println(response);
		/*}
		catch(Exception e) {
			Assert.fail("=== Login failed ==="+e.getMessage());
		}*/
		/*js=new JsonPath(response);
		pcAcNumber=js.getString("userBankAc[0].pcAccNumber");
		String resDesc=js.getString("resDesc");
		userAuthToken = js.getString("userToken");
		userID=js.getString("userId");*/
		return res;
	}
	
	/***
	 * Method to logout
	 * @param mobileNo
	 */
	public Response logoutAPI(String mobileNo , String DC , String userAuthToken , String userId) {

		RestAssured.baseURI=DC;
		Log.info("DC for logging out : "+DC);
		Response res=null;
		//try {
			res = given().header("X-Auth-Token", userAuthToken).header("X-Auth-UID", userId).log().all()
					.param("username", mobileNo)
					.param("deviceId", "tdid%3A62Zm0zrjP80kOILv2YEE7xthhp1UhjWFpNbzm3PhcT4%3D%3A4900")
					.param("deviceIdType", "3").when().post("/in/user/auth/6019/logout");
			Log.info("Logout response : " + res.asString());
			/*Assert.assertTrue((Generic.getJsonPathValue(res, "status").equals("true")), "Logout API response not as desired");
		}
		catch(Exception e) {
			Assert.fail(mobileNo+" logout failed "+e.getMessage());
		}*/
		
		return res;
		
	}
	
	public Response Logout(String mob , List<Response> loginResponse) {
		//DC = getSaltResponse.jsonPath().getString("clusterInfo.urls.api");
		ELog.info("Logging "+mob+" out");
		//Response getSaltResponse = getSalt(mob);
		userId = Generic.getJsonPathValue(loginResponse.get(3), "userId");
		//userId = loginResponse.jsonPath().getString("userId");
		Log.info("user ID to logout :"+userId);
		userAuthToken=Generic.getJsonPathValue(loginResponse.get(3), "userToken");
		Log.info("userAuthToken to logout :"+userAuthToken);

		Response logoutResponse = logoutAPI(mob, DC , userAuthToken , userId);
		
		return logoutResponse;
	}
	
	

}
