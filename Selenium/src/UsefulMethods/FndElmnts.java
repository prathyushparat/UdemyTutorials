package UsefulMethods;

import static org.junit.Assert.*;
import static UsefulMethods.GenericMethod.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FndElmnts {
	WebDriver driver;
	String Baseurl;
	private GenericMethod gm ;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.firefox.bin", "D://Program Files (x86)//Mozilla Firefox//firefox.exe");
		System.setProperty("webdriver.gecko.driver", "D://Prathyush//Work//Automation//UdemyTutorial/geckodriver.exe");
		driver = new FirefoxDriver();
		Baseurl="https://letskodeit.teachable.com/p/practice";
		gm = new GenericMethod(driver);
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    
	}

	@After
	public void tearDown() throws Exception {
		driver.get(Baseurl);
		WebElement nameField = gm.GetElement("name", "id");
		//WebElement nameField = driver.findElement(By.id("name"));
		nameField.sendKeys("Parat");
	}

	@Test
	public void test() {
		//fail("Not yet implemented");
	}

}
