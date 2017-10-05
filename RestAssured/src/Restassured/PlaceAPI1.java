package Restassured;

import org.testng.annotations.Test;

import files.ReusableMethods;
import scala.Equals;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@Test
public class PlaceAPI1 {

	public static void getPlace() {
		RestAssured.baseURI="https://maps.googleapis.com";
		Response res=given().param("location","-33.8670522,151.1957362").
				param("radius","500").
				param("key","AIzaSyCZ00h0RLQ9JQsPieF9LTLT4EQ_-O-DWhA").
				param("type","restaurant").
				param("keyword","cruise").
		when().
				get("/maps/api/place/nearbysearch/json").
				then().
				extract().response();
		JsonPath response = ReusableMethods.rawToJson(res);
		
		int size = response.get("results.size()");
		for(int i=0; i<size;i++){
			
			System.out.println(response.get("results["+i+"].vicinity"));
		}
		System.out.println(response);
		

	}

}