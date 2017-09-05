package JUnit;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AvailableLinks {
	WebDriver driver;
	String baseurl;

	@Before
	public void setUp() throws Exception {
		baseurl = "https://www.expedia.co.in/Flights";
		System.setProperty("webdriver.firefox.bin", "D://Program Files (x86)//Mozilla Firefox//firefox.exe");
		System.setProperty("webdriver.gecko.driver", "D://Prathyush//Work//Automation//UdemyTutorial//geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@Test
	public void test() {
		driver.get(baseurl);
		
	}
	
	///////////incomplete. could not understand the lecture 125
	public static List<WebElement> clickableLinks(WebDriver driver){
		List<WebElement> licksToClick = new ArrayList<WebElement>();
		List<WebElement> elements = driver.findElements(By.tagName("a"));
		//elements.addAll(driver.findElement(By.tagName("img")));
		return elements;
	}

	@After
	public void tearDown() throws Exception {
	}

	

}
