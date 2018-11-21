package com.wibmo.KYC.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.wibmo.API.LoginAPI;
import com.wibmo.API.SendMoneyAPI;

import io.restassured.response.Response;
import library.BaseClass;
import library.ExcelLibrary;
import library.Generic;

/***
 * KYC_001 Verify send money to no KYC no wallet a/c
 * @author Prathyush
 *
 */
public class KYC_001 extends BaseClass{
	String testCaseID="KYC_001";
	String sheetName="KYC";
	String testCaseId=ExcelLibrary.getTestCase(testCaseID , sheetName);
	String testData=ExcelLibrary.getTestData(testCaseID, sheetName);
	
	@Test
	public void KYC_001() {
		String mobileNo=testData.split(",")[0];
		String pin=testData.split(",")[1];
		String txnAmt=testData.split(",")[2];
		String receiverMob = testData.split(",")[3];
		
		LoginAPI lgn = new LoginAPI();
		List<Response> loginResponse = lgn.Login(mobileNo, pin);
		SendMoneyAPI sm = new SendMoneyAPI();
		Response sendMoneyResponse= sm.sendMoneyAPI(txnAmt,loginResponse, receiverMob);
		String sendMoneyStatusMessage = Generic.getJsonPathValue(sendMoneyResponse , "displayMessages[\"Error Description\"]");
		Assert.assertEquals(sendMoneyStatusMessage, "Recepient is non-kyc user");
		lgn.Logout(mobileNo, loginResponse);
		
	
	}

}
