package Regression;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Base.BaseTestSuite;

public class TestNG_Class1 extends BaseTestSuite{

	@BeforeClass
	public void tbeBeforeClass(){
		System.out.println("This is before TestNG_Class1 class");
	}
	
	@BeforeMethod
	public void tbeBeforeTest(){
		System.out.println("This is before method");
	}
	
	@Test
	public void testMethod1() {
		System.out.println("This is test method 1");
	}
	
	@Test
	public void testMethod2(){
		System.out.println("This is test method 2");
	}
	
	@AfterMethod
	public void tbeAfterTest(){
		System.out.println("This is after method");
	}
	
	@AfterClass
	public void tbeAfterClass(){
		System.out.println("This is after TestNG_Class1 class");
	}
}
