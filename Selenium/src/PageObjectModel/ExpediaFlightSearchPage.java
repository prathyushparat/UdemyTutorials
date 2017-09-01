package PageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ExpediaFlightSearchPage {
	public WebDriver driver;
	public static WebElement element;
	
	public static WebElement oneWay(WebDriver driver){
		element = driver.findElement(By.id("flight-type-one-way-label"));
		return element;
	}
	
	public static WebElement originPlace(WebDriver driver){
		element = driver.findElement(By.id("flight-origin"));
		return element;
	}
	
	public static WebElement destinationPlace(WebDriver driver){
		element = driver.findElement(By.id("flight-destination"));
		return element;
	}
	
	public static WebElement departingDate(WebDriver driver){
		return element = driver.findElement(By.id("flight-departing"));
	}
	
	public static WebElement searchButton(WebDriver driver)
	{
		return element = driver.findElement(By.id("search-button"));
	}
	
	public static void clickOnSearchButton(WebDriver driver){
		searchButton(driver).click();
	}
	
	public static void clickOnOneWayButton(WebDriver driver){
	
		oneWay(driver).click();
	}
	
	public static void fillOrigin(WebDriver driver , String origin){
		originPlace(driver).sendKeys(origin);
	}
	
	public static void fillDestination(WebDriver driver , String destination){
		destinationPlace(driver).sendKeys(destination);
	}
	
	public static void fillDate(WebDriver driver , String date){
		departingDate(driver).sendKeys(date);
	}
	
	

}
