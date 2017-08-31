import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;



public class Basic {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RestAssured.baseURI = "https://maps.googleapis.com";
		given().
				param("location","-33.8670522,151.1957362").
				param("radius","500").
				param("key","AIzaSyAw4p3DBM_k0Xj_P1wxToFYrtGyX0ZC3kk").
				param("type","restaurant").
				param("keyword","cruise").
		when().
				get("/maps/api/place/nearbysearch/json").
		then().assertThat().statusCode(200).and().contentType(ContentType.JSON);
				
				

	}

}
