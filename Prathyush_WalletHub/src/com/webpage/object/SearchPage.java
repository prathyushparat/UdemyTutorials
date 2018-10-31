package com.webpage.object;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;

import SearchTestCases.BaseClass;

public class SearchPage extends BaseClass {

	public WebDriver driver;
	List l1 = new ArrayList();

	/** flipkart landing page product search filed */
	@FindBy(xpath = "//input[@class='LM6RPg']")
	private WebElement searchField;
	
	/**flipkart landing page login box close button	 */
	
	@FindBy(xpath="//button[@class='_2AkmmA _29YdH8']")
	private WebElement closeButton;

	/** flipkart landing page product search button */
	@FindBy(xpath = "//button[@class='vh79eN']")
	private WebElement searchButton;

	/** flipkat item name in search results */
	@FindBy(xpath = "//div[@class='_3wU53n']")
	private WebElement productName;

	@FindBy(xpath = "//li[@class='dropdown small']")
	private WebElement sortOption1;

	@FindBy(xpath = "//div[@class='_1vC4OE _2rQ-NK']")
	private WebElement productPrice;

	/** flipkart sort low to high */
	@FindBy(xpath = "//div[@class='_3ywJNQ']//div[3]")
	private WebElement sortLowToHigh;

	/** flipkart sort high to low */
	@FindBy(xpath = "//div[@class='_3ywJNQ']//div[4]")
	private WebElement sortHighToLow;

	public SearchPage(WebDriver driver) {
		{
			this.driver = driver;
			PageFactory.initElements(driver, this);
		}
	}

	public void searchItem(String itemName) {
		closeButton.click();
		searchField.sendKeys(itemName);
		searchButton.click();
		
	}
	
	 
	public List productList(int count) {
		
		List itemList=null;
		String prdtName=null;
		for(int i=0;i<count;i++) {
			prdtName=(productName+"["+i+"]");
			l1.add(prdtName);
			
		}
		System.out.println(l1);
		return l1;
		
	}

	public void sortBy(String order) {
		if (order.equalsIgnoreCase("LowToHigh")) {

			sortLowToHigh.click();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (order.equalsIgnoreCase("HighToLow")) {
			sortHighToLow.click();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 

	}

	

}
