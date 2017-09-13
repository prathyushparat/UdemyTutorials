package TestClasses;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

public class TestNG_Priority {
	
  @Test(priority=3)
  public void a() {
	  System.out.println("Method a");
  }
  
  @Test(priority=2)
  public void b(){
	  System.out.println("Method b");
  }
  
  @Test(priority=1)
  public void c(){
	  System.out.println("Method c");
  }
  
  @BeforeMethod
  public void beforeMethod() {
	  System.out.println("Runs before method");
  }

  @AfterMethod
  public void afterMethod() {
	  System.out.println("Runs after method");
  }

  @BeforeClass
  public void beforeClass() {
	  System.out.println("Runs before class");
  }

  @AfterClass
  public void afterClass() {
	  System.out.println("Runs after class");

  }

}
