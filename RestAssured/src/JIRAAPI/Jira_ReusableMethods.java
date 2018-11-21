package JIRAAPI;

import static io.restassured.RestAssured.given;
import files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Jira_ReusableMethods {
	static Properties prop = new Properties();
	
	public static void initializeProperties() throws IOException{
		FileInputStream fs = new FileInputStream("./src/files/jira.properties");
		prop.load(fs);
		
	}
	
	public static String createSession() {
		  RestAssured.baseURI=prop.getProperty("HOST");
		  Response res = given().header("Content-Type","application/json").
		  	  body("{\"username\":\""+prop.getProperty("username")+"\",\"password\":\""+prop.getProperty("password")+"\"}").
		  	when().
			  post("/rest/auth/1/session").
			  then().statusCode(200).extract().response();
			  JsonPath js = ReusableMethods.rawToJson(res);
			  String sessionID=js.get("session.value");
			  System.out.println("Session ID is "+ sessionID);
			  return sessionID;
			  
	  }
	
	

}
