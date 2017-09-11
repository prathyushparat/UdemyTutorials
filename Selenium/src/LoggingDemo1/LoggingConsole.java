package LoggingDemo1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggingConsole {
	private static final Logger log = LogManager.getLogger(LoggingConsole.class.getName());

	public static void main(String[] args) {
		log.debug("Debug logs");
		log.error("Error logs");
		log.fatal("Fatal logs");

	}

}
