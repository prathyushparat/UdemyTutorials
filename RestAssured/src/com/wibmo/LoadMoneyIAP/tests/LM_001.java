package com.wibmo.LoadMoneyIAP.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.wibmo.API.LoadMoneyAPI;
import com.wibmo.API.LoginAPI;

import io.restassured.response.Response;
import library.BaseClass;
import library.ExcelLibrary;

/***
 * Verify successful load money using corp card (visa card)
 * @author Prathyush
 *
 */

public class LM_001 extends BaseClass{
	
	String testCaseID="LM_001";
	String sheetName="LoadMoney";
	String testCase=ExcelLibrary.getTestCase(testCaseID , sheetName);
	String testData=ExcelLibrary.getTestData(testCaseID, sheetName);
	
	@Test
	public void LM_001() {
		String mobileNo=testData.split(",")[0];
		String pin=testData.split(",")[1];
		String txnAmt=testData.split(",")[2];
		LoginAPI lgn = new LoginAPI();
		List<Response> loginResponse = lgn.Login(mobileNo, pin);
		LoadMoneyAPI lm = new LoadMoneyAPI();
		List<Response>loadMoneyResponse = lm.loadMoney(txnAmt, loginResponse);
		String loadStatusMessage = loadMoneyResponse.get(3).jsonPath().getString("addFundDetails.headerMessage");
		Assert.assertEquals(loadStatusMessage ,"Funds have been loaded successfully"); 
		lgn.Logout(mobileNo, loginResponse);

	}

}
