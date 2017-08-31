package SwitchTo;

import static org.junit.Assert.*;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SwitchFrame {
	WebDriver driver;
	String Baseurl="";
	JavascriptExecutor js;

	@Before
	public void setUp() throws Exception {
		Baseurl="https://letskodeit.teachable.com/p/practice";
		System.setProperty("webdriver.firefox.bin", "D://Program Files (x86)//Mozilla Firefox//firefox.exe");
		System.setProperty("webdriver.gecko.driver", "D://Prathyush//Work//Automation//UdemyTutorial//geckodriver.exe");
		driver=new FirefoxDriver();
		js=(JavascriptExecutor)driver;
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);	
	}

	
	@Test
	public void test() throws InterruptedException {
		/* using java script to open the window & scroll*/
		js.executeScript("window.location='https://letskodeit.teachable.com/p/practice'");

		js.executeScript("window.scrollBy(0,1000);");
		Thread.sleep(2000);

		//driver.switchTo().frame(0); //swicth t o frame by no:
		driver.switchTo().frame("courses-iframe"); //switching to frame by id 
		//driver.switchTo().frame("iframe-name");//by name
		WebElement searchCourse=driver.findElement(By.id("search-courses"));
		searchCourse.sendKeys("python");
		driver.switchTo().defaultContent(); //switching to top window
		js.executeScript("window.scrollBy(0,-1000);");
		Thread.sleep(2000);

		driver.findElement(By.id("name")).sendKeys("testing"); //ensuring switch has happened by acting on a webelement on  the top window
			
		
		
		
		
		
		
	}
	
	@After
	public void tearDown() throws Exception {
		driver.quit();
	}


}
