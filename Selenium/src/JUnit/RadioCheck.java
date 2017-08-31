package JUnit;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class RadioCheck {

	WebDriver driver;

	@Before
	public void setUp() throws Exception {
		
		String Baseurl="https://letskodeit.teachable.com/p/practice";
		System.setProperty("webdriver.firefox.bin", "D://Program Files (x86)//Mozilla Firefox//firefox.exe");
		System.setProperty("webdriver.gecko.driver", "D://Prathyush//Work//Automation//UdemyTutorial//geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get(Baseurl);
		//driver.manage().window().maximize();
		
	}
	

	@Test
	public void test() throws InterruptedException {
		WebElement bmwradiobtn =driver.findElement(By.id("bmwradio"));
		bmwradiobtn.click();
		Thread.sleep(2000);
		WebElement benzradiobtn =driver.findElement(By.id("benzradio"));
		benzradiobtn.click();
		WebElement bmwcheckbox = driver.findElement(By.id("bmwcheck"));
		bmwcheckbox.click();
		WebElement benzcheckbox = driver.findElement(By.id("benzcheck"));
		benzcheckbox.click();
		
		System.out.println("BMW radio button selected " + bmwradiobtn.isSelected());


	}
	
	@After
	public void tearDown() throws Exception {
		
	}

}
