package com.wibmo.API;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import org.testng.Assert;

import library.BaseClass;
import library.Generic;
import library.*;

public class KYCAPI {
	
	public static void updateToMinKYC(String mobNo) {
		String jobName = Generic.getPropetyValues("KYC_AUTO_UPGRADE");
		DB.updateKYCAutoUpgradeLevel(mobNo, 30);
		jobTrigger(jobName);
		
		
	}
	
	public static void updateToEKYC(String mobNo) {
		String jobName = Generic.getPropetyValues("KYC_AUTO_UPGRADE");
		DB.updateKYCAutoUpgradeLevel(mobNo, 100);
		jobTrigger(jobName);
		
		
	}
	
	public static boolean jobTrigger(String jobName) {
		
		System.out.println("Running "+jobName);
		
		RestAssured.baseURI=Generic.getPropetyValues("CSR_RESTDOMAIN");
		try
		{
			Response res = given()
					   .param("password" , Generic.getPropetyValues("JOB_PWD"))
					   .when()
					   .post("/internal/job/"+jobName+"/start/");
		
		ELog.info("======== Response of " + jobName + " job API ======== " +res.asString() );
		
		Assert.assertEquals("true", res.asString());
		return true;
		}catch(Exception e) {
			Assert.fail("====="+jobName+" Job failed=====");
			return false;
		}
		
	}

}
