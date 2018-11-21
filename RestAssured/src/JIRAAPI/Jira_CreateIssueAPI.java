package JIRAAPI;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import files.ReusableMethods;

import files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import JIRAAPI.Jira_LoginApi;

public class Jira_CreateIssueAPI extends Jira_ReusableMethods {
	@BeforeTest
	public void setup() throws IOException {
		Jira_ReusableMethods.initializeProperties();
	}

	@Test
	public void createIssue() {
		RestAssured.baseURI = Jira_ReusableMethods.prop.getProperty("HOST");
		Response res = given().
				header("Content-Type","application/json")
				.header("Cookie",
						"JSESSIONID=" + Jira_ReusableMethods.createSession()
								+ "").
				body("{" + "\"fields\": {" + "\"project\": {"
						+ "\"key\": \"RES\"" + "},"
						+ "\"summary\": \"something's wrong\","
						+ "\"issuetype\": {" + "\"name\": \"Bug\"" + "},"
						+ "\"description\": \"description\"" + "}" + "}")
				.when().post("/rest/api/2/issue").then().statusCode(201).and().
				extract().response();

		JsonPath js = ReusableMethods.rawToJson(res);
		String id = js.get("id");
		System.out.println("Id is " + id);
	}
}
