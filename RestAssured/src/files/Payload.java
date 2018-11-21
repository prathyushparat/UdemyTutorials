package files;

import TestPackage.WibmoAPI.getSalt;
import TestPackage.WibmoAPI.AllInOneLogin;
import TestPackage.WibmoAPI.CreatePWD;

public class Payload {
	
	public static String loginBody(){
		
		String body = ""+
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
   //"\"password\":\""+AllInOneLogin.pwd+"\","+ 
   "\"salt\":\""+getSalt.salt+"\","+
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
"}";
		return body;
	}
	
	public static String completeLoginBody(){
		String completeLogin = ""+
"{"+  
   "\"clientInfo\":{"+  
      "\"clientDeviceName\":\"AndriodApp @ motorola XT1225 - Vodafone IN\","+
      "\"clientMaker\":\"motorola\","+
      "\"clientModel\":\"XT1225\","+
      "\"clientOsName\":\"Android\","+
      "\"clientOsVersion\":\"5.0.2\","+
      "\"clientDeviceType\":3"+
   "},"+
   "\"dvc\":\"103019\","+
   "\"gpsInfo\":{"+  
      "\"accuracy\":0.0,"+
      "\"gpsTime\":0,"+
      "\"latitude\":0.0,"+
      "\"longitude\":0.0"+
   "},"+
   "\"simAndDeviceData\":{"+  
      "\"deviceData1\":\"zoklBWqgx5agU+s5I7mDMiCFqIKbHgszRuUuJAQ8CXs=\","+
      "\"deviceData2\":\"DES+n3lIlX2wc6HyTCZrB1CLEn7AMLMoJprQUtchOqc=\","+
      "\"deviceData3\":\"NeYujrnWV1k3N+dm11rz4OdhI0fSU7T3ejJkgqjsSf8=\","+
      "\"deviceData4\":\"3uZIZQX5wO9c0ZG+UsyKRYDUri+KHA+zPh5go4yrp4M=\","+
      "\"deviceId\":\"tdid:62Zm0zrjP80kOILv2YEE7xthhp1UhjWFpNbzm3PhcT4=:4900\","+
      "\"simId\":\"FL41kzjvztwh6TzyugVKRiLrFUEfFQrE+LTZgEa5wB0=:4555\","+
      "\"simOperator\":\"Vodafone IN\","+
      "\"deviceIdType\":3"+
   "},"+
   "\"tempToken\":\"201709IN6019P6nK798Q5H0Nkr0P3L22o\","+
   "\"userId\":\"87101\","+
   "\"username\":\"9986939965\","+
   "\"trustThisDevice\":true"+
"}";
		return completeLogin;
	}

}
