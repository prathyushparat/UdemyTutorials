package TestClasses;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import appcode.SomeClassToTest;

public class TestNG_SoftAssert {
 
	@Test
  public void testSoftAssert() {
		SoftAssert sa = new SoftAssert();
		SomeClassToTest obj = new SomeClassToTest();
		int result = obj.add(1, 2);
		int correctExpected = 3;
		int wrongExpected=4;
		sa.assertEquals(wrongExpected, result);
		System.out.println("\nline after 1st assert");
		sa.assertEquals(correctExpected, result);
		System.out.println("\nline after 2nd assert");
		sa.assertAll();
  }
}
