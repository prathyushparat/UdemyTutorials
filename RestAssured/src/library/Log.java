package library;

import com.relevantcodes.extentreports.LogStatus;

/***
 * Class to insert logs to lo4j
 * @author Ravi
 *
 */
public class Log {
	
	public static void info(String msg)
	{
		BaseClass.log4j.info(msg);
	}
	
	public static void error(String errMsg)
	{
		BaseClass.log4j.error(errMsg);
	}

	
	
}
