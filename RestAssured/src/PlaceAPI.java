package Restassured;

import org.testng.annotations.Test;

import scala.Equals;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@Test
public class PlaceAPI {

	public static void getPlace() {
		RestAssured.baseURI="https://maps.googleapis.com";
		given().param("location","-33.8670522,151.1957362").
				param("radius","500").
				param("key","AIzaSyCZ00h0RLQ9JQsPieF9LTLT4EQ_-O-DWhA").
				param("type","restaurant").
				param("keyword","cruise").
		when().
				get("/maps/api/place/nearbysearch/json").
		then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
			body("results[0].name",equalTo("Sydney Showboats"));
		

	}

}
