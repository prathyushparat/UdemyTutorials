package TestClasses;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

public class TestNG_DisableTest {

	@BeforeClass
	public void beforeClass() {
		System.out.println("Before class");
	}

	@Test(enabled=false)
	public void f() {
		System.out.println("Test method f ");
	}
	
	@Test//(dependsOnMethods = {"f"})
	public void a() {
		System.out.println("Test method a");
	}

	@AfterClass
	public void afterClass() {
		System.out.println("After class");

	}

}
