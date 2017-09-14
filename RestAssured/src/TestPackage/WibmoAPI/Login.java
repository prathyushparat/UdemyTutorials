package TestPackage.WibmoAPI;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import files.Payload;
import files.resources;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class Login {
	static Properties prop = new Properties();
	@BeforeTest
	public void set() throws IOException {
		FileInputStream fs = new FileInputStream("./src/files/env.properties");
		prop.load(fs);
		

	}

	@Test
	public static void login1() {
		getSalt.salt();
		CreatePWD.password();
		RestAssured.baseURI = prop.getProperty("HOST");
		System.out.println("BaseURI is"+RestAssured.baseURI);
		Response res = given().body(Payload.loginBody()).when()
				.post(resources.postLoginResource());
		String response = res.asString();
		System.out.println("Login response is" + response);
		getOTP.otp();

	}

}
