package TestClasses;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

public class TestNG_TimeOut {
	
  @Test(timeOut=100)
  public void f() throws InterruptedException {
	  Thread.sleep(200);
	System.out.println("Test method f");  
  }
  
  @Test(timeOut=300)
  public void b(){
	  System.out.println("Test method b");
  }
  
  @BeforeClass
  public void beforeClass() {
  }

  @AfterClass
  public void afterClass() {
  }

}
