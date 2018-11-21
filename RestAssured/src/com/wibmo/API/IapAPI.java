package com.wibmo.API;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import library.ELog;
import library.Log;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;



public class IapAPI {

	String   initMsgHash=null,chargeMsgHash; 
	
	public List<Response> IapAPI(String txnAmt , List<Response> LoginStatus) {
		
		ELog.info("IAP API method executing");
		
		//Extracting relevant values from login response list
		String DC = LoginStatus.get(0).jsonPath().getString("clusterInfo.urls.api");
		String userId = LoginStatus.get(3).jsonPath().getString("userId");
		String userAuthToken = LoginStatus.get(3).jsonPath().getString("userToken");
		String pcAcNumber = LoginStatus.get(3).jsonPath().getString("userBankAc[0].pcAccNumber");

		List<Response> IAPResponse = new ArrayList<Response>();
		Response messageHashForInitResponse = messageHashForInit(txnAmt);
		IAPResponse.add(messageHashForInitResponse);
		String merTxnId = messageHashForInitResponse.jsonPath().getString("merTxnId");
		String initMsgHash = messageHashForInitResponse.jsonPath().getString("msgHash");
		
		//extracting relevant values from init response
		Response initResponse = init(DC, txnAmt, userId, userAuthToken, merTxnId , initMsgHash);
		IAPResponse.add(initResponse); //Adding init response to IAPResponse list
		String wibmoTxnId = initResponse.jsonPath().getString("wibmoTxnId");
		String wibmoTxnToken=initResponse.jsonPath().getString("wibmoTxnToken");

		//extracting relevant values from process response
		Response processResponse = process( DC, userId, pcAcNumber, userAuthToken, wibmoTxnId, wibmoTxnToken);
		IAPResponse.add(processResponse); //Adding process response to IAPResponse list
		
		Response mock3dsResponse = mock3dsRes(DC, userId, userAuthToken, txnAmt, pcAcNumber , wibmoTxnId , wibmoTxnToken);
		IAPResponse.add(mock3dsResponse);
		Response messageHashForChargeResponse = messageHashForCharge(txnAmt, wibmoTxnId, merTxnId);
		chargeMsgHash=messageHashForChargeResponse.jsonPath().getString("msgHash");
		IAPResponse.add(messageHashForChargeResponse);
		
		Response chargeResponse = charge(DC, txnAmt, chargeMsgHash, wibmoTxnId, merTxnId);
		IAPResponse.add(chargeResponse);
		return IAPResponse;
		
	}
	
	public Response messageHashForInit(String txnAmt) {
		RestAssured.baseURI="https://wallet.pc.enstage-sas.com";
		Response res = given()
						.param("merAppData","This is Wibmo Test App")
						.param("merName", "Mobile Merchant")
						.param("merDyn", "false")
						.param("txnAmount", txnAmt)
						.param("txnAmountKnown", "true")
						.param("chargeLater", "true")
						.param("version", "2")
						.param("txnType", "wPay")
						.log().all()
						.when()
						.post("/sampleMerchant/iap/generateInitReqMessageHash.jsp");
		String response=res.asString();
		System.out.println(response);
		return res;
		/*merTxnId=js.getString("merTxnId");
		initMsgHash=js.getString("msgHash");*/
		
	}
	
