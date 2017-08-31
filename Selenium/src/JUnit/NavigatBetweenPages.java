package JUnit;


import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NavigatBetweenPages {
	WebDriver driver;
	String BaseUrl="https://wallet.pc.enstage-sas.com/sampleMerchant/wPay/";



	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.firefox.bin", "D://Program Files (x86)//Mozilla Firefox//firefox.exe");
		System.setProperty("webdriver.gecko.driver", "D://Prathyush//Work//Automation//UdemyTutorial/geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get(BaseUrl);
	}

	@Test
	public void tearDown() throws Exception {
		Thread.sleep(3000);
		driver.navigate().to("https://wallet.pc.enstage-sas.com/sampleMerchant/wPay/startTxn.jsp");
		Thread.sleep(3000);
		driver.navigate().back();
		Thread.sleep(3000);
		driver.navigate().forward();
		Thread.sleep(3000);
		driver.navigate().refresh();
	}

	@After
	public void test() {
		driver.quit();
	}

}
