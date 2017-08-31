package UsefulMethods;

import static org.junit.Assert.*;

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

public class GuestCheckoutWithoutRegistration {
	WebDriver driver;
	String Baseurl;
	CardDetailsPage cdp;

	
	@FindBy (xpath="//button[@type='submit']")
	WebElement submit;
	
	@FindBy (xpath="//input[@type='submit']")
	WebElement submit1;
	
	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.gecko.driver", "D://Prathyush//Work//Automation//UdemyTutorial//geckodriver.exe");
		System.setProperty("webdriver.firefox.bin", "D://Program Files (x86)//Mozilla Firefox//firefox.exe");
		driver = new FirefoxDriver();
		cdp = new CardDetailsPage(driver);
		Baseurl="https://wallet.pc.enstage-sas.com/sampleMerchant/wPay/";
		driver.get(Baseurl);
		PageFactory.initElements(driver, this);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		
		
	}
	
	@Test
	public void test() {
		
		submit.click(); //clicking on submit
		submit1.click(); //clicking on checkout
		WebElement phNo = driver.findElement(By.id("input_0"));
		phNo.sendKeys("4444555566");
		WebElement eMail = driver.findElement(By.id("input_1"));
		eMail.sendKeys("zyzy@zyz.com");
		submit.click();
		System.out.println("Clicked on sumit after entering ph no: & email");
		WebElement name=cdp.geteElement("input_2", "id");
		name.sendKeys("Prathyush Parat");
		boolean result1 = cdp.isElementPresent("input_2", "id");
		boolean result2 = cdp.isElementPresent("input_21", "id");

		WebElement cardNo=cdp.geteElement("ccNumber", "name");
		cardNo.sendKeys("4329091207169785");
		WebElement expmonth = cdp.geteElement("expriyDate", "name");
		Select month = new Select(expmonth);
		month.selectByVisibleText("08");
		WebElement expriyYear = cdp.geteElement("expriyYear", "name");
		Select year = new Select(expriyYear);
		year.selectByVisibleText("2023");
		submit.click();
		driver.findElement(By.xpath("//button[@type='button'][1]")).click();
		WebElement pwd = driver.findElement(By.id("txtPassword"));
		pwd.sendKeys("123");
		WebElement submit3ds = driver.findElement(By.id("cmdSubmit"));
		submit3ds.click();
		driver.findElement(By.xpath("html/body/table[1]/tbody/tr[8]/td[1]//following-sibling::td")).getText().contains("000");
		WebElement charge=driver.findElement(By.xpath("//form[@action='https://wallet.pc.enstage-sas.com/sampleMerchant/iap/statusCheck.jsp']//following-sibling::tr[4]//child::td//child::input"));
		charge.click(); //clicking on charge
		
		driver.findElement(By.xpath("html/body")).getText().contains("\"pgStatusCode\" : \"50020\"");
		driver.findElement(By.xpath("html/body")).getText().contains("\"hashVerfied\" : \"true\"");
			
		
		
		
	}

	@After
	public void tearDown() throws Exception {
		
	}

	

}