	public Response init(String DC , String txnAmt , String userId , String userAuthToken , 
			String merTxnId , String initMsgHash) {
		RestAssured.baseURI=DC;
		Response res = given()
						.header("Content-Type","application/json")
						.header("X-Auth-UID",userId)
						.header("X-Auth-Token",userAuthToken)
						.log().all()
						.body("{"+  
								   "\"cardInfo\":{"+  

								   "},"+
								   "\"customerInfo\":{"+  
								      "\"custDob\":\"20140101\","+
								      "\"custEmail\":\"abc@abc.com\","+
								      "\"custMobile\":\"1234512345\","+
								      "\"custName\":\"Sharath Kumar\""+
								   "},"+
								   "\"deviceInfo\":{"+  
								      "\"appInstalled\":true,"+
								      "\"deviceIDType\":3,"+
								      "\"deviceType\":3,"+
								      "\"osType\":\"Android\","+
								      "\"wibmoAppVersion\":\"??\","+
								      "\"wibmoSdkVersion\":\"2.0.1\""+
								   "},"+
								   "\"merchantInfo\":{"+  
								      "\"merAppId\":\"1\","+
								      "\"merCountryCode\":\"IN\","+
								      "\"merId\":\"81516121\","+
								      "\"merName\":\"Test Merchant\""+
								   "},"+
								   "\"msgHash\":\""+initMsgHash+"\","+
								   "\"transactionInfo\":{"+  
								      "\"chargeLater\":true,"+
								      "\"merAppData\":\"This is Wibmo Test App\","+
								      "\"merDataField\":\"This is for recon \","+
								      "\"merTxnId\":\""+merTxnId+"\","+
								      "\"supportedPaymentType\":["+  
								         "\"*\""+
								      "],"+
								      "\"txnAmount\":\""+txnAmt+"\","+
								      "\"txnAmtKnown\":true,"+
								      "\"txnCurrency\":\"356\","+
								      "\"txnDesc\":\"For Movie Ticket\""+
								   "},"+
								   "\"txnType\":\"WPay\""+
								"}")
						.when()
						.post("/v2/in/txn/iap/wpay/init");
		
		System.out.println(res.asString());
		String wibmoTxnId=res.jsonPath().getString("wibmoTxnId");
		String wibmoTxnToken=res.jsonPath().getString("wibmoTxnToken");
		System.out.println("txn ID & token is "+wibmoTxnId+" "+wibmoTxnToken);
		Log.info("Init res :"+res.asString());
		ELog.info("txn ID & token is "+wibmoTxnId+" "+wibmoTxnToken);
		return res;
	
						
	}
	
	public Response process(String DC , String userId , String pcAcNumber , String userAuthToken , 
			String wibmoTxnId , String wibmoTxnToken ) {
		RestAssured.baseURI=DC;
		Response res = given()
						.header("Content-Type","application/json")
						.header("X-Auth-UID",userId)
						.header("X-Auth-Token",userAuthToken)
						.log().all()
						.body("{"+  
								   "\"deviceInfo\":{"+  
								      "\"appInstalled\":true,"+
								      "\"deviceID\":\"tdid:a5lwjhSTzs0HhD2t2kOG6fR6oTp5wDkDNQidi2eHRPw=:4197\","+
								      "\"deviceIDType\":3,"+
								      "\"deviceMake\":\"OnePlus\","+
								      "\"deviceModel\":\"ONEPLUS A5010\","+
								      "\"deviceType\":3,"+
								      "\"osType\":\"Android\","+
								      "\"osVersion\":\"8.1.0\","+
								      "\"wibmoAppVersion\":\"3.06.01.00\","+
								      "\"wibmoSdkVersion\":\"2.2.0\""+
								   "},"+
								   "\"linkBillingAddress\":false,"+
								   "\"merchantInfo\":{"+  
								      "\"merAppId\":\"1\","+
								      "\"merCountryCode\":\"IN\","+
								      "\"merId\":\"81516121\","+
								      "\"merName\":\"MVisa\","+
								      "\"merProgramId\":\"6019\""+
								   "},"+
								   "\"paymentCardInfo\":{"+  
								      "\"alias\":\"Visa\","+
								      "\"expMM\":\"08\","+
								      "\"expYYYY\":\"2020\","+
								      "\"favPaymentCard\":false,"+
								      "\"nameOnCard\":\"Corp\","+
								      "\"pan\":\"4329091207169785\","+
								      "\"saveCard\":true,"+
								      "\"useWibmoCard\":false"+
								   "},"+
								   "\"userAuthToken\":\""+userAuthToken+"\","+
								   "\"userId\":\""+userId+"\","+
								   "\"userPcAccount\":\""+pcAcNumber+"\","+
								   "\"wibmoTxnId\":\""+wibmoTxnId+"\","+
								   "\"wibmoTxnToken\":\""+wibmoTxnToken+"\""+
								"}")
						.when()
						.post("/v2/in/txn/iap/wpay/6019/process");
		//response=res.asString();
		System.out.println(res.asString());
		Log.info("IAP process res :"+res.asString());
		return res;
	}
	
