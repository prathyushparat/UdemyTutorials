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

	/** ebay landing page product search filed */
	@FindBy(id = "gh-ac")
	private WebElement searchField;

	/** ebay landing page product search button */
	@FindBy(id = "gh-btn")
	private WebElement searchButton;

	@FindBy(id = "DashSortByContainer")
	private WebElement sortOption;

	@FindBy(xpath = "//li[@class='dropdown small']")
	private WebElement sortOption1;

	@FindBy(xpath = "//ul[@id='ListViewInner']//h3[@class='lvtitle']")
	private WebElement productName;

	@FindBy(xpath = "//ul[@class='lvprices left space-zero']//li[@class='lvprice prc']")
	private WebElement productPrice;

	@FindBy(xpath = "//ul[@id='SortMenu']//li[3]")
	private WebElement sortLowToHigh;

	@FindBy(xpath = "//ul[@id='SortMenu']//li[4]")
	private WebElement sortHighToLow;

	public SearchPage(WebDriver driver) {
		{
			this.driver = driver;
			PageFactory.initElements(driver, this);
		}
	}

	public void searchItem(String itemName) {

		searchField.sendKeys(itemName);
		searchButton.click();
	}

	public List extractResult(int count, String order) {
		String name;
		String price;

		for (int i = 1; i <= count; i++) {
			name = driver
					.findElement(
							By.xpath("//ul[@id='ListViewInner']//li[contains(@class,'sresult lvresult clearfix li')]["
									+ i + "]//h3[@class='lvtitle']")).getText();
			price = driver
					.findElement(
							By.xpath("//ul[@id='ListViewInner']//li[contains(@class,'sresult lvresult clearfix li')]["
									+ i
									+ "]//ul[contains(@class,'lvprices left space-zero')]//li[@class='lvprice prc']"))
					.getText();
			l1.add(name);
			l1.add(price);

		}
		test.log(LogStatus.INFO, "No: of items is " + l1.size()/2);
				return l1;

	}

	/**
	 * Method to validate if the price wise sorting
	 * @param order
	 */
	public void validateSorting(String order) {
		double costOfPreviousItem = 0, costCurrentItem=0;

		if (order.equals("HighToLow")) {
			
			for (int i = 1; i < l1.size(); i+=2) {
				if (l1.get(i).toString().contains("$")) {
					costCurrentItem = Double.parseDouble(l1.get(i).toString()
							.replace("$", "").replace(",", ""));
					System.out.println("Current item cost is "
							+ costCurrentItem);
					if (i > 1) {
						costOfPreviousItem = Double.parseDouble(l1.get(i - 2)
								.toString().replace("$", "").replace(",", ""));
						System.out.println("Previous item cost is "
								+ costOfPreviousItem);

						Assert.assertTrue(costOfPreviousItem >= costCurrentItem,
								"Not in " + order + " order");
					}

				}
			}
			
		} else if (order.equals("LowToHigh")) {
			
			for (int i = 1; i < l1.size(); i+=2) {
				if (l1.get(i).toString().contains("$")) {
					costCurrentItem = Double.parseDouble(l1.get(i).toString()
							.replace("$", "").replace(",", ""));
					System.out.println("Current item cost is "
							+ costCurrentItem);
					if (i > 1) {
						costOfPreviousItem = Double.parseDouble(l1.get(i - 2)
								.toString().replace("$", "").replace(",", ""));
						System.out.println("Previous item cost is "
								+ costOfPreviousItem);

						Assert.assertTrue(costOfPreviousItem<=costCurrentItem, "Not in "+order+" order");

					}

				}
			}
			
		}

	}
/**
 * Sorts the result based on desired sorting order 
 * @param order
 * @return
 */
	public void sortBy(String order) {
		if (order.equalsIgnoreCase("LowToHigh")) {

			sortOption.click();
			sortLowToHigh.click();
		} else if (order.equalsIgnoreCase("HighToLow")) {
			sortOption.click();
			sortHighToLow.click();
		} 

	}

}
