package TestClasses;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

import appcode.SomeClassToTest;

public class TestNG_DependsOnDemo {
	SomeClassToTest obj;

	@BeforeClass
	public void beforeClass() {
		obj = new SomeClassToTest();
		System.out.println("Before class");
	}

	@Test(dependsOnMethods = { "testMethod2" })
	public void testMethod1() {
		int result = obj.add(1, 2);
		Assert.assertEquals(result, 4);
	}

	@Test
	public void testMethod2() {
		System.out.println("Test method 2");
	}

	@Test(dependsOnMethods = { "testMethod1" }, alwaysRun=true)
	public void testMethod3() {
		System.out.println("Test method 3");
	}

	@Test
	public void testMethod4() {
		System.out.println("Test method 4");
	}

	@AfterClass
	public void afterClass() {
		System.out.println("After class");
	}

}
