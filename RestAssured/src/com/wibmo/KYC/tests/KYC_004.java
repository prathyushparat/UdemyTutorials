package com.wibmo.KYC.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.wibmo.API.LoginAPI;
import com.wibmo.API.SendMoneyAPI;

import io.restassured.response.Response;
import library.BaseClass;
import library.ExcelLibrary;
import library.Generic;

/***
 * KYC_004 Verify send money from a min-KYC account
 * @author Prathyush
 *
 */
public class KYC_004 extends BaseClass{
	String testCaseID="KYC_004";
	String sheetName="KYC";
	String testCaseId=ExcelLibrary.getTestCase(testCaseID , sheetName);
	String testData=ExcelLibrary.getTestData(testCaseID, sheetName);
	
	@Test
	public void KYC_004() {
		String mobileNo=testData.split(",")[0];
		String pin=testData.split(",")[1];
		String txnAmt=testData.split(",")[2];
		String receiverMob = testData.split(",")[3];
		
		LoginAPI lgn = new LoginAPI();
		List<Response> loginResponse = lgn.Login(mobileNo, pin);
		SendMoneyAPI sm = new SendMoneyAPI();
		Response sendMoneyResponse= sm.sendMoneyAPI(txnAmt,loginResponse, receiverMob);
		sendMoneyResponse.then().assertThat().statusCode(403); //403 is the response from server refusing to honor the request. hence asserting on 403 is the validation.
		//String sendMoneyStatusMessage = Generic.getJsonPathValue(sendMoneyResponse , "displayMessages[\"Error Description\"]");
		//Assert.assertEquals(sendMoneyStatusMessage, "Recepient is non-kyc user");
		lgn.Logout(mobileNo, loginResponse);
		
	
	}
	
	

}
