package SearchTestCases;

import org.testng.annotations.Test;

import SearchPageObject.SearchPage;

import com.relevantcodes.extentreports.LogStatus;

public class TestCode extends BaseClass{
	
	@Test
	public void SRCH_001(){
		SearchPage sp = new SearchPage(driver);
		test.log(LogStatus.INFO, "Inputting the item in search field");
		//sp.searchItem();
		test.log(LogStatus.INFO, "Clicking on search button");
		
		
	}

}
