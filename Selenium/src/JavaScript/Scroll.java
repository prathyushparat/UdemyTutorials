package JavaScript;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Scroll {
	WebDriver driver;
	JavascriptExecutor js;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.firefox.bin", "D://Program Files (x86)//Mozilla Firefox//firefox.exe");
		System.setProperty("webdriver.gecko.driver", "D://Prathyush//Work//Automation//UdemyTutorial//geckodriver.exe");
		driver = new FirefoxDriver();
		js=(JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}
	
	@Test
	public void test() {
		js.executeScript("window.location='https://letskodeit.teachable.com/p/practice'");
		//Scroll down
		js.executeScript("window.scrollBy(0,1900);");
		//scroll up
		js.executeScript("window.scrollBy(0,-1900);");
		//Scroll to elementview
		
		WebElement element = driver.findElement(By.id("mousehover"));

		js.executeScript("arguments[0].scrollIntoView(true);",element);
		js.executeScript("window.scrollBy(0,-190);");
		
		
	}

	@After
	public void tearDown() throws Exception {
	}

	

}
