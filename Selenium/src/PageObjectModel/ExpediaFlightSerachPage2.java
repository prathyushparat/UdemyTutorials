package PageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ExpediaFlightSerachPage2 {
	WebDriver driver;
	
	@FindBy(id="flight-type-one-way-label")
	
		WebElement oneWay;
	
	@FindBy(id="flight-origin")
	
	WebElement originPlace;
	
	@FindBy(id="flight-destination")
	
	WebElement destinationPlace;
	
	@FindBy(id="flight-departing")
	
	WebElement dateField;
	
	@FindBy(id="search-button")
	
	WebElement searchButton;
	
	public ExpediaFlightSerachPage2(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}	

	
	public  void clickOnSearchButton(WebDriver driver){
		searchButton.click();
	}
	
	public  void clickOnOneWayButton(WebDriver driver){
	
		oneWay.click();
	}
	
	public  void fillOrigin(String origin){
		originPlace.sendKeys(origin);
	}
	
	public  void fillDestination(String destination){
		destinationPlace.sendKeys(destination);
	}
	
	public  void fillDate(String date){
		dateField.sendKeys(date);
	}
	
	

}
