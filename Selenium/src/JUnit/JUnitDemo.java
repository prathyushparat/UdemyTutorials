package JUnit;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class JUnitDemo {

	WebDriver driver;
	String BaseUrl;
	GetOTP dvc = new GetOTP();



	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.firefox.bin", "D://Program Files (x86)//Mozilla Firefox//firefox.exe");
		System.setProperty("webdriver.gecko.driver", "D://Prathyush//Work//Automation//UdemyTutorial/geckodriver.exe");
		driver = new FirefoxDriver();
		BaseUrl="https://wallet.pc.enstage-sas.com/sampleMerchant/wPay/";
		driver.get(BaseUrl);
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void test() throws InterruptedException {
	    driver.findElement(By.xpath("//input[@name='cust_mobile_number']")).sendKeys("9986939965");
	    driver.findElement(By.xpath("//button[@type='submit']")).click();
	    driver.findElement(By.xpath("//input[@value='Checkout']")).click();
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	    if(driver.findElement(By.xpath("//button[@type='submit']")).isDisplayed()){
	    	driver.findElement(By.xpath("//button[@type='submit']")).click();
	    }
	    
		driver.findElement(By.xpath("//input[@id='input_1']")).sendKeys("1111");
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.findElement(By.xpath("//button[@type='submit']")).click();
		String DVC=dvc.otp();
		driver.findElement(By.id("input_2")).sendKeys(DVC);
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//button[@class='md-raised md-primary full-button md-button md-ink-ripple']")).click();
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.findElement(By.xpath("//button[@class='md-raised md-primary full-button danger md-button md-ink-ripple']")).click();
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.findElement(By.id(".//*[@id='radio_8']/div[1]/div[1]")).click();
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.findElement(By.xpath(".//*[@id='dialogContent_4']/div/form/button")).click();
	}
	
	@After
	public void tearDown() throws Exception {
	}

}
