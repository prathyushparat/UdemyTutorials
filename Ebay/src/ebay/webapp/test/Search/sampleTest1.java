package ebay.webapp.test.Search;

import org.testng.annotations.Test;

import com.ebay.webpage.repo.SearchPage;
import com.relevantcodes.extentreports.LogStatus;

public class sampleTest1 extends BaseClass {
	@Test
	public void SRCH_001(){
		SearchPage sp = new SearchPage(driver);
		test.log(LogStatus.INFO, "Inputting the item in search field");
		sp.searchItem();
		test.log(LogStatus.INFO, "CLicking on search button");

		
	}

}
