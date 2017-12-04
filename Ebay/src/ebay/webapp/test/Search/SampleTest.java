package ebay.webapp.test.Search;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.ebay.webpage.repo.SearchPage;
import com.relevantcodes.extentreports.LogStatus;

public class SampleTest extends BaseClass{
	/*String testCaseID="SRCH_001";
	String TCId=getTestCase(testCaseID);*/
	
			
	
	
	
	@Test
	public void SRCH_001(){
		SearchPage sp = new SearchPage(driver);
		test.log(LogStatus.INFO, "Inputting the item in search field");
		sp.searchItem();
		test.log(LogStatus.INFO, "CLicking on search button");

		
	}

}
