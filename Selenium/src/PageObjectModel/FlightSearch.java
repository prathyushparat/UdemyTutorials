package PageObjectModel;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class FlightSearch {

	static WebDriver driver;
	static String baseurl;
	
	@Before
	protected void setUp() throws Exception {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		baseurl="https://www.expedia.co.in/Flights";
	}
	
	@Test
	public static void searchFlight(){
		driver.get(baseurl);
		ExpediaFlightSearchPage.clickOnOneWaay(driver);
		ExpediaFlightSearchPage.fillUpOrigin(driver, "New York");
		ExpediaFlightSearchPage.fillUpDestination(driver, "Chicago");
		ExpediaFlightSearchPage.fillDate(driver, "10/10/2017");
		ExpediaFlightSearchPage.clickSearch(driver);
		
	}
	@After
	protected void tearDown() throws Exception {
		
	}

}
