package com.wibmo.API;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import library.ELog;
import library.Generic;

public class LoadMoneyAPI {
	
	public List<Response> loadMoney(String txnAmt , List<Response> LoginStatus){
		List<Response> LoadMoneyAPIResponse = new ArrayList<Response>();
		String DC = LoginStatus.get(0).jsonPath().getString("clusterInfo.urls.api");
		String userId = LoginStatus.get(3).jsonPath().getString("userId");
		String userAuthToken = LoginStatus.get(3).jsonPath().getString("userToken");
		String pcAcNumber = LoginStatus.get(3).jsonPath().getString("userBankAc[0].pcAccNumber");
		
		Response loadMoneyInitResponse = loadMoneyInit(DC, txnAmt, userId, userAuthToken, pcAcNumber);
		LoadMoneyAPIResponse.add(loadMoneyInitResponse);
		String wibmoTxnId=loadMoneyInitResponse.jsonPath().getString("initResponse.wibmoTxnId");
		String wibmoTxnToken=loadMoneyInitResponse.jsonPath().getString("initResponse.wibmoTxnToken");
		Response loadMoneyMPILegStartResponse = loadMoneyMPILegStart(DC, userId, pcAcNumber, userAuthToken, wibmoTxnId, wibmoTxnToken);
		LoadMoneyAPIResponse.add(loadMoneyMPILegStartResponse);
		Response loadMoneyMockMPIResponse=loadMoneyMockMPIResponse(DC, userId, userAuthToken, txnAmt, pcAcNumber, wibmoTxnId, wibmoTxnToken);
		LoadMoneyAPIResponse.add(loadMoneyMockMPIResponse);
		String dataPickUpCode = Generic.getJsonPathValue(loadMoneyMockMPIResponse, "dataPickUpCode");
		String msgHash = Generic.getJsonPathValue(loadMoneyMockMPIResponse, "msgHash");
		Response loadMoneyProcessResponse = loadMoneyProcess(DC, userId, userAuthToken, pcAcNumber, wibmoTxnId, wibmoTxnToken, dataPickUpCode, msgHash);
		LoadMoneyAPIResponse.add(loadMoneyProcessResponse);

		return LoadMoneyAPIResponse;
	}
	
	/***
	 * This method does load money init
	 * @param DC
	 * @param txnAmt
	 * @param userId
	 * @param userAuthToken
	 * @param pcAcNumber
	 * @return
	 */
	public Response loadMoneyInit(String DC , String txnAmt , String userId , String userAuthToken , String pcAcNumber
			 ) {
		RestAssured.baseURI=DC;
		Response res=null;
		ELog.info("=== Starting load money init ===");
		 res=given()
				.header("Content-Type","application/json")
				.header("X-Auth-UID",userId)
				.header("X-Auth-Token",userAuthToken)
				.log().all()
				.body("{"+
					    "\"deviceInfo\": {"+
				        "\"appInstalled\": true,"+
				        "\"deviceID\": \"tdid:62Zm0zrjP80kOILv2YEE7xthhp1UhjWFpNbzm3PhcT4=:4900\","+
				        "\"deviceIDType\": 3,"+
				        "\"deviceMake\": \"motorola\","+
				        "\"deviceModel\": \"XT1225\","+
				        "\"deviceType\": 3,"+
				        "\"osType\": \"Android\","+
				        "\"osVersion\": \"6.0.1\","+
				        "\"wibmoAppVersion\": \"3.07.02.02\","+
				        "\"wibmoSdkVersion\": \"2.2.0\""+
				    "},"+
				    "\"pan\": \"4329091207169785\","+
				    "\"sourceOfFundAliasName\": \"Video\","+
				    "\"sourceOfFundId\": \"SFT_VDC\","+
				    "\"transactionCurrencyCode\": \"356\","+
				    "\"txnAmount\": \""+txnAmt+"\""+
				"}")
				.when()
				.post("/v2/in/txn/loadMoney/6019/"+pcAcNumber+"/init");
		 ELog.info(res.asString());
			//System.out.println(res.asString());
			/*String wibmoTxnId=res.jsonPath().getString("initResponse.wibmoTxnId");
			String wibmoTxnToken=res.jsonPath().getString("initResponse.wibmoTxnToken");
			String initStatus=Generic.getJsonPathValue(res, "resDesc");*/
			//ELog.info("txn ID & token is "+wibmoTxnId+" "+wibmoTxnToken);
			ELog.info("=== Load money init is success ===");
		
		
		return res;
		
	}
	
