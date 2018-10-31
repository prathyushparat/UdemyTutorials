package SearchTestCases;

import java.util.List;

import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.webpage.object.SearchPage;

public class SRCH_001 extends BaseClass{
	
	String testCaseID="SRCH_001";
	String TCId=getTestCase(testCaseID);
	String testData=getTestData(testCaseID);

	@Test
	public void SRCH_001(){
		String item=testData.split(",")[0];
		int count=Integer.parseInt(testData.split(",")[1]);
		String sortOrder=testData.split(",")[2];
		SearchPage sp = new SearchPage(driver);
		sp.searchItem(item);
		test.log(LogStatus.INFO, "Clicked on search button");
		sp.sortBy(sortOrder);
		test.log(LogStatus.INFO, "Sorted in "+sortOrder+" order");


		/*List l1=sp.extractResult(count,sortOrder);
		test.log(LogStatus.INFO, l1.toString());
		sp.validateSorting(sortOrder);*/
		
	}

}
