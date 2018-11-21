package library;

import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import library.*;
import org.apache.logging.log4j.*;

import com.relevantcodes.extentreports.ExtentReports;



public class BaseClass {
	
	public static Logger log4j = LogManager.getLogger();
	
	@BeforeSuite
	public  void initializeReports() {
		
		ExtentReporting.createReportFile();

	}

	@BeforeMethod
	public static void testStart(Method testMethod) { 

		ExtentReporting.startTest(testMethod);
	}
	
	@AfterMethod
	public static void updateResults(ITestResult result) {
		ExtentReporting.updateTestResult(result);
	}
	
	
	
	
}
