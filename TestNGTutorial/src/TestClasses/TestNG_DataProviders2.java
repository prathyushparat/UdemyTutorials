package TestClasses;

import org.testng.annotations.Test;

public class TestNG_DataProviders2 {

	@Test(dataProvider = "inputs", dataProviderClass = TestNG_TestData.class)
	public void cars(String input1, String input2) {
		System.out.println(input1);
		System.out.println(input2);
	}
}
