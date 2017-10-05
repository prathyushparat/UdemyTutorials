package TestClasses;

import java.util.concurrent.TimeUnit;

import org.eclipse.jetty.util.log.Log;
import org.testng.Assert;
import org.testng.ITestResult;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestNG_ExtentReportDemo {
 static ExtentReports reports;
 static ExtentTest test;
 String Baseurl ;
 WebDriver driver;
 
  @BeforeMethod
  public void beforeMethod() {
	  Baseurl="http://www.letskodeit.com/";
	  reports = new ExtentReports("D:\\Prathyush\\Work\\Automation\\UdemyTutorial\\ExtentReport\\Report.html", true);
	  
	  System.setProperty("webdriver.firefox.bin", "D://Program Files (x86)//Mozilla Firefox//firefox.exe");
	  System.setProperty("webdriver.gecko.driver", "D://Prathyush//Work//Automation//UdemyTutorial//geckodriver.exe");
	  test = reports.startTest("Verify Login");
	  driver = new FirefoxDriver();

	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  test.log(LogStatus.INFO,"Initiazing driver");
  }
  
  @Test
  public void loginLetsKodeIt() throws InterruptedException {
	  driver.get(Baseurl);
	  test.log(LogStatus.INFO, "Opening the URL"+Baseurl);
	  WebElement loginLink = driver.findElement(By.id("comp-iiqg1vggactionTitle"));
	  loginLink.click();
	  test.log(LogStatus.INFO, "Clicking on login/logout link");

	  WebElement loginLink2=driver.findElement(By.id("signUpDialogswitchDialogLink"));
	  loginLink2.click();
	  test.log(LogStatus.INFO, "Clicking on login link");
	  
	  WebElement uname = driver.findElement(By.id("memberLoginDialogemailInputinput"));
	  uname.sendKeys("test@email.com");
	  test.log(LogStatus.INFO, "Entered username"+uname);

	  
	  WebElement pwd = driver.findElement(By.id("memberLoginDialogpasswordInputinput"));
	  pwd.sendKeys("abcabc");
	  test.log(LogStatus.INFO, "Entered password"+pwd);

	  
	  WebElement submit = driver.findElement(By.id("memberLoginDialogoklink"));
	  submit.click();
	  test.log(LogStatus.INFO, "clicking on Go");

	  Thread.sleep(3000);
	  WebElement logout = driver.findElement(By.id("comp-iiqg1vggactionTitle"));
	  logout.click();
	  test.log(LogStatus.INFO, "clicking on logout");

	  Thread.sleep(3000);

	  Assert.assertFalse(logout.getText().contains("Logout"));
	  test.log(LogStatus.INFO, "Logged out successfully");

	  
  }

  @AfterMethod
  public void afterMethod(ITestResult testResult)
  {
	int result = testResult.getStatus();
	if(result==1)
		test.log(LogStatus.PASS, "Logged out successfully");
	else
		test.log(LogStatus.FAIL, "Logout failed");
	  reports.endTest(test);

	  reports.flush();
  }

}
