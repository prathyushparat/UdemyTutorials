package com.wibmo.KYC.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.wibmo.API.LoadMoneyAPI;
import com.wibmo.API.LoginAPI;
import com.wibmo.API.RegisterOfflineAPI;

import io.restassured.response.Response;
import library.BaseClass;
import library.DB;
import library.ExcelLibrary;
import library.Generic;

/***
 * KYC_002 Verify load money to newly registered non-KYC account
 * @author Prathyush
 * ; to be completed
 */
public class KYC_002 extends BaseClass{
	
	String testCaseID="KYC_002";
	String sheetName="KYC";
	String testCaseId=ExcelLibrary.getTestCase(testCaseID , sheetName);
	String testData=ExcelLibrary.getTestData(testCaseID, sheetName);
	String mobileNo=null;
	@Test
	public void KYC_002(){
		mobileNo=testData.split(",")[0];
		String pin=testData.split(",")[1];
		String txnAmt=testData.split(",")[2];
		RegisterOfflineAPI ro= new RegisterOfflineAPI();
		Response offlineRegRes=ro.offlineRegistration(mobileNo);
		//String regStatus=Generic.getJsonPathValue(offlineRegRes, "registered");
		//Assert.assertEquals("true", regStatus);
		
		LoginAPI lgn = new LoginAPI();
		List<Response> loginResponse = lgn.Login(mobileNo, pin);
		LoadMoneyAPI lm = new LoadMoneyAPI();
		List<Response>loadMoneyResponse = lm.loadMoney(txnAmt, loginResponse);
		//System.out.println("Load money response is "+loadMoneyResponse.get(0).asString());
		loadMoneyResponse.get(0).then().assertThat().statusCode(403); //403 is the response from server refusing to honor the request. hence asserting on 403 is the validation.
	}
	
	@AfterTest
	public void deleteUser() {
		DB.deleteUser(mobileNo);
	}
	
	

}
