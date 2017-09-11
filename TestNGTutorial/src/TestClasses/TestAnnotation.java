package TestClasses;

import org.testng.Assert;
import org.testng.annotations.Test;

import appcode.SomeClassToTest;

public class TestAnnotation {
  @Test
  public void testMethod1() {
	  SomeClassToTest obj = new SomeClassToTest();
	  int result = obj.add(1, 3);
	  Assert.assertEquals(result, 4);
  }
  
  @Test
  public void addString() {
	  SomeClassToTest obj = new SomeClassToTest();
	  String expected = "Hello World";
	  String result=obj.addString("Hello", "World");
	  Assert.assertEquals(expected, result);
  }
  
  @Test
  public void testMethod3() {
	  SomeClassToTest obj = new SomeClassToTest();
	  int[] expected = {1,2,3};
	  int[] result=obj.numberArray();
	  Assert.assertEquals(expected, result);
  }
}
