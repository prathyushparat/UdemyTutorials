package TestClasses;

import org.testng.annotations.DataProvider;

public class TestNG_TestData {
	
	@DataProvider(name="inputs")
	public Object[][] getdata(){
		return new Object[][]{
			{"bmw","x6"},
			{"audi","a6"},
			{"benz","c300"}
		};
	}

}
