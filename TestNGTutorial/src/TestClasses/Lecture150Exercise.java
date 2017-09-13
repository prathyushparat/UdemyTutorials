package TestClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import PageObject.LetsKodeItLoginPage;

public class Lecture150Exercise {
	WebDriver driver;

	@BeforeMethod
	@Parameters({"browser"})
	public void openBrowser(String browser) {
		
System.out.println(browser);
		if (browser.equals("firefox") ) {
			System.setProperty("webdriver.firefox.bin",
					"D://Program Files (x86)//Mozilla Firefox//firefox.exe");
			System.setProperty("webdriver.gecko.driver",
					"D://Prathyush//Work//Automation//UdemyTutorial//geckodriver.exe");
			

			driver = new FirefoxDriver();

		} else if (browser.equals("chrome")){
			System.setProperty("webdriver.chrome.driver",
					"D:\\Prathyush\\Work\\Automation\\UdemyTutorial\\chromedriver.exe");

			driver = new ChromeDriver();
		}
	}

	@Test
	public void successfulLogin() throws InterruptedException {
		driver.get("https://letskodeit.teachable.com/");
		LetsKodeItLoginPage obj = new LetsKodeItLoginPage(driver);
		obj.navigateToLogin();
		Thread.sleep(3000);
		obj.login();
	}
	
	@AfterMethod
	public void closeBrowser(){
		driver.quit();
	}
}
