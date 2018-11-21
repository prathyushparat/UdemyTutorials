package com.wibmo.API;

import static io.restassured.RestAssured.given;

import java.util.List;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import library.Generic;
import library.Log;

public class SendMoneyAPI {
	
	public Response sendMoneyAPI(String txnAmt , List<Response> LoginStatus , String receiverMob) {
		
		String DC = Generic.getJsonPathValue(LoginStatus.get(0), "clusterInfo.urls.api");
		String userId = Generic.getJsonPathValue(LoginStatus.get(3), "userId");
		String userAuthToken = Generic.getJsonPathValue(LoginStatus.get(3),"userToken");
		String pcAcNumber = Generic.getJsonPathValue(LoginStatus.get(3),"userBankAc[0].pcAccNumber");
		
		Response sendMoneyResponse = sendMoney(DC, txnAmt, userId, userAuthToken, pcAcNumber, receiverMob);
		Log.info("Send money response :"+sendMoneyResponse.asString());
		return sendMoneyResponse;
	}
	
	public Response sendMoney(String DC , String txnAmt , String userId , 
			String userAuthToken , String pcAcNumber, String receiverMob)
	{
		RestAssured.baseURI=DC;
		Response res = given()
					   .header("Content-Type","application/json")
				       .header("X-Auth-UID",userId)
				       .header("X-Auth-Token",userAuthToken)
				       .log().all()
				       .body("{"+  
				    		   "\"clientInfo\":{"+  
				    		      "\"clientDeviceName\":\"AndriodApp @ motorola XT1225 - Vodafone IN\","+
				    		      "\"clientDeviceType\":3,"+
				    		      "\"clientMaker\":\"motorola\","+
				    		      "\"clientModel\":\"XT1225\","+
				    		      "\"clientOsName\":\"Android\","+
				    		      "\"clientOsVersion\":\"6.0.1\""+
				    		   "},"+
				    		   "\"destinationOfFundAliasName\":\"tears\","+
				    		   "\"destinationOfFundId\":\"DFT_MOBILE\","+
				    		   "\"gpsInfo\":{"+  
				    		      "\"accuracy\":0.0,"+
				    		      "\"gpsTime\":0,"+
				    		      "\"latitude\":0.0,"+
				    		      "\"longitude\":0.0"+
				    		   "},"+
				    		   "\"inputs\":["+  
				    		      "{"+  
				    		         "\"allowStore\":false,"+
				    		         "\"editable\":false,"+
				    		         "\"encryption\":false,"+
				    		         "\"id\":\"MOBILE\","+
				    		         "\"inputValue\":\""+receiverMob+"\","+
				    		         "\"mandatory\":false,"+
				    		         "\"maxLength\":0,"+
				    		         "\"minLength\":0"+
				    		      "},"+
				    		      "{"+  
				    		         "\"allowStore\":false,"+
				    		         "\"editable\":false,"+
				    		         "\"encryption\":false,"+
				    		         "\"id\":\"RCP_NAME\","+
				    		         "\"inputValue\":\"tears\","+
				    		         "\"mandatory\":false,"+
				    		         "\"maxLength\":0,"+
				    		         "\"minLength\":0"+
				    		      "},"+
				    		      "{"+  
				    		         "\"allowStore\":false,"+
				    		         "\"editable\":false,"+
				    		         "\"encryption\":false,"+
				    		         "\"id\":\"AMOUNT\","+
				    		         "\"inputValue\":\"0.01\","+
				    		         "\"mandatory\":false,"+
				    		         "\"maxLength\":0,"+
				    		         "\"minLength\":0"+
				    		      "},"+
				    		      "{"+  
				    		         "\"allowStore\":false,"+
				    		         "\"editable\":false,"+
				    		         "\"encryption\":false,"+
				    		         "\"id\":\"GIFT_OR_CASH\","+
				    		         "\"inputValue\":\"1\","+
				    		         "\"mandatory\":false,"+
				    		         "\"maxLength\":0,"+
				    		         "\"minLength\":0"+
				    		      "},"+
				    		      "{"+  
				    		         "\"allowStore\":false,"+
				    		         "\"editable\":false,"+
				    		         "\"encryption\":false,"+
				    		         "\"id\":\"TRANSFER_DESCRIPTION\","+
				    		         "\"inputValue\":\"teardrop\","+
				    		         "\"mandatory\":false,"+
				    		         "\"maxLength\":0,"+
				    		         "\"minLength\":0"+
				    		      "},"+
				    		      "{"+  
				    		         "\"allowStore\":false,"+
				    		         "\"editable\":false,"+
				    		         "\"encryption\":false,"+
				    		         "\"id\":\"TRANSFER_DESCRIPTION\","+
				    		         "\"inputValue\":\"teardrop\","+
				    		         "\"mandatory\":false,"+
				    		         "\"maxLength\":0,"+
				    		         "\"minLength\":0"+
				    		      "}"+
				    		   "],"+
				    		   "\"lastAction\":10,"+
				    		   "\"newDestinationOfFund\":true,"+
				    		   "\"pcAccountNumber\":\""+pcAcNumber+"\","+
				    		   "\"saveUserDF\":false,"+
				    		   "\"simAndDeviceData\":{"+  
				    		      "\"deviceData1\":\"zoklBWqgx5agU+s5I7mDMiCFqIKbHgszRuUuJAQ8CXs=\","+
				    		      "\"deviceData2\":\"DES+n3lIlX2wc6HyTCZrB1CLEn7AMLMoJprQUtchOqc=\","+
				    		      "\"deviceData3\":\"NeYujrnWV1k3N+dm11rz4OdhI0fSU7T3ejJkgqjsSf8=\","+
				    		      "\"deviceData4\":\"coyERY/CJaEj47LHmuUZ73uaROgPudGhrKuS8jF4ym4=\","+
				    		      "\"deviceId\":\"tdid:62Zm0zrjP80kOILv2YEE7xthhp1UhjWFpNbzm3PhcT4=:4900\","+
				    		      "\"deviceIdProof\":\"XxbU95awPovoYk889kMz5j6RcG+ZnbLs1vxzjiqVhz2zjZJuV4FzFw2NnHjfOYeDRA0vMDCol+e0j6eIe9q/YLk8Z6FSvLJbnF5gRI9KiX2MT7C7PSZOBCy0G94c8EUkMOmHwu968ZP9R9/f+0B9nCWW+IiS+hT3QlvKcTBkiqA=\","+
				    		      "\"deviceIdProof\":\"XxbU95awPovoYk889kMz5j6RcG+ZnbLs1vxzjiqVhz2zjZJuV4FzFw2NnHjfOYeDRA0vMDCol+e0j6eIe9q/YLk8Z6FSvLJbnF5gRI9KiX2MT7C7PSZOBCy0G94c8EUkMOmHwu968ZP9R9/f+0B9nCWW+IiS+hT3QlvKcTBkiqA=\","+
				    		      "\"deviceIdType\":3,"+
				    		      "\"simId\":\"subid:UO/GZJGz7H2y4Cm1umj80Lqu1Mn5kMZEuK74MZaTEHU=:0373\","+
				    		      "\"simIdProof\":\"SNpQX/tIzzYR/UiY7vFdJsLKTJUhwi/fCubMx0ZambqvahADeC1slkSBTfQCmhbFFBstFF+AOfRAEAsYq+MQmH+GOLhNrzuwjZtaMgTRpjIXZKRC54Rf2syBeF7ZbW5hZbcDowWW+T5uoPbAGU79xXSKz/dlDrO+p4FxAtK82Uk=\","+
				    		      "\"simOperator\":\"Vodafone IN\""+
				    		   "}"+
				    		"}")
				       .when()
				       .post("/in/txn/outflow/6019/sendFund");
		System.out.println(res.asString());
		return res;
	}

}
