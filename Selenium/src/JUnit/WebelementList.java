package JUnit;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;


public class WebelementList extends SampleMerchantFields {
	WebDriver driver;
	String baseurl="https://letskodeit.teachable.com/p/practice";


	

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.gecko.driver", "D://Prathyush//Work//Automation//UdemyTutorial//geckodriver.exe");
		System.setProperty("webdriver.firefox.bin", "D://Program Files (x86)//Mozilla Firefox//firefox.exe");
		driver = new FirefoxDriver();
	}
	
	@Test
	public void test() throws InterruptedException {
		boolean isEnabled=false;
		driver.get(baseurl);
		List<WebElement> buttons = driver.findElements(By.xpath("//input[@name='cars']"));
		int size = buttons.size();
		
		for(int i = 0;i<size;i++){
			if(!isEnabled){
				Thread.sleep(2000);
				buttons.get(i).click();
			}
		}
	}

	@After
	public void tearDown() throws Exception {
		Thread.sleep(2000);
		driver.quit();
	}

	

}
