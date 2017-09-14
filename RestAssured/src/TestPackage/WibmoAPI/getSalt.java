package TestPackage.WibmoAPI;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import files.resources;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;




public class getSalt {
	static Properties prop = new Properties();
	
	@BeforeTest
	public static void set() throws IOException{
		FileInputStream fs = new FileInputStream("./src/files/env.properties");
		prop.load(fs);
	}
	
	public static String salt , DC;
	@Test
	public static void salt() {
		
		RestAssured.baseURI=prop.getProperty("HOST");
		
		//RestAssured.baseURI="https://api.pc.enstage-sas.com";
		Response res = given().
		param("username",prop.getProperty("LoginMobileNumber")).
		when().
		post(resources.getSaltResource());
		String response = res.asString();
		System.out.println("The response is "+response);
		JsonPath js = new JsonPath(response);
		DC = js.get("clusterInfo.urls.api");
		System.out.println("The DC hit is "+DC );
		salt = js.getString("salt");
		
	}

}
