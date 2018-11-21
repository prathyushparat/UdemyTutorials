package com.wibmo.IAPV2.tests;

import java.util.List;

import org.codehaus.groovy.runtime.dgmimpl.arrays.IntegerArrayPutAtMetaMethod;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.wibmo.API.IapAPI;
import com.wibmo.API.LoginAPI;

import io.restassured.response.Response;
import library.BaseClass;
import library.ExcelLibrary;

/***
 * Verify IAP V2 for successful charge 
 * @author Prathyush
 *
 */
public class IAPV2_001 extends BaseClass{
	
	String testCaseID="IAPV2_001";
	String sheetName="IAPV2";
	String testCase=ExcelLibrary.getTestCase(testCaseID , sheetName);
	String testData=ExcelLibrary.getTestData(testCaseID, sheetName);
	
	@Test
	public void IAPV2_001() {
		String mobileNo=testData.split(",")[0];
		String pin=testData.split(",")[1];
		String txnAmt=testData.split(",")[2];
		LoginAPI lgn = new LoginAPI();
		IapAPI iap = new IapAPI();

		//Response loginResponse=lgn.Login(mobileNo, pin);
		List<Response> loginResponse = lgn.Login(mobileNo, pin);
		List<Response> IAPResponse = iap.IapAPI(txnAmt, loginResponse);
		String pgStatusCode = IAPResponse.get(5).jsonPath().getString("data.pgStatusCode");
		String resCode = IAPResponse.get(5).jsonPath().getString("resCode");
		String authenticationSuccessful = IAPResponse.get(5).jsonPath().getString("authenticationSuccessful");
		String chargeSuccessful = IAPResponse.get(5).jsonPath().getString("chargeSuccessful");
		/*
		 * Below values should be verified as part of charge response
		 */
		Assert.assertEquals(pgStatusCode, "50020");
		Assert.assertEquals(resCode, "000");
		Assert.assertEquals(authenticationSuccessful, "true");
		Assert.assertEquals(chargeSuccessful, "true");
		lgn.Logout(mobileNo, loginResponse);
	}

}
