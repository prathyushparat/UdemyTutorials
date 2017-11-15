package TestClasses;

import java.util.concurrent.TimeUnit;





import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentReportPractice1 {
	private WebDriver driver;
	private String BaseUrl = "http://www.letskodeit.com/";
	ExtentReports report;
	ExtentTest test;
	
	@BeforeMethod
	public void beforeClass(){
		System.setProperty("webdriver.firefox.bin", "D://Program Files (x86)//Mozilla Firefox//firefox.exe");
		System.setProperty("webdriver.gecko.driver", "D://Prathyush//Work//Automation//UdemyTutorial/geckodriver.exe");
		report = new ExtentReports("D:\\Prathyush\\Work\\Automation\\UdemyTutorial\\Selenium\\TestData\\TestReport.html");
		test = report.startTest("Verifying login");
		driver = new FirefoxDriver();
		driver.get(BaseUrl);
		test.log(LogStatus.INFO,"Browser started");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@Test
	public void test1(){
		driver.findElement(By.id("comp-iiqg1vggactionTitle")).click();
		driver.findElement(By.id("signUpDialogswitchDialogLink")).click();
		driver.findElement(By.id("memberLoginDialogemailInputinput")).sendKeys("test@email.com");
		driver.findElement(By.id("memberLoginDialogpasswordInputinput")).sendKeys("abcabc");
		driver.findElement(By.id("memberLoginDialogokButton")).click();
		test.log(LogStatus.INFO, "Verifying login");
		WebElement welcomeText = null;
		
		try {
			welcomeText = driver.findElement(By.xpath("//div[text()='Hello test@email.com']"));
		}
		catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
		}
		
		Assert.assertEquals("Hello test@email.com", welcomeText.getText());
		
		driver.findElement(By.id("comp-iiqg1vggactionTitle")).click();
		
		
	}
	
	@AfterMethod
	public void afterClass(ITestResult testResult){
		int status=testResult.getStatus();
		if(status==1)
			test.log(LogStatus.PASS, "Verified login successfully");
		else
			test.log(LogStatus.FAIL, "Could not verify logout");
		driver.quit();
		report.endTest(test);
		report.flush();
	}
	

}