	public Response mock3dsRes(String DC , String userId , String userAuthToken , 
			String txnAmt , String pcAcNumber , String wibmoTxnId ,String wibmoTxnToken) {
		
		RestAssured.baseURI=DC;
		Response res = given()
						.header("Content-Type","application/json")
						.header("X-Auth-UID",userId)
						.header("X-Auth-Token",userAuthToken)
						.log().all()		
						.body("{"+  
								   "\"additionalUserInputData\":{"+  
								      "\"browserKeyValue\":["+  
								         "{"+  
								            "\"id\":\"status\","+
								            "\"value\":\"Y\""+
								         "},"+
								         "{"+  
								            "\"id\":\"eci\","+
								            "\"value\":\"05\""+
								         "},"+
								         "{"+  
								            "\"id\":\"cavv\","+
								            "\"value\":\"AAABBRkAAISSUEYVSQAAAAAAAAA=\""+
								         "},"+
								         "{"+  
								            "\"id\":\"purchase_amount\","+
								            "\"value\":\""+txnAmt+"\""+
								         "},"+
								         "{"+  
								            "\"id\":\"currency\","+
								            "\"value\":\"356\""+
								         "},"+
								         "{"+  
								            "\"id\":\"message_hash\","+
								            "\"value\":\"0E2IsMOVtbxfy50EfIT/FXgCcyw=\""+
								         "},"+
								         "{"+  
								            "\"id\":\"mpiErrorCode\","+
								            "\"value\":\"000\""+
								         "},"+
								         "{"+  
								            "\"id\":\"ID\","+
								            "\"value\":\"20180813604609\""+
								         "},"+
								         "{"+  
								            "\"id\":\"md\","+
								            "\"value\":\""+wibmoTxnId+"\""+
								         "},"+
								         "{"+  
								            "\"id\":\"xid\","+
								            "\"value\":\"TVBJWElEM2NLOW1KM2haMGFPOXQ=\""+
								         "},"+
								         "{"+  
								            "\"id\":\"statusReceivedInPARes\","+
								            "\"value\":\"Y\""+
								         "},"+
								         "{"+  
								            "\"id\":\"statusReceivedInVERes\","+
								            "\"value\":\"Y\""+
								         "},"+
								         "{"+  
								            "\"id\":\"PAResVerified\","+
								            "\"value\":\"true\""+
								         "},"+
								         "{"+  
								            "\"id\":\"PAResSyntaxOK\","+
								            "\"value\":\"true\""+
								         "},"+
								         "{"+  
								            "\"id\":\"cardholder_country\","+
								            "\"value\":\"IN\""+
								         "}"+
								      "],"+
								      "\"formInput\":["+  
								         "{"+  
								            "\"allowStore\":false,"+
								            "\"editable\":false,"+
								            "\"id\":\"METHOD_IDENTIFIER\","+
								            "\"inputType\":\"HID\","+
								            "\"inputValue\":\"RESPONSE_FROM_EXTERANL_SOURCE\","+
								            "\"mandatory\":false,"+
								            "\"maxLength\":0,"+
								            "\"minLength\":0"+
								         "}"+
								      "]"+
								   "},"+
								   "\"deviceInfo\":{"+  
								      "\"appInstalled\":true,"+
								      "\"deviceID\":\"tdid:a5lwjhSTzs0HhD2t2kOG6fR6oTp5wDkDNQidi2eHRPw=:4197\","+
								      "\"deviceIDType\":3,"+
								      "\"deviceMake\":\"OnePlus\","+
								      "\"deviceModel\":\"ONEPLUS A5010\","+
								      "\"deviceType\":3,"+
								      "\"osType\":\"Android\","+
								      "\"osVersion\":\"8.1.0\","+
								      "\"wibmoAppVersion\":\"3.06.01.00\","+
								      "\"wibmoSdkVersion\":\"2.2.0\""+
								   "},"+
								   "\"linkBillingAddress\":false,"+
								   "\"merchantInfo\":{"+  
								      "\"merAppId\":\"1\","+
								      "\"merCountryCode\":\"IN\","+
								      "\"merId\":\"81516121\","+
								      "\"merName\":\"MVisa\","+
								      "\"merProgramId\":\"6019\""+
								   "},"+
								   "\"paymentCardInfo\":{"+  
								      "\"alias\":\"Visa\","+
								      "\"expMM\":\"08\","+
								      "\"expYYYY\":\"2020\","+
								      "\"favPaymentCard\":false,"+
								      "\"nameOnCard\":\"Corp\","+
								      "\"pan\":\"4329091207169785\","+
								      "\"saveCard\":true,"+
								      "\"useWibmoCard\":false"+
								   "},"+
								   "\"userAuthToken\":\""+userAuthToken+"\","+
								   "\"userId\":\""+userId+"\","+
								   "\"userPcAccount\":\""+pcAcNumber+"\","+
								   "\"wibmoTxnId\":\""+wibmoTxnId+"\","+
								   "\"wibmoTxnToken\":\""+wibmoTxnToken+"\""+
								"}")
						.when()
						.post("/v2/in/txn/iap/wpay/6019/process");
		//response=res.asString();
		System.out.println(res.asString());
		Log.info("Mock MPI res :"+res.asString());
		return res;
	}
	
