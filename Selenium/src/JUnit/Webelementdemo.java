package JUnit;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Webelementdemo {
	WebDriver driver;

	
		

	@Test
	public void test() throws Exception {
		String Baseurl="https://www.google.co.in";

		System.setProperty("webdriver.firefox.bin", "D://Program Files (x86)//Mozilla Firefox//firefox.exe");
		System.setProperty("webdriver.gecko.driver", "D://Prathyush//Work//Automation//UdemyTutorial//geckodriver.exe");
		driver = new FirefoxDriver();

		driver.get(Baseurl);
		WebElement e1,e2, e3;
		e1=driver.findElement(By.id("gs_htif0"));
		System.out.println("The state of the element is "+ e1.isEnabled());
		e2=driver.findElement(By.id("gs_taif0"));
		System.out.println("The state of the element is "+ e2.isEnabled());
		e3=driver.findElement(By.id("lst-ib"));
		System.out.println("The state of the element is "+ e3.isEnabled());

	}
	
	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

}
