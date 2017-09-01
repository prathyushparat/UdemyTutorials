package PageObjectModel;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class OneWayFlightSearchTestCase {
	WebDriver driver;
	String Baseurl;
	ExpediaFlightSerachPage2 exp; //creating page factory object

	@Before
	public void setUp() throws Exception {

		System.setProperty("webdriver.firefox.bin", "D://Program Files (x86)//Mozilla Firefox//firefox.exe");
		System.setProperty("webdriver.gecko.driver", "D://Prathyush//Work//Automation//UdemyTutorial//geckodriver.exe");
		Baseurl="https://www.expedia.co.in/Flights";
		driver = new FirefoxDriver();
		exp = new ExpediaFlightSerachPage2(driver); //initializing page factory object
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}
	
	@Test
	public void test() {
		driver.get(Baseurl);
		exp.clickOnOneWayButton(driver);
		exp.fillOrigin("New York, NY, United States (NYC-All Airports)");
		exp.fillDestination("Chicago, IL, United States (CHI-All Airports)");
		exp.fillDate("10/10/2017");
		exp.clickOnSearchButton(driver);
		
		//ExpediaFlightSearchPage exp  = new ExpediaFlightSearchPage();
		/*Without page factory implementation. Uncomment the below lines for lecture 122 code
		 * exp.clickOnOneWayButton(driver);
		exp.fillOrigin(driver, "New York, NY, United States (NYC-All Airports)");
		exp.fillDestination(driver, "Chicago, IL, United States (CHI-All Airports)");
		exp.fillDate(driver, "10/10/2017");
		exp.clickOnSearchButton(driver);
		*/System.out.println("Clicked on searchButton");
	}

	@After
	public void tearDown() throws Exception {
	}

	

}
