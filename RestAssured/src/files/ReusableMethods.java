package files;

import static io.restassured.RestAssured.given;

import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ReusableMethods {
	public static Properties prop = new Properties();

	public static JsonPath rawToJson(Response raw){
		String res=raw.asString();
		JsonPath js=new JsonPath(res);
		return js;
	}
	
	

}
