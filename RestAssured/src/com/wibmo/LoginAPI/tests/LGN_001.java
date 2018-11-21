package com.wibmo.LoginAPI.tests;

import java.util.List;

import org.apache.logging.log4j.*;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.wibmo.API.LoginAPI;

import io.restassured.response.Response;
import library.BaseClass;
import library.ELog;
import library.ExcelLibrary;
import library.Generic;

/***
 * Verify login with valid mob # & PIN
 * @author Prathyush
 *
 */
public class LGN_001 extends BaseClass {
	
	
	
	String testCaseID="LGN_001";
	String sheetName="Login";
	String testCaseId=ExcelLibrary.getTestCase(testCaseID , sheetName);
	String testData=ExcelLibrary.getTestData(testCaseID, sheetName);
	
	@Test
	public void LGN_001()
	{
		String mobileNo=testData.split(",")[0];
		String pin=testData.split(",")[1];
		LoginAPI lgn = new LoginAPI();
		
		ELog.info("Started");
		
		List<Response> loginResponse=lgn.Login(mobileNo, pin); //Response list from the LoginAPI class

		String loginStatus = Generic.getJsonPathValue(loginResponse.get(3), "resDesc"); //extracting the resDesc from 3rd index which is has completeLogin Response
		Assert.assertEquals(loginStatus, "SUCCESS");
		lgn.Logout(mobileNo, loginResponse);
		
	}

}
