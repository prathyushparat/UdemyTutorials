package PageObject;

import javax.xml.ws.Holder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class LoginPage {
	 WebDriver driver=null;
	 ExtentTest test;
	
	public LoginPage(WebDriver driver, ExtentTest test){
		this.driver=driver;
		this.test=test;
	}
	
	public void clickLoginLink(){
		WebElement signupLink = driver.findElement(By.id("comp-iiqg1vggactionTitle"));
		signupLink.click();
		test.log(LogStatus.INFO, "Clicked on signup link");
	}
	
	public void enterUserName(String uname){
		WebElement emailField = driver.findElement(By.xpath("//div[@id='memberLoginDialogemail']//input"));
		emailField.sendKeys(uname);
		test.log(LogStatus.INFO, "Enter email");
	}
	
	public void enterPassword(String pwd){
		WebElement passwordField = driver.findElement(By.xpath("//div[@id='memberLoginDialogpassword']//input"));
		passwordField.sendKeys(pwd);
		test.log(LogStatus.INFO, "Enter password");
	}
	
	public void clickOnGoButton(){
		WebElement goButton = driver.findElement(By.id("memberLoginDialogsubmitButton"));
		goButton.click();
		test.log(LogStatus.INFO, "Clicked Go button");
	}

}
