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

public class SwitchWindow {

	WebDriver driver;
	String Baseurl="";

	@Before
	public void setUp() throws Exception {
		Baseurl="https://letskodeit.teachable.com/p/practice";
		System.setProperty("webdriver.firefox.bin", "D://Program Files (x86)//Mozilla Firefox//firefox.exe");
		System.setProperty("webdriver.gecko.driver", "D://Prathyush//Work//Automation//UdemyTutorial//geckodriver.exe");
		driver=new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);	
	}

	
	@Test
	public void test() {
		driver.get(Baseurl);
		String parentHandle=driver.getWindowHandle();
		System.out.println("Parent handle is: " +parentHandle);
		WebElement openWindow = driver.findElement(By.id("openwindow"));
		openWindow.click();
		Set<String> openHandles=driver.getWindowHandles();
		for(String handle : openHandles)     //get all the open window handle into a set
		{
			//System.out.println(handle);		//printing all the handles
			if(!handle.equals(parentHandle)){	//if the window handle is not parent handle then switch the handle 
				driver.switchTo().window(handle);
				WebElement searchCourse=driver.findElement(By.id("search-courses"));
				searchCourse.sendKeys("python");
			}
		}
		
		driver.switchTo().window(parentHandle); //switching back to parent window 
		driver.findElement(By.id("name")).sendKeys("testing"); //ensuring switch has happened by acting on a webelement on  that windown
//		
		
		
		
	}
	
	@After
	public void tearDown() throws Exception {
		driver.quit();
	}


}
