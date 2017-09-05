package LoggingDemo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggingConsole {
	private static final Logger log = LogManager.getLogger(LoggingConsole.class.getName());

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		log.debug("Debig logs");
		log.error("Error logs");
		log.fatal("Fatal logs");

	}

}
