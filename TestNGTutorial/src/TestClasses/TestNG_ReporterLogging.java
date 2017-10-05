package TestClasses;

import junit.framework.Assert;

import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestNG_ReporterLogging {
 
	@BeforeClass
	public void setUp(){
		Reporter.log("Runs once before class", true);
	}
	
	@AfterClass
	public void cleanUp(){
		Reporter.log("Runs after class", true);
	}
	
	@Test
  public void testMethod1() {
		Reporter.log("This is test method 1", true);
		Assert.assertTrue(true);;
  }
	
	@Test
	public void testMethod2(){
		Reporter.log("This is test method 2", true);
		Assert.assertTrue(false);
	}
	
	@Test(dependsOnMethods="testMethod2")
	public void testMethod3(){
		Reporter.log("This is test method 3", true);
	}
}
