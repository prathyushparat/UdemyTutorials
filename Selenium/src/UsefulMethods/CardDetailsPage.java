package UsefulMethods;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CardDetailsPage {
	WebDriver driver;
	public CardDetailsPage(WebDriver driver){
		this.driver=driver;
	}
	
	public WebElement geteElement(String locator, String type){
		type=type.toLowerCase();
		if(type=="id")
			return(driver.findElement(By.id(locator)));
		else if(type=="name")
			return(driver.findElement(By.name(locator)));
		
		else
			System.out.println("Invalid attribute");
			return null;
	}
	
	public List<WebElement> geteElementList(String locator, String type){
		type=type.toLowerCase();
		if(type=="id")
			return(driver.findElements(By.id(locator)));
		else
			System.out.println("Invalid attribute");
			return null;
	}
	
	public boolean isElementPresent(String locator, String type){
		List<WebElement> exists = geteElementList(locator, type);
		int size = exists.size();
		if(size==0){
			System.out.println("Element exists");
			return true;
		}	
		else{
			System.out.println("element does NOT exists");
			return false;
		}
			
		
		
	}

}
