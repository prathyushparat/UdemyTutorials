package com.wibmo.SendMoneyAPI.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.wibmo.API.LoadMoneyAPI;
import com.wibmo.API.LoginAPI;
import com.wibmo.API.SendMoneyAPI;

import io.restassured.response.Response;
import library.BaseClass;
import library.ExcelLibrary;
import library.Generic;

/***
 * To verify successful send money
 * @author Prathyush
 *
 */
public class SM_001 extends BaseClass{
	String testCaseID="SM_001";
	String sheetName="SendMoney";
	String testCase=ExcelLibrary.getTestCase(testCaseID , sheetName);
	String testData=ExcelLibrary.getTestData(testCaseID, sheetName);
	
	@Test
	public void SM_001() {
		String mobileNo=testData.split(",")[0];
		String pin=testData.split(",")[1];
		String txnAmt=testData.split(",")[2];
		String receiverMob = testData.split(",")[3];
		
		LoginAPI lgn = new LoginAPI();
		List<Response> loginResponse = lgn.Login(mobileNo, pin);
		SendMoneyAPI sm = new SendMoneyAPI();
		Response sendMoneyResponse= sm.sendMoneyAPI(txnAmt,loginResponse, receiverMob);
		String sendMoneyStatusMessage = Generic.getJsonPathValue(sendMoneyResponse ,"fundTransferDetails.headerMessage");
		Assert.assertEquals("Funds have been sent successfully", sendMoneyStatusMessage);
		lgn.Logout(mobileNo, loginResponse);
		
	}

}
