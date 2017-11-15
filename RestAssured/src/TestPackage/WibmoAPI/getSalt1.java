package TestPackage.WibmoAPI;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

@Test
public class getSalt1 {
	public void generateSalt(){
		RestAssured.baseURI="https://api.pc.enstage-sas.com";
		Response res = given().queryParameter("username","9986939965").
		when().
		post("/in/user/auth/6019/salt");
	}
	
	public void createPassword(){
		RestAssured.baseURI="https://api.pc.enstage-sas.com";
		Response res = given().queryParameter("username","9986939965").
		when().
		post("/in/user/auth/6019/salt");
	}
	
	


}
