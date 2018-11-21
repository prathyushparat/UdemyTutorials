package com.wibmo.LoginAPI.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.wibmo.API.LoginAPI;

import io.restassured.response.Response;
import library.BaseClass;
import library.ExcelLibrary;
import library.Generic;

public class LGN_002 extends BaseClass{
	
	/***
	 * Verify login using an invalid PIN to login
	 * 
	 */
	
	String testCaseID="LGN_002";
	String sheetName="Login";
	String testCase=ExcelLibrary.getTestCase(testCaseID , sheetName);
	String testData=ExcelLibrary.getTestData(testCaseID, sheetName);
	

	
	
	@Test
	public void LGN_002() {
		String mobileNo=testData.split(",")[0];
		String pin=testData.split(",")[1];
		LoginAPI lgn = new LoginAPI();
		List<Response> loginResponse=lgn.Login(mobileNo, pin);

		String loginStatus = Generic.getJsonPathValue(loginResponse.get(2), "resDesc");//extracting the resDesc from 2nd index which is has completeLogin Response
		Assert.assertEquals(loginStatus, "BAD_PWD");
		
		
		
		//Assert.assertEquals(startLoginResponse.jsonPath().getString("resDesc"), "BAD_PWD");
		/*LoginAPI.getOtp(mobileNo, 6019, 5);
		LoginAPI.completeLogin(mobileNo);*/
	
	}
}
