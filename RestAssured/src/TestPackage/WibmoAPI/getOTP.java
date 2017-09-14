package TestPackage.WibmoAPI;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import files.resources;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import static io.restassured.RestAssured.given;


public class getOTP {
	static Properties prop=new Properties();

	@BeforeTest
	public static void set() throws IOException{
		FileInputStream fs = new FileInputStream("./src/files/env.properties");
		prop.load(fs);
	}
	@Test
	public static void otp() {
		// TODO Auto-generated method stub
		RestAssured.baseURI=prop.getProperty("HOST1");
		Response res = given().
					   param("accessData","9986939965").
					   param("programId","6019").
					   param("eventId","5").
					   when().
					   post(resources.getOTPResource());
		String response=res.asString();
		System.out.println(response);

	}

}
