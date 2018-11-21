package com.wibmo.IAPV2.tests;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.wibmo.API.IapAPI;
import com.wibmo.API.LoginAPI;

import io.restassured.response.Response;
import library.BaseClass;
import library.ExcelLibrary;

/***
 * Verify IAP v2 charge with txn ID of a different txn & message hash of a different txn
 */

public class IAPV2_003 extends BaseClass{
	
	String testCaseID="IAPV2_003";
	String sheetName="IAPV2";
	String testCase=ExcelLibrary.getTestCase(testCaseID , sheetName);
	String testData=ExcelLibrary.getTestData(testCaseID, sheetName);

	@Test
	public  void IAPV2_003() {
		String mobileNo = testData.split(",")[0];
		String pin = testData.split(",")[1];
		String txnAmt = testData.split(",")[2];
		LoginAPI lgn = new LoginAPI();
		IapAPI iap = new IapAPI();
		
		List<Response> loginResponse = lgn.Login(mobileNo, pin);
		String DC = loginResponse.get(0).jsonPath().getString("clusterInfo.urls.api");
		String userId = loginResponse.get(3).jsonPath().getString("userId");
		String userAuthToken = loginResponse.get(3).jsonPath().getString("userToken");
		String pcAcNumber = loginResponse.get(3).jsonPath().getString("userBankAc[0].pcAccNumber");

		List<Response> IAPResponseTxn1 = iap.IapAPI(txnAmt, loginResponse);
		String merTxnId1=IAPResponseTxn1.get(0).jsonPath().getString("merTxnId");
		String wibmoTxnId1 = IAPResponseTxn1.get(1).jsonPath().getString("wibmoTxnId");
		String pgStatusCode = IAPResponseTxn1.get(5).jsonPath().getString("data.pgStatusCode");
		String resCode = IAPResponseTxn1.get(5).jsonPath().getString("resCode");
		String authenticationSuccessful = IAPResponseTxn1.get(5).jsonPath().getString("authenticationSuccessful");
		String chargeSuccessful = IAPResponseTxn1.get(5).jsonPath().getString("chargeSuccessful");
		/*
		 * Below values should be verified as part of charge response
		 */
		Assert.assertEquals(pgStatusCode, "50020");
		Assert.assertEquals(resCode, "000");
		Assert.assertEquals(authenticationSuccessful, "true");
		Assert.assertEquals(chargeSuccessful, "true");
		
		Response messageHashForInitResponseTxn2 = iap.messageHashForInit(txnAmt);
		String merTxnId2 = messageHashForInitResponseTxn2.jsonPath().getString("merTxnId");
		String initMsgHashTxn2 = messageHashForInitResponseTxn2.jsonPath().getString("msgHash");
		Response initResponse = iap.init(DC, txnAmt, userId, userAuthToken, merTxnId2 , initMsgHashTxn2);
		String wibmoTxnId2 = initResponse.jsonPath().getString("wibmoTxnId");
		String wibmoTxnToken2=initResponse.jsonPath().getString("wibmoTxnToken");

		//extracting relevant values from process response
		iap.process( DC, userId, pcAcNumber, userAuthToken, wibmoTxnId2, wibmoTxnToken2);
		iap.mock3dsRes(DC, userId, userAuthToken, txnAmt, pcAcNumber , wibmoTxnId2 , wibmoTxnToken2);
		Response messageHashForChargeResponseTxn2 = iap.messageHashForCharge(txnAmt, wibmoTxnId2, merTxnId2);
		String chargeMsgHashTxn2=messageHashForChargeResponseTxn2.jsonPath().getString("msgHash");
		
		Response chargeResponseTxn2 = iap.charge(DC, txnAmt, chargeMsgHashTxn2, wibmoTxnId1, merTxnId1); 
		String resCodeTxn2 = chargeResponseTxn2.jsonPath().getString("resCode");
		Assert.assertEquals(resCodeTxn2, "070");
	}

}