	/***
	 * This method does start of MPI leg & returns the response of the start of MPI leg
	 * @param DC
	 * @param userId
	 * @param pcAcNumber
	 * @param userAuthToken
	 * @param wibmoTxnId
	 * @param wibmoTxnToken
	 * @return
	 */
	public Response loadMoneyMPILegStart(String DC , String userId , String pcAcNumber , String userAuthToken , 
			String wibmoTxnId , String wibmoTxnToken ) {
		RestAssured.baseURI=DC;
		Response res=null;
		ELog.info("=== Starting MPI leg ===");
			
		
		 res = given()
				.header("Content-Type","application/json")
				.header("X-Auth-UID",userId)
				.header("X-Auth-Token",userAuthToken)
				.log().all()
				.body("{"+
					    "\"deviceInfo\": {"+
				        "\"appInstalled\": true,"+
				        "\"deviceID\": \"tdid:62Zm0zrjP80kOILv2YEE7xthhp1UhjWFpNbzm3PhcT4=:4900\","+
				        "\"deviceIDType\": 3,"+
				        "\"deviceMake\": \"motorola\","+
				        "\"deviceModel\": \"XT1225\","+
				        "\"deviceType\": 3,"+
				        "\"osType\": \"Android\","+
				        "\"osVersion\": \"6.0.1\","+
				        "\"wibmoAppVersion\": \"3.03.01.04\","+
				        "\"wibmoSdkVersion\": \"2.2.0\""+
				    "},"+
				    "\"linkBillingAddress\": false,"+
				    "\"merchantInfo\": {"+
				        "\"merAppId\": \"9017\","+
				        "\"merCountryCode\": \"IN\","+
				        "\"merId\": \"1273864627877702560\","+
				        "\"merName\": \"PayZapp Load Money\","+
				        "\"merProgramId\": \"6019\""+
				    "},"+
				    "\"paymentCardInfo\": {"+
				        "\"alias\": \"Visa\","+
				        "\"expMM\": \"08\","+
				        "\"expYYYY\": \"2020\","+
				        "\"favPaymentCard\": false,"+
				        "\"nameOnCard\": \"Video\","+
				        "\"pan\": \"4329091207169785\","+
				        "\"saveCard\": true,"+
				        "\"useWibmoCard\": false"+
				    "},"+
				    "\"userAuthToken\": \""+userAuthToken+"\","+
				    "\"userId\": \""+userId+"\","+
				    "\"userPcAccount\": \""+pcAcNumber+"\","+
				    "\"wibmoTxnId\": \""+wibmoTxnId+"\","+
				    "\"wibmoTxnToken\": \""+wibmoTxnToken+"\""+
				"}")
				.when()
				.post("/v2/in/txn/iap/wpay/6019/process");
		ELog.info(res.asString());
		System.out.println(res.asString());
		String redirectResponse=Generic.getJsonPathValue(res, "additionalUserInputData.redirectPageText");
		
		return res;
	}
	
