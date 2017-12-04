package SearchTestCases;

import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import SearchPageObject.SearchPage;

import com.relevantcodes.extentreports.LogStatus;

public class SRCH_001 extends BaseClass{
	
	String testCaseID="SRCH_001";
	String TCId=getTestCase(testCaseID);
	@Test
	public void SRCH_001(){
		String testData=getTestData(TCId);
		int count=Integer.parseInt(testData.split(",")[1]);
		String item=testData.split(",")[0];
		SearchPage sp = new SearchPage(driver);
		test.log(LogStatus.INFO, "Inputting the item in search field");
		sp.searchItem(item);
		test.log(LogStatus.INFO, "Clicking on search button");
		sp.extractResult(count);
		//sp.sortBy();
		
	}

}
