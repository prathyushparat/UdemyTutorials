package ABCD;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriver.SystemProperty;
import org.openqa.selenium.support.ui.Select;

public class OpenFFBrowser {
///ng-pristine ng-empty ng-invalid ng-invalid-required ng-touched
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.firefox.bin", "D://Program Files (x86)//Mozilla Firefox//firefox.exe");
		System.setProperty("webdriver.gecko.driver", "D://Prathyush//Work//Automation//UdemyTutorial/geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		String path = "https://wallet.pc.enstage-sas.com/sampleMerchant/wPay/"; //https://www.irctc.co.in/
		driver.get(path);
		driver.findElement(By.name("cust_mobile_number")).sendKeys("9986939965");
		driver.findElement(By.cssSelector("body > form:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(11) > td:nth-child(1) > button:nth-child(1)")).click();
		driver.findElement(By.cssSelector("body > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(21) > td:nth-child(1) > input:nth-child(1)")).click();
		Thread.sleep(3000);
		Select dropdown = new Select(driver.findElement(By.name("app")));
		//driver.findElement(By.className("ng-pristine ng-empty ng-invalid ng-invalid-required ng-touched")).click();
		dropdown.selectByVisibleText("PayZapp");
		driver.findElement(By.cssSelector("button")).click();
		driver.findElement(By.name("wibmoPin")).sendKeys("1111");
		driver.findElement(By.xpath(".//*[@id='body']/md-card/ng-view/div/md-content/form/div/div[2]/button[2]")).click();
		
	}

}
