package library;

import java.lang.reflect.Method;

import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentReporting {
	
	static ExtentReports report;
	public static ExtentTest testReporter;
	static String reportLocation = ".\\TestReport\\TestReport.html";
	
	
	public static void createReportFile() {
		report = new ExtentReports(reportLocation, true);
	}

	
	public static void startTest(Method testMethod) {
		testReporter = report.startTest(testMethod.getName());

		//test = report.startTest(getTestCase(testMethod.getName()));
	}
	
	public static void endTest(Method testMethod) {
		
		report.endTest(testReporter);
		
	}
	
	
	public static void updateTestResult(ITestResult testResult) {
		int a = testResult.getStatus();
		if (a == 1)
			testReporter.log(LogStatus.PASS, "Test is passed");
		else {
			testReporter.log(LogStatus.FAIL, "Test is failed");

			testReporter.log(LogStatus.FAIL, "Test is failed",
					testResult.getThrowable());
			// test.log(LogStatus.FAIL,"stepName",
			// ExceptionUtils.getStackTrace(testResult.getThrowable()));

		}
		report.endTest(testReporter);
		report.flush();

	}
	
	public static void log(String msg)
	{
		testReporter.log(LogStatus.INFO, msg);
	}
	
	public static void error(String errorMsg)
	{
		
	}
	

}
