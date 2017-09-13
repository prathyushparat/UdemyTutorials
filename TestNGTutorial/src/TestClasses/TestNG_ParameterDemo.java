package TestClasses;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

public class TestNG_ParameterDemo {

	@BeforeClass()
	@Parameters({"browser","platform"})
	public void beforeClass(String browser , String platform) {
		System.out.println("The platform is "+ platform);
		System.out.println("The browser is "+ browser);
	}

	@Test
	@Parameters({"response"})
	public void f(String response) {
		String[] res=response.split(",");
		//System.out.println("The response is "+response);
		for(int i=0;i<res.length;i++)
			System.out.println("The responses are "+res[i]);
	}

	@AfterClass
	public void afterClass() {
	}

}
