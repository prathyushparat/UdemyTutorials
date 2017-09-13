package TestClasses;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

public class TestNG_Parallel2 {
	
  @Test
  public void testMethod1() throws InterruptedException {
	  System.out.println("Parallel 2 --> Test method 1");
	  Thread.sleep(6000);
	  System.out.println("Parallel 2 --> Test method 1");

  }
  
  @Test
  public void testMethod2() throws InterruptedException {
	  System.out.println("Parallel 2 --> Test method 2");
	  Thread.sleep(6000);
	  System.out.println("Parallel 2 -->");
  }

}
