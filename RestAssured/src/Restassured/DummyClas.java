package Restassured;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;

public class DummyClas {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestAssured.baseURI="https://maps.googleapis.com";
		given().
		param("location","-33.8670522,151.1957362").
		param("radius","500").
		param("key","AIzaSyCZ00h0RLQ9JQsPieF9LTLT4EQ_-O-DWhA").
		param("type","restaurant").
		param("keyord","cruise").
		when().
		get("/maps/api/place/nearbysearch/json").
		then().assertThat().statusCode(200).and().contentType(ContentType.JSON);

	}

}
