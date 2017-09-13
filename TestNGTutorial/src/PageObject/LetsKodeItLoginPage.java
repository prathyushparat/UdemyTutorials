package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LetsKodeItLoginPage {
	WebDriver driver;
	// page 1
	@FindBy(xpath = "//a[contains(text(),'Login')]")
	WebElement loginLink;

	// page 2
	@FindBy(id = "user_email")
	WebElement uname;

	@FindBy(id = "user_password")
	WebElement pwd;

	@FindBy(name = "commit")
	WebElement submit;

	public LetsKodeItLoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void navigateToLogin() {
		loginLink.click();
	}

	public void login() {
		uname.sendKeys("prathyushp@gmail.com");
		pwd.sendKeys("abcd");
		submit.click();
	}

	

}
