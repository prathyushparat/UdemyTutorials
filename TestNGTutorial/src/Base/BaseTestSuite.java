package Base;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class BaseTestSuite {
	

	@BeforeClass
	public void beforeClass() {
		System.out.println("BaseTestSuite - Base test before class");
	}

	@AfterClass
	public void afterClass() {
		System.out.println("BaseTestSuite - Base test after class");

	}
	
	@BeforeTest
	public void beforeTest() {
		System.out.println("BaseTestSuite - before test");
	}

	@AfterTest
	public void afterTest() {
		System.out.println("BaseTestSuite - after test");

	}

	@BeforeSuite
	public void beforeSuite() {
		System.out.println("BaseTestSuite - Base test before suite");

	}

	@AfterSuite
	public void afterSuite() {
		System.out.println("BaseTestSuite - Base test after suite");

	}

}
