package com.wibmo.KYC.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.wibmo.API.KYCAPI;
import com.wibmo.API.LoadMoneyAPI;
import com.wibmo.API.LoginAPI;
import com.wibmo.API.RegisterOfflineAPI;

import io.restassured.response.Response;
import library.BaseClass;
import library.DB;
import library.ExcelLibrary;
import library.Generic;

/***
 * KYC_003 Verify load money to newly registered min-KYC account
 * @author Prathyush
 * ; to be completed
 */
public class KYC_003 extends BaseClass{
	
	String testCaseID="KYC_003";
	String sheetName="KYC";
	String testCaseId=ExcelLibrary.getTestCase(testCaseID , sheetName);
	String testData=ExcelLibrary.getTestData(testCaseID, sheetName);
	String mobileNo=null;
	@Test
	public void KYC_003(){
		mobileNo=testData.split(",")[0];
		String pin=testData.split(",")[1];
		String txnAmt=testData.split(",")[2];
		RegisterOfflineAPI ro= new RegisterOfflineAPI();
		Response offlineRegRes=ro.offlineRegistration(mobileNo);
		KYCAPI kyc = new KYCAPI();
		kyc.updateToMinKYC(mobileNo);
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//String regStatus=Generic.getJsonPathValue(offlineRegRes, "registered");
		//Assert.assertEquals("true", regStatus);
		
		LoginAPI lgn = new LoginAPI();
		List<Response> loginResponse = lgn.Login(mobileNo, pin);
		LoadMoneyAPI lm = new LoadMoneyAPI();
		List<Response>loadMoneyResponse = lm.loadMoney(txnAmt, loginResponse);
		String loadStatusMessage = Generic.getJsonPathValue(loadMoneyResponse.get(3), "addFundDetails.headerMessage");

		Assert.assertEquals(loadStatusMessage ,"Funds have been loaded successfully"); 

	}
	
	
	
	@AfterTest
	public void deleteUser() {
		DB.deleteUser(mobileNo);
	}
	
	

}
