package SearchTestCases;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.beust.jcommander.Parameter;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BaseClass {

	WebDriver driver;
	static String BaseURL = null;
	static Properties prop = new Properties();

	static XSSFWorkbook wBook;
	static XSSFSheet wSheet;
	static XSSFCell testCaseNameCell, testCaseIdCell, testDataCell;

	static String testDataFile = ".\\TestData\\TestData.xlsx";
	static String sheetName = "Search";
	static String testCaseId = null;
	static String testCaseName = null, automationTestData = null;

	public static String getTestData(String TCId) {
		try {
			FileInputStream testData = new FileInputStream(testDataFile);
			wBook = new XSSFWorkbook(testData);
			wSheet = wBook.getSheet(sheetName);
			int columnNum = 0, rowNum = 0;
			int totalRows = wSheet.getLastRowNum(); // returns the last
													// populated row index
			// System.out.println("No of tests are "+totalRows);

			for (int i = 0; i <= totalRows; i++) {
				testCaseId = wSheet.getRow(i).getCell(0).getStringCellValue();
				// System.out.println("==== Test case ID under loop ==== "+testCaseId);

				if (testCaseId.equals(TCId)) {

					// System.out.println("==== Test case ID is ==== "+testCaseId);
					testDataCell = wSheet.getRow(i).getCell(2);
					automationTestData = testDataCell.getStringCellValue();
					System.out.println("==== Test data is ==== "
							+ automationTestData);
					return automationTestData;

					/* break; */

				}
			}
			return "default";
			// System.out.println("==== Test case ID is ==== "+testCaseId);
			// return testCaseId;

		} catch (Exception e) {
			e.printStackTrace();
			return "Invalid test case ID";
		}

	}

	public static String getTestCase(String TCId) {

		try {
			FileInputStream testData = new FileInputStream(testDataFile);
			wBook = new XSSFWorkbook(testData);
			wSheet = wBook.getSheet(sheetName);
			int columnNum = 0, rowNum = 0;
			int totalRows = wSheet.getLastRowNum(); // returns the last
													// populated row index
			// System.out.println("No of tests are "+totalRows);

			for (int i = 0; i <= totalRows; i++) {
				testCaseId = wSheet.getRow(i).getCell(0).getStringCellValue();
				// System.out.println("==== Test case ID under loop ==== "+testCaseId);

				if (testCaseId.equals(TCId)) {

					System.out.println("==== Test case ID is ==== "
							+ testCaseId);
					testCaseNameCell = wSheet.getRow(i).getCell(1);
					testCaseName = testCaseNameCell.getStringCellValue();
					System.out.println("==== Test case name is ==== "
							+ testCaseName);
					return testCaseName;

					/* break; */

				}
			}
			return "default";
			// System.out.println("==== Test case ID is ==== "+testCaseId);
			// return testCaseId;

		} catch (Exception e) {
			e.printStackTrace();
			return "Invalid test case ID";
		}

	}

	static ExtentReports report;
	public static ExtentTest test;
	static String reportLocation = ".\\TestReport\\TestReport.html";
	JavascriptExecutor jse;

	public static void setProperties() {
		try {
			FileInputStream fs = new FileInputStream(
					".\\Config\\env.properties");

			prop.load(fs);
		} catch (IOException e) {
			e.printStackTrace();
		}
		BaseURL = "https://www.flipkart.com";

	}

	@BeforeSuite
	public void initOnce() {
		report = new ExtentReports(reportLocation, false);
	}

	@BeforeMethod
	public static void initializeReports(Method testMethod) {
		test = report.startTest(testCaseId , getTestCase(testMethod.getName()));

		//test = report.startTest(getTestCase(testMethod.getName()));
	}

	@BeforeTest
	@Parameters({ "browser" })
	public void openBrowser(String browser) {
		if (browser.equals("firefox")) {
			setProperties();
			System.setProperty("webdriver.firefox.bin",
					"D://Program Files (x86)//Mozilla Firefox//firefox.exe");
			System.setProperty("webdriver.gecko.driver",
					"D://Prathyush//Work//Automation//UdemyTutorial//geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"D:\\Prathyush\\Work\\Automation\\UdemyTutorial\\chromedriver.exe");

			driver = new ChromeDriver();
		}

		// test.log(LogStatus.INFO, "Open URL "+BaseURL);

		driver.get(BaseURL);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}
	@AfterTest
	public void closeBrowser(){
		driver.close();
		driver.quit();

	}

	@AfterMethod
	public static void updateTestResult(ITestResult testResult) {
		int a = testResult.getStatus();
		if (a == 1)
			test.log(LogStatus.PASS, "Test is passed");
		else {
			test.log(LogStatus.FAIL, "Test is failed");

			test.log(LogStatus.FAIL, "Test is failed",
					testResult.getThrowable());
			// test.log(LogStatus.FAIL,"stepName",
			// ExceptionUtils.getStackTrace(testResult.getThrowable()));

		}
		report.endTest(test);
		report.flush();

	}

}
