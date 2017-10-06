package ABCD;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class ExpediaWebDriverListenerDemo {

	public static void main(String[] args) {
		String baseurl = "https://expedia.co.in/";
		System.setProperty("webdriver.gecko.driver", "D://Prathyush//Work//Automation//UdemyTutorial//geckodriver.exe");
		System.setProperty("webdriver.firefox.bin", "D://Program Files (x86)//Mozilla Firefox//firefox.exe");
		WebDriver driver=new FirefoxDriver();
		EventFiringWebDriver eDriver = new EventFiringWebDriver(driver);
		HandleEvents he = new HandleEvents();
		eDriver.register(he);
		eDriver.get(baseurl);
		eDriver.findElement(By.id("tab-flight-tab")).click();
		
	}

}
