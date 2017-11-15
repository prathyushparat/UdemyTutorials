package TestPackage.WibmoAPI;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class GetSalt2 {
	@Test
	public void generateSalt(){
		RestAssured.baseURI="https://api.pc.enstage-sas.com";
		Response res = given().param("username","9986939965").
						when().
						post("/in/user/auth/6019/salt")
						;
		given().param("username","9986939965").
		when().
		post("/in/user/auth/6019/salt").
		//then().statusCode(200).and().contentType(ContentType.XML);
		then().statusCode(200).and().contentType(ContentType.JSON);
		String r = res.asString();
		JsonPath jp = new JsonPath(r);
		String salt = jp.getJsonObject("salt");
		System.out.println("Salt is "+salt);
		//System.out.println(r);
		RestAssured.baseURI="https://wallet.pc.enstage-sas.com";
		res=
		given().
				param("password","1111").
				param("salt",salt).
		when().
				post("/sampleMerchant/passwordEncrypt.jsp");
		String re = res.asString();
		jp = new JsonPath(re);
		String pwd = jp.getString("encPwd");
		System.out.println("The encrypted password is "+pwd);
		
		RestAssured.baseURI = "https://api.pc.enstage-sas.com";
		res = given().log().all().body(""+
				"{"+  
				   "\"clientInfo\":{"+  
				      "\"clientDeviceName\":\"AndriodApp @ motorola XT1225 - Vodafone IN\","+
				      "\"clientMaker\":\"motorola\","+
				      "\"clientModel\":\"XT1225\","+
				      "\"clientOsName\":\"Android\","+
				      "\"clientOsVersion\":\"5.0.2\","+
				      "\"clientDeviceType\":3"+
				   "},"+
				   "\"gpsInfo\":{"+  
				      "\"accuracy\":0.0,"+
				      "\"gpsTime\":0,"+
				      "\"latitude\":0.0,"+
				      "\"longitude\":0.0"+
				   "},"+
				   "\"password\":\""+pwd+"\","+ 
				   "\"salt\":\""+salt+"\","+
				   "\"simAndDeviceData\":{"+  
				      "\"deviceData1\":\"zoklBWqgx5agU+s5I7mDMiCFqIKbHgszRuUuJAQ8CXs=\","+
				      "\"deviceData2\":\"DES+n3lIlX2wc6HyTCZrB1CLEn7AMLMoJprQUtchOqc=\","+
				      "\"deviceData3\":\"NeYujrnWV1k3N+dm11rz4OdhI0fSU7T3ejJkgqjsSf8=\","+
				      "\"deviceData4\":\"3uZIZQX5wO9c0ZG+UsyKRYDUri+KHA+zPh5go4yrp4M=\","+
				      "\"deviceId\":\"tdid:62Zm0zrjP80kOILv2YEE7xthhp1UhjWFpNbzm3PhcT4=:4900\","+
				      "\"deviceIdProof\":\"UFV4wtZ/gAZ1cQq0j9gzFgi47T1M/NUhPxTNTt5fFgyCV56clBFY1IDkv6tT6JcQGjcpR/Ij+54FO9Wde47IAQ==\","+
				      "\"simId\":\"FL41kzjvztwh6TzyugVKRiLrFUEfFQrE+LTZgEa5wB0=:4555\","+
				      "\"simOperator\":\"Vodafone IN\","+
				      "\"deviceIdType\":3"+
				   "},"+
				   "\"username\":\"9986939965\","+
				   "\"triggerDvcIfReq\":false,"+
				   "\"appVersion\":3020005"+
				"}")
				.when()
				.post("/in/user/auth/6019/login");
		String response = res.asString();
		System.out.println("Login response is" + response);
			
		
		
	}

}
