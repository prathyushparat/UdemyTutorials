package TestClasses;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestNG_Test2 {
	WebDriver driver;
	ExtentReports reports;
	ExtentTest test;
	String Baseurl;
  
  @BeforeTest
  public void beforeTest() {
	  Baseurl = "http://www.letskodeit.com/";
	  reports = ExtentReportClass.getInstance();
	  System.setProperty("webdriver.firefox.bin", "D://Program Files (x86)//Mozilla Firefox//firefox.exe");
	  System.setProperty("webdriver.gecko.driver", "D://Prathyush//Work//Automation//UdemyTutorial//geckodriver.exe");
	  test = reports.startTest("Verify login");
	  driver = new FirefoxDriver();
	  test.log(LogStatus.INFO, "Initializing driver");
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  }
  
  @Test
  public void f() throws InterruptedException {
	  driver.get(Baseurl);
	  test.log(LogStatus.INFO, "Opening lets code it site");
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

  }

  @AfterTest
  public void afterTest(ITestResult testResult) {
	  if(testResult.getStatus()==0)
		  test.log(LogStatus.INFO, "Looged out successfully");
	  else
		  test.log(LogStatus.INFO, "Didn't logout successfully");
	  reports.endTest(test);
	  reports.flush();
  }

}
