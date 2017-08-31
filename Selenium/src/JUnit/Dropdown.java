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

public class Dropdown {
	WebDriver driver;
	String Baseurl="https://letskodeit.teachable.com/p/practice";

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.gecko.driver", "D://Prathyush//Work//Automation//UdemyTutorial//geckodriver.exe");
		System.setProperty("webdriver.firefox.bin", "D://Program Files (x86)//Mozilla Firefox//firefox.exe");
		driver = new FirefoxDriver();
		driver.get(Baseurl);
		
	}
	
	@Test
	public void test() {
		WebElement cars= driver.findElement(By.id("carselect"));
		Select sel = new Select(cars);
	    sel.selectByIndex(2);
	    sel.selectByValue("bmw");
	    sel.selectByVisibleText("Benz");		
		
		List<WebElement> options=sel.getOptions();
		int size=options.size();
		String value;
		for(int i =0;i<size;i++){
			value=options.get(i).getText();
			System.out.println(value);
		}
		
		
	}

	@After
	public void tearDown() throws Exception {
	}

	

}
