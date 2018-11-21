package com.wibmo.LoginAPI.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.wibmo.API.LoginAPI;

import io.restassured.response.Response;
import library.BaseClass;
import library.ExcelLibrary;
import library.Generic;

public class LGN_003 extends BaseClass{
	
	/***
	 * Verify login using a blank PIN to login
	 * 
	 */
	String testCaseID="LGN_003";
	String sheetName="Login";
	String testCase=ExcelLibrary.getTestCase(testCaseID , sheetName);
	String testData=ExcelLibrary.getTestData(testCaseID, sheetName);
	
	@Test
	public  void LGN_003() {
		String mobileNo=testData.split(",")[0];
		String pin=testData.split(",")[1];
		LoginAPI lgn = new LoginAPI();
		List<Response> loginResponse = lgn.Login(mobileNo, pin);
		String loginStatus = Generic.getJsonPathValue(loginResponse.get(2), "resDesc");
		Assert.assertEquals(loginStatus, "BAD_PWD");
	}
}
