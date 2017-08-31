package JUnit;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TakeScreenShot {
	WebDriver driver;
	String baseurl ="";

	@Before
	public void setUp() throws Exception {
		baseurl="https://www.expedia.co.in/Flights";
		System.setProperty("webdriver.firefox.bin", "D://Program Files (x86)//Mozilla Firefox//firefox.exe");
		System.setProperty("webdriver.gecko.driver", "D://Prathyush//Work//Automation//UdemyTutorial//geckodriver.exe");
		driver = new FirefoxDriver();
		
	}
	
	@Test
	public void test() {
		driver.get(baseurl);
		WebElement oneWay = driver.findElement(By.id("flight-type-one-way-label"));

		oneWay.click();

		WebElement fromLocation = driver.findElement(By.id("flight-origin"));
		WebElement toLocation = driver.findElement(By.id("flight-destination"));
		WebElement departingDate = driver.findElement(By.id("flight-departing"));
		fromLocation.sendKeys("New York, NY, United States (JFK-John F. Kennedy Intl.)");
		toLocation.sendKeys("New York, NY, United States (JFK-John F. Kennedy Intl.)");
		WebElement searchButton = driver.findElement(By.id("search-button"));
		searchButton.click();
		
		
	}

	@After
	public void tearDown() throws Exception {
		String fileName = "abc.png";
		String directory = "D://Prathyush//Work//Automation//UdemyTutorial//Screenshots//";
		
		File sourceFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(sourceFile, new File(directory + fileName));
		driver.quit();
	}

	

}
