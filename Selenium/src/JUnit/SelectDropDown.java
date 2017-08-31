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
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class SelectDropDown {
	WebDriver driver;
	String Baseurl;
	
	@FindBy (xpath = "//button[@type='submit']")
	WebElement submit;
	
	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.gecko.driver", "D://Prathyush//Work//Automation//UdemyTutorial//geckodriver.exe");
		System.setProperty("webdriver.firefox.bin", "D://Program Files (x86)//Mozilla Firefox//firefox.exe");
		Baseurl="https://wallet.pc.enstage-sas.com/sampleMerchant/wPay/";
		driver= new FirefoxDriver();
		driver.get(Baseurl);
		PageFactory.initElements(driver, this);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@Test
	public void test() {
		
		submit.click();
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		WebElement phNo = driver.findElement(By.id("input_0"));
		phNo.sendKeys("4444555566");
		WebElement eMail = driver.findElement(By.id("input_1"));
		eMail.sendKeys("zyzy@zyz.com");
		submit.click();
		WebElement expDate = driver.findElement(By.name("expriyDate"));
		Select sel = new Select(expDate);
		List<WebElement> months = sel.getOptions();
		for(WebElement i : months)
			System.out.println(i.getText());
		
	}

	@After
	public void tearDown() throws Exception {
	}

	

}
