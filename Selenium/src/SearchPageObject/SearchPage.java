package SearchPageObject;

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

import JUnit.WebElementList1;

public class SearchPage {
	
	public WebDriver driver;
	List l1 = new ArrayList();
	
	/** ebay landing page product search filed*/
	@FindBy(id="gh-ac")
	private WebElement searchField;
	
	/** ebay landing page product search button*/
	@FindBy(id="gh-btn")
	private WebElement searchButton;
	
	@FindBy(id="//ul[@id='ListViewInner']//li[@class='sresult lvresult clearfix li'][1]")
	private WebElementList1 resultList;
	
	@FindBy(id="DashSortByContainer")
	private WebElement	sortOption;
	
	@FindBy(xpath="//li[@class='dropdown small']")
	private WebElement	sortOption1;
	
	@FindBy(xpath="//ul[@id='ListViewInner']//h3[@class='lvtitle']")
	private WebElement productName;
	
	@FindBy(xpath="//ul[@class='lvprices left space-zero']//li[@class='lvprice prc']")
	private WebElement productPrice;

	
	@FindBy(linkText="https://www.ebay.com/sch/i.html?_from=R40&_sacat=0&_nkw=ipad&_sop=15\" value=\"15\"")
	private WebElement sortLowToHigh;
	
	JavascriptExecutor jse;

	
	public SearchPage(WebDriver driver) {
		{
			this.driver=driver;
			PageFactory.initElements(driver, this);
		}
	}

	public void searchItem(String itemName){
		searchField.sendKeys(itemName);
		searchButton.click();
	}
	
	public void extractResult(int count){
		//int no=9;
		String name;
		String price;
		
		for(int i=1;i<=count;i++){
			name=driver.findElement(By.xpath("//ul[@id='ListViewInner']//li[@class='sresult lvresult clearfix li']["+i+"]//h3[@class='lvtitle']")).getText();
			price=driver.findElement(By.xpath("//ul[@id='ListViewInner']//li[@class='sresult lvresult clearfix li']["+i+"]//ul[@class='lvprices left space-zero']//li[@class='lvprice prc']")).getText();
			l1.add(name);
			l1.add(price);
		}
		
		
		System.out.println(l1);
		
	}
	
	public void sortBy(/*String order*/) {
		
		//sortOption.click();
		//sortLowToHigh.click();
		jse = (JavascriptExecutor)driver;
		Actions action = new Actions(driver);
		
		action.moveToElement(sortOption1).perform();
		
		WebElement subElement = driver.findElement(By.xpath("//ul[@id='SortMenu']//a[text()='Price + Shipping: lowest first']"));
		action.moveToElement(subElement).click().perform();		
	}
	

}
