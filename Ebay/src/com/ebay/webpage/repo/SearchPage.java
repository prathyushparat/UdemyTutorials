package com.ebay.webpage.repo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {
	
	public WebDriver driver;
	
	/** ebay landing page product search filed*/
	@FindBy(id="gh-ac")
	private WebElement searchField;
	
	/** ebay landing page product search button*/
	@FindBy(id="gh-btn")
	private WebElement searchButton;
	
	public SearchPage(WebDriver driver) {
		{
			this.driver=driver;
			PageFactory.initElements(driver, this);
		}
	}

	public void searchItem(/*String itemName*/){
		searchField.sendKeys("iPad");
		searchButton.click();
	}
	
	public void extractResult(/*int noOfItems*/){
		//extract the result list based on the no: passed
	}
	

}
