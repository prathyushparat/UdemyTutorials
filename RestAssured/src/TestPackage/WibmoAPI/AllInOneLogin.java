package TestPackage.WibmoAPI;

import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import files.Payload;
import files.ReusableMethods;
import files.resources;

public class AllInOneLogin {

	public static void main(String[] args) throws IOException {
		Properties prop = new Properties();
		FileInputStream fs = new FileInputStream("./src/files/env.properties");
		prop.load(fs);
		
		
		//=======Getting salt==========//
		
		System.out.println("======Getting salt=======");
		RestAssured.baseURI=prop.getProperty("HOST");
		//RestAssured.baseURI="https://api.pc.enstage-sas.com";
		Response res = given().
		param("username",prop.getProperty("LoginMobileNumber")).log().all().
		when().
		post(resources.getSaltResource());
		String response = res.asString();
		System.out.println("The response is "+response);
		JsonPath js = new JsonPath(response);
		String DC = js.get("clusterInfo.urls.api");
		System.out.println("The DC hit is "+DC );
		String salt = js.getString("salt");
		
		//=======Creating password==========//
		System.out.println("======Creating password=======");
		RestAssured.baseURI=prop.getProperty("HOST1");
		res=given().
		param("password",prop.getProperty("PIN")).
		param("key",salt).log().all().
		when().
		post(resources.getCReatePwdResource());
		/*String pwd = res.asString();
		js = new JsonPath(pwd);*/
		ReusableMethods.rawToJson(res);
		String pwd=js.getString("encPwd");
		System.out.println("The encrypted password is "+pwd);
		
		//=======Login1==========//
		System.out.println("======Login1=======");
		RestAssured.baseURI=prop.getProperty("HOST");
		res = given().
				body(Payload.loginBody()).log().all().
				when().
				post(resources.postLoginResource());
		ReusableMethods.rawToJson(res);
		
		
		String tempToken=js.get("tempToken");
		System.out.println("The temp token is "+tempToken);
		


	}
}
