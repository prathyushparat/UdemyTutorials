package TestPackage.WibmoAPI;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import static io.restassured.RestAssured.given;

import scala.sys.process.ProcessBuilderImpl.FileInput;

public class getSalt4 {
	static Properties prop = new Properties();
	static Response res;
	JsonPath jp;
	public static void getSalt() throws IOException{
		FileInputStream fs = new FileInputStream("./src/files/env.properties");
		prop.load(fs);
		RestAssured.baseURI=prop.getProperty("HOST");
		res=given()
				.param("username","9986939965")
				.when()
				.post("/in/user/auth/6019/salt");
		String response=res.toString();
		System.out.println(response);
	}
}
