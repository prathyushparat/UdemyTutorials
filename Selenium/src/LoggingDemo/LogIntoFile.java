package LoggingDemo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogIntoFile {
	private static final Logger log = LogManager.getLogger(LogIntoFile.class.getName());

	public static void main(String[] args) {
		log.trace("Trace logs");
		log.info("Info logs");
		log.warn("Warn logs");
		log.debug("Debug logs");
		log.error("Error logs");
		log.fatal("Fatal logs");

	}

}
