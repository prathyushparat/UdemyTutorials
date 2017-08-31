package JUnit;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class HiddenElements {
	WebDriver driver;
	String Baseurl="https://letskodeit.teachable.com/p/practice";
	String Baseurl2 = "https://www.expedia.co.in/Flights";

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.gecko.driver", "D://Prathyush//Work//Automation//UdemyTutorial//geckodriver.exe");
		System.setProperty("webdriver.firefox.bin", "D://Program Files (x86)//Mozilla Firefox//firefox.exe");
		driver = new FirefoxDriver();
		
	}
	
	@Test //testing letskodeit site
	public void testLetsKodeIt(){
		driver.get(Baseurl);
		boolean isDisplayed=false;
		WebElement textfield = driver.findElement(By.id("displayed-text"));
		isDisplayed = textfield.isDisplayed();
		System.out.println("The text field displayed :" + isDisplayed);
		WebElement hidebutton = driver.findElement(By.id("hide-textbox"));
		hidebutton.click();
		System.out.println("The text field is displayed :" + textfield.isDisplayed());
		WebElement showbutton = driver.findElement(By.id("show-textbox"));
		showbutton.click();
		System.out.println("The text field is displayed :" + textfield.isDisplayed());

	}
	
	@Test
	public void testExpedia () {//testing letskodeit site
		driver.get(Baseurl2);
		WebElement childAge = driver.findElement(By.id("flight-age-select-1"));
		WebElement noOfChildren = driver.findElement(By.id("flight-children"));
		boolean isDisplayed=false;
		isDisplayed=childAge.isDisplayed();
		System.out.println("Child age field displayed :" + isDisplayed);
		
		
	}
	
	@Test
	public void testExpedia2 () {//testing letskodeit site when element available
		driver.get(Baseurl2);
		WebElement noOfChildren = driver.findElement(By.id("flight-children"));
		Select sel = new Select(noOfChildren);
		sel.selectByVisibleText("2");
		WebElement childAge = driver.findElement(By.id("flight-age-select-1"));
		boolean isDisplayed=false;
		isDisplayed=childAge.isDisplayed();
		System.out.println("Child age field displayed :" + isDisplayed);
		
		
	}

	
	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	

}
