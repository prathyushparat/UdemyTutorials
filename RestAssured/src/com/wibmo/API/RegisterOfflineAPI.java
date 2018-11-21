package com.wibmo.API;

import static io.restassured.RestAssured.given;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import library.ELog;
import library.Generic;
import library.Log;

public class RegisterOfflineAPI {
	
	public Response offlineRegistration(String mobNumber) {
		Response response=offlineReg(mobNumber);
		return response;
	}
	
	
	 public Response offlineReg(String mobNo) {
		RestAssured.baseURI=Generic.getPropetyValues("RESTDOMAIN");
		Response res = null;
		String response = null;
		ELog.info("=====Registering "+mobNo+" from API =====");
		try {
			res = given().header("Content-Type", "application/json").log().all().header("X-API-KEY", "testing")
					.body("{" + 
							"\"firstName\" : \"Prathyush\"," + 
							"\"lastName\" : \"Parat\"," + 
							"\"mobile\" : \"" + mobNo+ "\"," + 
							"\"dateOfBirthYYYYMMDD\" : \"19861123\"," + 
							"\"gender\" : \"M\","+
							"\"pin\" : \"MTExMQ==\"," + 
							"\"pinType\" : \"0\"," + 
							"\"linkedCards\" : [ " + 
							"]" + 
						 "}")
					.when().post("/in/user/reg/6019/trustedsrc");
			response = res.asString();
			Log.info("Offline reg response : "+response);
			
			Assert.assertTrue(response.contains("true"),"Invalid response : "+response);
			
			ELog.info(mobNo+" Registration complete \n");
			
			if(response.contains("already"))
				Assert.fail(mobNo+" already registered "+response);
	
		} catch (Exception e) {
			
			Assert.fail("===== " + mobNo + response+"... registration failed =====");
		}
		
		return res;
	
	 }
	

}
