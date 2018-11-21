package JIRAAPI;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Jira_LoginApi extends Jira_ReusableMethods {

	@BeforeTest
	public void setup() throws IOException {
		Jira_ReusableMethods.initializeProperties();
	}

	@Test
	public static void testCreateSession() {
		/*RestAssured.baseURI = prop.getProperty("HOST");
		Response res = given()
				.header("Content-Type", "application/json")
				.body("{\"username\":\"" + prop.getProperty("username")
						+ "\",\"password\":\"" + prop.getProperty("password")
						+ "\"}").when().post("/rest/auth/1/session").then()
				.statusCode(200).extract().response();
		JsonPath js = ReusableMethods.rawToJson(res);
		String sessionID = js.get("session.value");
		System.out.println("Session ID is " + sessionID);*/
		Jira_ReusableMethods.createSession();

	}
}
