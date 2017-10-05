package TestClasses;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestNG_DataProviders {
	
	@DataProvider(name="inputs")
	public Object[][] getData(){
		return new Object[][]{
				{"bmw","x6"},
				{"audi","a6"},
				{"benz","c300"}
		};
	}
  @Test(dataProvider="inputs")
  public void inputs(String input1, String input2) {
		System.out.println(input1);
		System.out.println(input2);
  }
}
