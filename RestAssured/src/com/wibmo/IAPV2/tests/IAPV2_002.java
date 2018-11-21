package com.wibmo.IAPV2.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.wibmo.API.IapAPI;
import com.wibmo.API.LoginAPI;

import io.restassured.response.Response;
import library.BaseClass;
import library.ExcelLibrary;
import library.Generic;

/***
 * Verify IAP V2 by performing charge without completing authentication
 * @author Prathyush
 *
 */
public class IAPV2_002 extends BaseClass{

	
	String testCaseID="IAPV2_002";
	String sheetName="IAPV2";
	String testCase=ExcelLibrary.getTestCase(testCaseID , sheetName);
	String testData=ExcelLibrary.getTestData(testCaseID, sheetName);

	@Test
	public  void IAPV2_002() {
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

		Response messageHashForInitResponse = iap.messageHashForInit(txnAmt);
		String merTxnId = messageHashForInitResponse.jsonPath().getString("merTxnId");
		String initMsgHash = messageHashForInitResponse.jsonPath().getString("msgHash");
		
		//extracting relevant values from init response
		Response initResponse = iap.init(DC, txnAmt, userId, userAuthToken, merTxnId , initMsgHash);
		String wibmoTxnId = initResponse.jsonPath().getString("wibmoTxnId");
		String wibmoTxnToken=initResponse.jsonPath().getString("wibmoTxnToken");

		//extracting relevant values from process response
		Response processResponse = iap.process( DC, userId, pcAcNumber, userAuthToken, wibmoTxnId, wibmoTxnToken);
		
		Response messageHashForChargeResponse = iap.messageHashForCharge(txnAmt, wibmoTxnId, merTxnId);
		String chargeMsgHash=messageHashForChargeResponse.jsonPath().getString("msgHash");
		
		//Doing charge without receiving authentication reponse.
		Response chargeResponse = iap.charge(DC, txnAmt, chargeMsgHash, wibmoTxnId, merTxnId);
		String resCode = chargeResponse.jsonPath().getString("resCode");
		Assert.assertEquals(resCode, "080");
		Assert.assertEquals(Generic.getJsonPathValue(chargeResponse, "resDesc"),"Too early to do inquiry..please try after sometime");
		lgn.Logout(mobileNo, loginResponse);
	}

}
