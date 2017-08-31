package JavaScript;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptExecutor;

public class WindowSize {
	String Baseurl="";
	JavascriptExecutor js;
	WebDriver driver;

	@Before
	public void setUp() throws Exception {
		Baseurl="https://www.expedia.co.in/";
		System.setProperty("webdriver.firefox.bin", "D://Program Files (x86)//Mozilla Firefox//firefox.exe");
		System.setProperty("webdriver.gecko.driver", "D://Prathyush//Work//Automation//UdemyTutorial//geckodriver.exe");
		driver = new FirefoxDriver();
		js=(JavascriptExecutor) driver;
		

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@Test
	public void test() {
		js.executeScript("window.location = 'https://www.expedia.co.in/'");
		long height=(Long) js.executeScript("return window.innerHeight");
		long width = (Long) js.executeScript("return window.innerWidth");
		System.out.println(width +" "+ height);
	}

	@After
	public void tearDown() throws Exception {
	}

	

}
