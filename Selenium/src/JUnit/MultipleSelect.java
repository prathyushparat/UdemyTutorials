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

public class MultipleSelect {
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
		
		WebElement element = driver.findElement(By.id("multiple-select-example"));
		Select sel = new Select(element);
		//Select by index
		sel.selectByIndex(1);
		
		//Delect the selected
		sel.deselectByIndex(1);
		
		//Select by value
		sel.selectByValue("apple");
		
		//Select by visible text
		sel.selectByVisibleText("Peach");
		
		//Print all the select values
		
		List<WebElement> selectedOptions = sel.getAllSelectedOptions();
		for(WebElement i : selectedOptions)
			System.out.println(i.getText());
	}

	@After
	public void tearDown() throws Exception {
	}

	

}
