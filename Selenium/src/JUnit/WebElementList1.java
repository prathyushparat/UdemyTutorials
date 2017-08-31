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

public class WebElementList1 {
	WebDriver driver;
    String Baseurl; 

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.gecko.driver", "D://Prathyush//Work//Automation//UdemyTutorial//geckodriver.exe");
		System.setProperty("webdriver.firefox.bin", "D://Program Files (x86)//Mozilla Firefox//firefox.exe");
		Baseurl="https://wallet.pc.enstage-sas.com/sampleMerchant/wPay/";
		driver = new FirefoxDriver();
	}
//
	
	@Test
	public void test() {
		driver.get(Baseurl);
		List<WebElement> abc=driver.findElements(By.xpath("//input[@name]"));
		int size=abc.size();
		System.out.println("size of the list is :" + size);
		for(int i=0;i<size;i++){
			String name=abc.get(i).getTagName();
			
			System.out.println(name);
		}
	}
	
	@After
	public void tearDown() throws Exception {
		
	}


}
