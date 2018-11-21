package com.wibmo.LoginAPI.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.wibmo.API.LoginAPI;

import TestPackage.WibmoAPI.Login;
import io.restassured.response.Response;
import library.BaseClass;

public class LGN_004 extends BaseClass{
	/***
	 * Verify login when salt of mobile A used for mobile B.
	 * 
	 */
	static String mobileNo1="9986939965" , mobileNo2="1144778863" , pin="1111";
	
	@Test
	public static void LGN_004() {
		LoginAPI lgn = new LoginAPI();
		
		
		
		Response getSaltResponseMob1 = lgn.getSalt(mobileNo1);//getting salt of mob no: 1
		Response getSaltResponseMob2=lgn.getSalt(mobileNo2); //getting salt of mob no: 2
		String mob1Salt=getSaltResponseMob1.jsonPath().getString("salt"); //extracting salt of mob no: 1 from json response
		String DC = getSaltResponseMob1.jsonPath().getString("clusterInfo.urls.api");
		Response generatePasswordResponse = lgn.generatePassword(pin, mob1Salt); //generating password for mob no: 1 with mob1Salt
		String pwdMob1 = generatePasswordResponse.jsonPath().getString("pwd"); //extracting encrypted password for mob no: 1
		String mob2Salt=getSaltResponseMob2.jsonPath().getString("salt"); //extracting salt of mob no: 1 from json response
		Response startLoginResponse = lgn.startLogin(mobileNo1, pwdMob1, mob2Salt, DC);
		String resDesc = startLoginResponse.jsonPath().getString("resDesc"); 
		
		Assert.assertEquals(resDesc, "Invalid Salt");
		/*LoginAPI.getOtp(mobileNo2, 6019, 5);
		LoginAPI.completeLogin(mobileNo2);*/
	}

}
