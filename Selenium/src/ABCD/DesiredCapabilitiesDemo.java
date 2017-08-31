package ABCD;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DesiredCapabilitiesDemo {

	public static void main(String[] args) {
		
		System.setProperty("webdriver.firefox.bin", "D://Program Files (x86)//Mozilla Firefox//firefox.exe");
		System.setProperty("webdriver.gecko.driver", "D://Prathyush//Work//Automation//UdemyTutorial/geckodriver.exe");
		WebDriver driver;
		DesiredCapabilities cp = DesiredCapabilities.firefox();
		cp.setBrowserName("firefox");
		cp.setPlatform(Platform.WINDOWS);
		driver = new FirefoxDriver(cp);
		driver.manage().window().maximize();
		driver.get("https://www.google.com");
		
	}

}
