package library;

import com.relevantcodes.extentreports.LogStatus;

/***
 * Class to insert logs to extent report & lo4j
 * @author Ravi
 *
 */
public class ELog {
	
	public static void info(String msg)
	{
		BaseClass.log4j.info(msg);
		ExtentReporting.testReporter.log(LogStatus.INFO,msg);
	}
	
	public static void error(String errMsg)
	{
		ExtentReporting.testReporter.log(LogStatus.ERROR,errMsg);
	}

	
	
}
