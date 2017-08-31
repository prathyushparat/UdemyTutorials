package Actions;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class MouseHoverCLick {
	WebDriver driver;
	String BaseUrl;
	JavascriptExecutor jse;
	
	

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "D://Prathyush//Work//Automation//UdemyTutorial//chromedriver.exe");
		driver = new ChromeDriver();
		jse = (JavascriptExecutor)driver;
		BaseUrl="https://letskodeit.teachable.com/p/practice";
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@Test
	public void test() throws InterruptedException {
		driver.get(BaseUrl);
		jse.executeScript("window.scrollBy(0,800)");
		Thread.sleep(2000);
		WebElement mainElement = driver.findElement(By.id("mousehover"));
		Actions action = new Actions(driver);
		
		action.moveToElement(mainElement).perform();
		Thread.sleep(2000);
		WebElement subElement = driver.findElement(By.xpath("//div[@class='mouse-hover-content']//a[text()='Top']"));
		action.moveToElement(subElement).click().perform();
	}

	@After
	public void tearDown() throws Exception {
	}

	

}
