package com.practo.sai.jobportal.utility;

public class Logger {

	org.apache.log4j.Logger logger;

	public static Logger getInstance(Class clazz) {
		return new Logger(clazz);
	}

	private Logger(Class clazz) {
		logger = org.apache.log4j.Logger.getLogger(clazz);
	}

	public void info(Object message) {
		logger.info(message);
	}

	public void debug(Object message) {
		logger.debug(message);
	}

	public void debug(Object message, Throwable t) {
		logger.debug(message, t);
	}

	public void error(Object message, Throwable t) {
		logger.error(message, t);
	}

}
