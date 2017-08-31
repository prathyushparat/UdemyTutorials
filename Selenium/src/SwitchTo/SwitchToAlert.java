package SwitchTo;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SwitchToAlert {
	WebDriver driver;
	String Baseurl;
	

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.firefox.bin", "D://Program Files (x86)//Mozilla Firefox//firefox.exe");
		System.setProperty("webdriver.gecko.driver", "D://Prathyush//Work//Automation//UdemyTutorial//geckodriver.exe");
		Baseurl="https://letskodeit.teachable.com/p/practice";
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@Test
	public void test1() {
		driver.get(Baseurl);
		driver.findElement(By.id("name")).sendKeys("Prathyush");
		driver.findElement(By.id("alertbtn")).click();
		Alert alert = driver.switchTo().alert();
		alert.accept();
		
		
	}
	
	@Test
	public void test2() {
		driver.get(Baseurl);
		driver.findElement(By.id("name")).sendKeys("Prathyush");
		driver.findElement(By.id("confirmbtn")).click();
		Alert alert = driver.switchTo().alert();
		alert.dismiss();
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	

}
