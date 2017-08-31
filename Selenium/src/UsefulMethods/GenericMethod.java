package UsefulMethods;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GenericMethod {
	WebDriver driver;
	public   GenericMethod(WebDriver driver){
		this.driver=driver;
	}
	public WebElement GetElement(String locator, String type){
		type=type.toLowerCase();
		if(type=="id"){
			return(driver.findElement(By.id(locator)));
		}
		else if(type=="xpath"){
			return(driver.findElement(By.xpath(locator)));
		}
		else
			System.out.println("Wrong attribute");
			return null;
		
	}

}
