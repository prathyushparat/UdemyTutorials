package TestPackage.WibmoAPI;

import files.Payload;
import files.resources;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import TestPackage.WibmoAPI.getOTP;
import TestPackage.WibmoAPI.CreatePWD;
import TestPackage.WibmoAPI.Login;

import java.io.FileInputStream;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CompleteLogin {
	static Properties prop = new Properties();
  
  @BeforeTest
  public void preLogin(){
	  getSalt.salt();
	  CreatePWD.password();
  }
  @Test
  public void completionOfLogin() {
	 
	  Login.login1();
	  getOTP.otp();
	  
	  //RestAssured.baseURI=prop.getProperty("HOST");
	  Response res = given().
			  body(Payload.completeLoginBody()).
			  when().
			  post(resources.postCompleteLoginResource());
	  String response=res.asString();
	  System.out.println(response);
  }
}
