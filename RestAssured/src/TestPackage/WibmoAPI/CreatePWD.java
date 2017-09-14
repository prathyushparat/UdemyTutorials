package TestPackage.WibmoAPI;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import files.resources;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CreatePWD {
	static Properties prop = new Properties();

	@BeforeTest
	public static void set() throws IOException {
		FileInputStream fs = new FileInputStream("./src/files/env.properties");
		prop.load(fs);
	}

	public static String pwd;

	@Test
	public static void password() {
		getSalt.salt();
		//RestAssured.baseURI = prop.getProperty("HOST1");
		RestAssured.baseURI="https://wallet.pc.enstage-sas.com";
		Response rawRes = given().param("password", prop.getProperty("PIN"))
				.param("key", getSalt.salt).when()
				.post(resources.getCReatePwdResource());
		String strRes = rawRes.asString();
		System.out.println(strRes);
		JsonPath js = new JsonPath(strRes);
		pwd = js.get("encPwd");
		System.out.println("The encrypted password is :" + pwd);
	}
}
