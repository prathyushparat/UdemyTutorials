package LoggingDemo;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class LogDemo {
	private static final Logger log = LogManager.getLogger(LogDemo.class.getName());


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		log.debug("Debug logs");
		log.error("Error logs");
		log.fatal("fatal logs");

	}

}
