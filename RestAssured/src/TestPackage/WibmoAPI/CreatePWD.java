package TestPackage.WibmoAPI;

import java.io.FileInputStream;
import java.io.IOException;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class CreatePWD extends getSalt{
	
	@BeforeTest
	public static void set() throws IOException{
		FileInputStream fs = new FileInputStream("D:\\Prathyush\\Work\\Automation\\UdemyTutorial\\RestAssured\\env.properties");
		prop.load(fs);
	}
	
	@Test
	public static void password(){
		salt();
		RestAssured.baseURI=prop.getProperty("HOST1");
		//RestAssured.baseURI="https://wallet.pc.enstage-sas.com";
		Response rawRes=given().
		param("password","1111").
		param("key",salt).
		when().
		post("/sampleMerchant/passwordEncrypt.jsp");
		String strRes = rawRes.asString();
		System.out.println(strRes);
		JsonPath js = new JsonPath(strRes);
		System.out.println("The encrypted password is :" +js.get("encPwd"));
	}
}
