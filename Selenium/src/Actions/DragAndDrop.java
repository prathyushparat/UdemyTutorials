package Actions;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import com.sun.xml.internal.ws.wsdl.ActionBasedOperationSignature;

public class DragAndDrop {
	WebDriver driver;
	String baseurl;

	@Before
	public void setUp() throws Exception {
		baseurl="https://jqueryui.com/droppable/";
		System.setProperty("webdriver.chrome.driver", "D://Prathyush//Work//Automation//UdemyTutorial//chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}
	
	@Test
	public void test() {
		driver.get(baseurl);
		driver.switchTo().frame(0);
		WebElement fromElement = driver.findElement(By.id("draggable"));
		WebElement toElement = driver.findElement(By.id("droppable"));
		Actions action = new Actions(driver);
		//action.dragAndDrop(fromElement, toElement).release().build().perform();
		action.clickAndHold(fromElement).moveToElement(toElement).release().build().perform();
	}

	@After
	public void tearDown() throws Exception {
	}

	

}
