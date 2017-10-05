package PageObject;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class WebSDKPageObject {
	WebDriver driver=null;
	ExtentTest test;
	
	public WebSDKPageObject(WebDriver driver, ExtentTest test){
		this.driver=driver;
		this.test=test;
		PageFactory.initElements(driver,this);
		driver.manage().timeouts().implicitlyWait(35, TimeUnit.SECONDS);
	}
	@FindBy(xpath="//*[@type='submit']")
	private WebElement proceed;
	
	@FindBy(xpath="//input[@name='amount']")
	private WebElement amt;
	
	@FindBy(id="guestFullName")
	private WebElement name;
	
	@FindBy(id="cardNumber")
	private WebElement cardNumber;
	
	@FindBy (id="registerButton")
	private WebElement next;
	
	
	
	@FindBy(id="expMM")
	private WebElement expMM;
	
	@FindBy(id="expYYYY")
	private WebElement expYYYY;
	
	@FindBy(id="guestMobile")
	private WebElement mobile;
	
	@FindBy(id="guestEmail")
	private WebElement email;
	
	@FindBy(xpath="//tr[8]/td[2]")
	private WebElement txnStatus;
	
	
	public void enterName(String guestName){
		name.sendKeys(guestName);
		test.log(LogStatus.PASS, "Entering user full name");
	}
	
	public void enterCard(String cardNo) {
		cardNumber.sendKeys(cardNo);
		test.log(LogStatus.PASS, "Entering card no:");
	}
	
	public void selectExpiry(String mm , String yyyy){
		Select expmm = new Select(expMM);
		Select expyy = new Select(expYYYY);
		test.log(LogStatus.PASS, "Entering exp month");
		expmm.selectByValue(mm);
		test.log(LogStatus.PASS, "Entering exp year");
		expyy.selectByValue(yyyy);


	}
	
	public void clickOnCOntinue(){
		next.click();
		test.log(LogStatus.PASS, "Proceeding to 3ds");
	}
	
	public void submit(){
		proceed.click();
		test.log(LogStatus.FAIL, "Proceeding to next page");
	}
	
	public void enterEmail(String mailID){
		email.sendKeys(mailID);
	}
	
	public void enterMob(String mob){
		mobile.sendKeys(mob);
	}
	
	public String verifyTxnStatus(){
		/*if(txnStatus.getText()=="051")
			test.log(LogStatus.PASS, "Payment is success");
		else
			test.log(LogStatus.FAIL, "Payment is failed");*/
		return txnStatus.getText();
		
	}
	
	
	
	
	
	

}