	public Response messageHashForCharge(String txnAmt , String wibmoTxnId , String merTxnId) {
		

		RestAssured.baseURI="https://wallet.pc.enstage-sas.com";
		Response res = given()
						.param("txnAmount", txnAmt)
						.param("chargeUser", "true")
						.param("wibmoTxnId", wibmoTxnId)
						.param("txnType", "wPay")
						.param("merTxnId", merTxnId)
						.log().all()
						.when()
						.post("/sampleMerchant/iap/generateEnquiryMessageHash.jsp");
		/*response=res.asString();
		js = new JsonPath(response);*/
		//chargeMsgHash=js.getString("msgHash"); //extracting the charge message hash value
		System.out.println(res.asString());
		ELog.info("Message hash res :"+res.asString());
		return res;
	}
	
	public Response charge(String DC, String txnAmt , String chargeMsgHash , String wibmoTxnId , String merTxnId) {
		RestAssured.baseURI=DC;
		Response res = given()
						.header("Content-Type","application/json")
						.log().all()
						.body("{"+  
								   "\"merchantInfo\":{"+  
								      "\"merName\":null,"+
								      "\"merId\":\"81516121\","+
								      "\"merCountryCode\":\"IN\","+
								      "\"merAppId\":\"1\""+
								   "},"+
								   "\"transactionInfo\":{"+  
								      "\"txnAmount\":\""+txnAmt+"\","+
								      "\"txnCurrency\":\"356\","+
								      "\"txnDesc\":null,"+
								      "\"supportedPaymentType\":null,"+
								      "\"restrictedPaymentType\":null,"+
								      "\"merAppData\":null,"+
								      "\"merTxnId\":\""+merTxnId+"\","+
								      "\"merDataField\":null,"+
								      "\"txnDate\":\"20170419\","+
								      "\"txnAmtKnown\":true,"+
								      "\"chargeLater\":false"+
								   "},"+
								   "\"msgHash\":\""+chargeMsgHash+"\","+
								   "\"chargeCard\":true,"+
								   "\"txnType\":\"WPay\","+
								   "\"wibmoTxnId\":\""+wibmoTxnId+"\""+
								"}")
						.when()
						.post("/v2/in/txn/iap/wpay/enquiry/");
		System.out.println(res.asString());
		ELog.info("Charge res :"+res.asString());
		return res;
	}
}
