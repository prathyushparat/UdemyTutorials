package library;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Generic {
	
	public static String propertiesFilePath="./Properties/Staging1/env.properties";
	private static Logger log = LogManager.getLogger();
	/***
	 * This method extracts the json path value. Parameters are json response(res) & json path(key)
	 * @param res
	 * @param key
	 * @return
	 */
	public static String getJsonPathValue(Response res , String key) {
		String response = res.asString();
		JsonPath js = new JsonPath(response);
		String value = js.get(""+key+"")+"";
		
		return value;
	}
	
	
	
	public static String getPropetyValues(String key) {
		String value;
		Properties prop = new Properties();
		try
		{
		InputStream input = new FileInputStream(propertiesFilePath);
		prop.load(input);
		}
		catch(Exception e)
		{
			System.err.println("--------------------------------------------------------------------------------------------------");
			System.err.println("Please Check The path of Properties file: Provided Path is: "+propertiesFilePath);
			System.err.println("--------------------------------------------------------------------------------------------------");
			System.out.println(e.toString());	
			System.exit(0);
		}
		
		value = prop.getProperty(key);
		return value;
	}

}
