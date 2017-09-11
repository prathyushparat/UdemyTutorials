package TestPackage.WibmoAPI;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import static io.restassured.RestAssured.given;

@Test
public class getOTP {

	public static void otp() {
		// TODO Auto-generated method stub
		RestAssured.baseURI="https://wallet.pc.enstage-sas.com";
		Response res = given().
					   param("accessData","9986939965").
					   param("programId","6019").
					   param("eventId","5").
					   when().
					   post("/sampleMerchant/getOtp.jsp");
		String response=res.asString();
		System.out.println(response);

	}

}
