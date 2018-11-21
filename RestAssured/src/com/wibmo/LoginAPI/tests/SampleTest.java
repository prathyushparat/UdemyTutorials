package com.wibmo.LoginAPI.tests;

import org.testng.annotations.Test;

import com.wibmo.API.KYCAPI;
import com.wibmo.API.LoadMoneyAPI;
import com.wibmo.API.RegisterOfflineAPI;

import io.restassured.response.Response;
import library.BaseClass;
import library.DB;

public class SampleTest extends BaseClass {
	
	@Test
	public static void testtest() {
		/*KYCAPI kyc = new KYCAPI();
		kyc.updateToMinKYC("2233665545");*/
		//kyc.updateToEKYC("9986939965");
		//DB.deleteUser("2233665548");
	/*RegisterOfflineAPI ro= new RegisterOfflineAPI();
		Response offlineRegRes=ro.offlineRegistration("2233665548");*/
		KYCAPI.updateToMinKYC("2233665548");
		
	}

}
