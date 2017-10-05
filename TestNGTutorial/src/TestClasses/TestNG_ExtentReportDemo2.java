package TestClasses;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import PageObject.WebSDKPageObject;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestNG_ExtentReportDemo2 {
	ExtentReports reports;
	ExtentTest test;
	String BaseUrl;
	WebDriver driver=null;
  
  @BeforeMethod
  public void beforeMethod() {
	  System.setProperty("webdriver.firefox.bin", "D://Program Files (x86)//Mozilla Firefox//firefox.exe");
	  System.setProperty("webdriver.gecko.driver", "D://Prathyush//Work//Automation//UdemyTutorial//geckodriver.exe");
	  driver = new FirefoxDriver();
	  reports = new ExtentReports("D:\\Prathyush\\Work\\Automation\\UdemyTutorial\\ExtentReport\\WebSDK-Report.html", true);
	  test = reports.startTest("Verify Payment");
	  test.log(LogStatus.INFO, "Initializing driver");
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  BaseUrl="https://wallet.pc.enstage-sas.com/sampleMerchant/wPay/";
	  driver.get(BaseUrl);
	  test.log(LogStatus.INFO, "Opening merchant web sdk URL");

  }
  
  @Test
  public void VerifyPaymentAndCancel() {
	  WebSDKPageObject wpo = new WebSDKPageObject(driver, test);
	  wpo.submit();
	  wpo.submit();
	  wpo.enterName("Prathyush Parat");
	  wpo.enterCard("4329091207169785");
	  wpo.selectExpiry("08", "2020");
	  wpo.enterEmail("asas@asas.com");
	  wpo.enterMob("6767676767");
	  wpo.clickOnCOntinue();
	  String actual = wpo.verifyTxnStatus();
	  Assert.assertEquals("051", actual);

	  
  }

  @AfterMethod
  public void afterMethod() {
	  reports.endTest(test);
	  reports.flush();
	  
  }

}