	/***
	 * This method mocks the response from MPI to wibmo as part of the 3ds leg completion.
	 * @param DC
	 * @param userId
	 * @param userAuthToken
	 * @param txnAmt
	 * @param pcAcNumber
	 * @param wibmoTxnId
	 * @param wibmoTxnToken
	 * @return
	 */
	public Response loadMoneyMockMPIResponse(String DC , String userId , String userAuthToken , 
			String txnAmt , String pcAcNumber , String wibmoTxnId ,String wibmoTxnToken)
	{
		
		RestAssured.baseURI=DC;
		Response res=null;
		ELog.info("=== Mocking MPI response to wibmo post 3ds ===");
		 res=given()
					.header("Content-Type","application/json")
					.header("X-Auth-UID",userId)
					.header("X-Auth-Token",userAuthToken)
					.log().all()
					.body("{"+
						    "\"additionalUserInputData\": {"+
					        "\"browserKeyValue\": ["+
					            "{"+
					                "\"id\": \"status\","+
					                "\"value\": \"Y\""+
					            "},"+
					            "{"+
					                "\"id\": \"eci\","+
					                "\"value\": \"05\""+
					            "},"+
					            "{"+
					                "\"id\": \"cavv\","+
					                "\"value\": \"AAABBRkAADdIA2OFlAAAAAAAAAA=\""+
					            "},"+
					            "{"+
					                "\"id\": \"purchase_amount\","+
					                "\"value\": \""+txnAmt+"\""+
					            "},"+
					            "{"+
					                "\"id\": \"currency\","+
					                "\"value\": \"356\""+
					            "},"+
					            "{"+
					                "\"id\": \"message_hash\","+
					                "\"value\": \"cBiNI9U6qrcrSN06IAvgrp/GZgM=\""+
					            "},"+
					            "{"+
					                "\"id\": \"mpiErrorCode\","+
					                "\"value\": \"000\""+
					            "},"+
					            "{"+
					                "\"id\": \"ID\","+
					                "\"value\": \"20180103403038\""+
					            "},"+
					            "{"+
					                "\"id\": \"md\","+
					                "\"value\": \"201801100930121662sS23kZ3\""+
					            "},"+
					            "{"+
					                "\"id\": \"xid\","+
					                "\"value\": \"TVBJWElENXhJOHhaNXVPOHlKNm0=\""+
					            "},"+
					            "{"+
					                "\"id\": \"statusReceivedInVERes\","+
					                "\"value\": \"Y\""+
					            "},"+
					            "{"+
					                "\"id\": \"statusReceivedInPARes\","+
					                "\"value\": \"Y\""+
					            "},"+
					            "{"+
					                "\"id\": \"PAResVerified\","+
					                "\"value\": \"true\""+
					            "},"+
					            "{"+
					                "\"id\": \"PAResSyntaxOK\","+
					                "\"value\": \"true\""+
					            "},"+
					            "{"+
					                "\"id\": \"cardholder_country\","+
					                "\"value\": \"IN\""+
					            "}"+
					        "],"+
					        "\"formInput\": ["+
					            "{"+
					                "\"allowStore\": false,"+
					                "\"editable\": false,"+
					                "\"id\": \"METHOD_IDENTIFIER\","+
					                "\"inputType\": \"HID\","+
					                "\"inputValue\": \"RESPONSE_FROM_EXTERANL_SOURCE\","+
					                "\"mandatory\": false,"+
					                "\"maxLength\": 0,"+
					                "\"minLength\": 0"+
					            "}"+
					        "]"+
					    "},"+
					    "\"deviceInfo\": {"+
					        "\"appInstalled\": true,"+
					        "\"deviceID\": \"tdid:62Zm0zrjP80kOILv2YEE7xthhp1UhjWFpNbzm3PhcT4=:4900\","+
					        "\"deviceIDType\": 3,"+
					        "\"deviceMake\": \"motorola\","+
					        "\"deviceModel\": \"XT1225\","+
					        "\"deviceType\": 3,"+
					        "\"osType\": \"Android\","+
					        "\"osVersion\": \"6.0.1\","+
					        "\"wibmoAppVersion\": \"3.03.01.04\","+
					        "\"wibmoSdkVersion\": \"2.2.0\""+
					    "},"+
					    "\"linkBillingAddress\": false,"+
					    "\"merchantInfo\": {"+
					        "\"merAppId\": \"9017\","+
					        "\"merCountryCode\": \"IN\","+
					        "\"merId\": \"1273864627877702560\","+
					        "\"merName\": \"PayZapp Load Money\","+
					        "\"merProgramId\": \"6019\""+
					    "},"+
					    "\"paymentCardInfo\": {"+
					        "\"alias\": \"Visa\","+
					        "\"expMM\": \"08\","+
					        "\"expYYYY\": \"2020\","+
					        "\"favPaymentCard\": false,"+
					        "\"nameOnCard\": \"Video\","+
					        "\"pan\": \"4329091207169785\","+
					        "\"saveCard\": true,"+
					        "\"useWibmoCard\": false"+
					    "},"+
					    "\"userAuthToken\": \""+userAuthToken+"\","+
					    "\"userId\": \""+userId+"\","+
					    "\"userPcAccount\": \""+pcAcNumber+"\","+
					    "\"wibmoTxnId\": \""+wibmoTxnId+"\","+
					    "\"wibmoTxnToken\": \""+wibmoTxnToken+"\""+
					"}")
					.when()
					.post("/v2/in/txn/iap/wpay/6019/process");
		ELog.info(res.asString());
		System.out.println(res.asString());
		String dataPCode = Generic.getJsonPathValue(res, "dataPickUpCode");
		String messageHash = Generic.getJsonPathValue(res, "msgHash");
	
		return res;
	}
	
