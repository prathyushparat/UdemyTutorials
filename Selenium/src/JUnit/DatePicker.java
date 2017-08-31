package JUnit;

import static org.junit.Assert.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DatePicker {
	WebDriver driver;
	String Baseurl;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.firefox.bin", "D://Program Files (x86)//Mozilla Firefox//firefox.exe");
		System.setProperty("webdriver.gecko.driver", "D://Prathyush//Work//Automation//UdemyTutorial//geckodriver.exe");
		Baseurl="https://www.expedia.co.in/Flights";
		driver=new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@Test
	public void test() {
		driver.get(Baseurl);
		WebElement departingDate = driver.findElement(By.id("flight-departing"));
		departingDate.click();
		driver.findElement(By.xpath("//table//child::tbody//button[@data-month='7' and @data-day='30']")).click();
	}
	
	@Test 
	public void test2(){
		driver.get(Baseurl);
		WebElement departingDate = driver.findElement(By.id("flight-departing"));
		departingDate.click();
		List<WebElement> availableDates=driver.findElements(By.xpath("//table//tbody//child::button[@data-month='7']"));
		for(WebElement date : availableDates){
			if(date.getText().equals("25")){
				date.click();
				break;
			}
		}
	}
	
	@Test
	public void test3(){
		String partialText = "cok";
		String searchText="Cochin, India (COK-Cochin Intl.)";
		driver.get(Baseurl);
		WebElement flightOrigin = driver.findElement(By.id("flight-origin"));
		flightOrigin.click();
		flightOrigin.sendKeys(partialText);
		WebElement results=driver.findElement(By.className("results"));
		List<WebElement> options = results.findElements(By.xpath("//ul[@class='results']//li"));
		int size = options.size();
		for(int i=0;i<size;i++)
			System.out.println(options.get(i).getText());
		for(int i=0;i<size;i++){
			if(options.get(i).getText().equals(searchText))
				options.get(i).click();
			
		}
			
		
	}

	@After
	public void tearDown() throws Exception {
	}

	

}
