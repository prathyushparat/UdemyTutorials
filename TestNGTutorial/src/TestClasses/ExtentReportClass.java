package TestClasses;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentReportClass {
	
	public static ExtentReports getInstance(){
		ExtentReports extent;
		String path = "D:\\Prathyush\\Work\\Automation\\UdemyTutorial\\ExtentReport\\CombinedReport.html";
		extent = new ExtentReports(path, false);
		
		return extent;
	}

}
