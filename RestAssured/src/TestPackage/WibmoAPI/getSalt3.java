package TestPackage.WibmoAPI;
import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class getSalt3 {

	String domain="https://api.pc.enstage-sas.com";
	Response res;
	String response;
	JsonPath jp;
	
	@Test
	public void getSalt(){
		RestAssured.baseURI=domain;
		res=given()
				.log()
				.all()
				.param("username","9986939965")
				.when()
				.post("/in/user/auth/6019/salt");
		response=res.asString();
		System.out.println(response);
		
	}
	
}
