package JUnit;

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
public class WebSDK1 {
	WebDriver driver;
	String Baseurl;
	GetOTP dvc = new GetOTP();
	
	@FindBy (xpath="//button[@type='submit']")
	WebElement submit;
	
    	

	@Before
	public void setUp() throws Exception {
		Baseurl="https://wallet.pc.enstage-sas.com/sampleMerchant/wPay/";
		System.setProperty("webdriver.firefox.bin", "D://Program Files (x86)//Mozilla Firefox//firefox.exe");
		System.setProperty("webdriver.gecko.driver", "D://Prathyush//Work//Automation//UdemyTutorial//geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get(Baseurl);
		PageFactory.initElements(driver, this);
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


	}

	
	@Test
	//Registered user corp card 3ds

	public void test() throws InterruptedException {
		WebElement mobno = driver.findElement(By.name("cust_mobile_number"));
		mobno.sendKeys("9986939965");
		//WebElement submit = driver.findElement(By.xpath("//button[@type='submit']"));
		submit.click();
		WebElement checkout = driver.findElement(By.xpath("//input[@type='submit']"));
		checkout.click();
		Thread.sleep(5000);
		//WebElement submit2 = driver.findElement(By.xpath("//button[@type='submit']"));

		boolean skip = driver.findElement(By.xpath("//button[@type='submit']")).isDisplayed();
		System.out.println("Skip proceed here displayed? " + skip);
		if(driver.findElement(By.xpath("//button[@type='submit']")).isDisplayed())
			submit.click();

			//driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("input_1")).sendKeys("1111");
//		WebElement submit3 = driver.findElement(By.xpath("//button[@type='submit']"));
//		submit3.click();
		submit.click();
		String DVC=dvc.otp();
		driver.findElement(By.id("input_2")).sendKeys(DVC);
//		WebElement submit4 = driver.a
//		WebElement submit4 = driver.findElement(By.xpath("//button[@type='submit']"));
//		submit4.click();
		submit.click();
		System.out.println("clicked on dvc submit");
		WebElement cardList = driver.findElement(By.name("selectCard"));
		Select sel = new Select(cardList);
		sel.selectByVisibleText("4329 09XX XXXX XX85 - Visa-9785");
		submit.click();
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
