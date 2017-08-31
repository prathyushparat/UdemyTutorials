package ABCD;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Sleeper;

public class ChromeDriverDemo {

	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "D:\\Prathyush\\Work\\Automation\\UdemyTutorial\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://www.google.com/");
		driver.findElement(By.id("lst-ib")).sendKeys("irctc");
		driver.findElement(By.xpath("//*[@id='_fZl']")).click();
		Thread.sleep(3000);
		driver.manage().timeouts();
		driver.findElement(By.linkText("IRCTC ::- Login")).click();
		

	}

}