	/***
	 * This method does load money process
	 * @param DC
	 * @param userId
	 * @param userAuthToken
	 * @param pcAcNumber
	 * @param wibmoTxnId
	 * @param wibmoTxnToken
	 * @param dataPickUpCode
	 * @param msgHash
	 * @return
	 */
	public Response loadMoneyProcess(String DC , String userId, String userAuthToken, 
			String pcAcNumber, String wibmoTxnId ,String wibmoTxnToken, String dataPickUpCode, String msgHash )
	{
		RestAssured.baseURI=DC;
		Response res=null;
		//try {
		 res=given()
					.header("Content-Type","application/json")
					.header("X-Auth-UID",userId)
					.header("X-Auth-Token",userAuthToken)
					.log().all()
					.body("{"+
						    "\"dataPickUpCode\": \""+dataPickUpCode+"\","+
						    "\"deviceInfo\": {"+
						        "\"appInstalled\": true,"+
						        "\"deviceID\": \"tdid:62Zm0zrjP80kOILv2YEE7xthhp1UhjWFpNbzm3PhcT4=:4900\","+
						        "\"deviceIDType\": 3,"+
						        "\"deviceMake\": \"motorola\","+
						        "\"deviceModel\": \"XT1225\","+
						        "\"deviceType\": 3,"+
						        "\"osType\": \"Android\","+
						        "\"osVersion\": \"6.0.1\","+
						        "\"wibmoAppVersion\": \"3.07.02.02\","+
						        "\"wibmoSdkVersion\": \"2.2.0\""+
						    "},"+
						    "\"msgHash\": \""+msgHash+"\","+
						    "\"resCode\": \"000\","+
						    "\"resDesc\": \"SUCCESS\","+
						    "\"wibmoTxnId\": \""+wibmoTxnId+"\""+
						"}")
					.when()
					.post("/v2/in/txn/loadMoney/6019/"+pcAcNumber+"/process");
		ELog.info(res.asString());
		System.out.println(res.asString());
		String loadStatusMsg = Generic.getJsonPathValue(res, "addFundDetails.headerMessage");
		/*Assert.assertEquals(res ,"Funds have been loaded successfully");
		}
		catch(Exception e) {
			Assert.fail("Load money failed "+e.getMessage());
		}*/
		return res;
	}
}
