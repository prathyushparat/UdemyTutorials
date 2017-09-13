package files;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ReusableMethods {
	public static JsonPath rawToJson(Response raw){
		String res=raw.asString();
		JsonPath js=new JsonPath(res);
		return js;
	}

}
