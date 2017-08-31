package JUnit;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class GetOTP {

	
	
	public static String otp() {
		System.setProperty("webdriver.firefox.bin", "D://Program Files (x86)//Mozilla Firefox//firefox.exe");
		System.setProperty("webdriver.gecko.driver", "D://Prathyush//Work//Automation//UdemyTutorial/geckodriver.exe");
		WebDriver driver = new FirefoxDriver();

		driver.get("https://wallet.pc.enstage-sas.com/sampleMerchant/getOtp.jsp?accessData=9986939965&programId=6019&eventId=5");
		String DVC = driver.findElement(By.xpath("//body")).getText();
		System.out.println("DVC is" + DVC);
		return(DVC);
	}
	

	

}
