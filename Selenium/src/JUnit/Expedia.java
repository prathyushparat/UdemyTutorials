package JUnit;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Expedia {
	WebDriver driver;
	String Baseurl="http://www.expedia.com/";

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.gecko.driver", "D://Prathyush//Work//Automation//UdemyTutorial//geckodriver.exe");
		System.setProperty("webdriver.firefox.bin", "D://Program Files (x86)//Mozilla Firefox//firefox.exe");
		driver = new FirefoxDriver();
		driver.get(Baseurl);
	}
	
	@Test
	public void test() throws InterruptedException {
		WebElement flight = driver.findElement(By.id("primary-header-flight"));
		flight.click(); //clicking on flight header
		//Thread.sleep(5000);
		WebElement Adultno=driver.findElement(By.id("flight-adults"));
		
		Select sel = new Select(Adultno);
		List<WebElement> options = sel.getOptions();
		int size=options.size();
		System.out.println(size);
		for(int i=0;i<size;i++){
			System.out.println(options.get(i).getText());
		}
	}

	@After
	public void tearDown() throws Exception {
	}

	

}
