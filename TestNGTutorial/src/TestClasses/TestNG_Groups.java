package TestClasses;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

public class TestNG_Groups {

	

	@BeforeClass//(alwaysRun=true)
	public void beforeClass() {
		System.out.println("Before class");
	}
	
	@Test(groups = {"cars","SUV"})
	public void bmwX5() {
		System.out.println("bmwX5");
	}
	
	@Test(groups = {"cars","sedan"})
	public void audiA6(){
		System.out.println("audiA6");
	}
	
	@Test(groups = {"bikes"})
	public void ninja(){
		System.out.println("ninja");
	}
	
	@Test (groups = {"bikes"})
	public void hondaCBR(){
		System.out.println("hondaCBR");
	}

	@AfterClass//(alwaysRun=true)
	public void afterClass() {
		System.out.println("After class");
	}

}
