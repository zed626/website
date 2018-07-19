package com.aiwac.utils;

import org.apache.logging.log4j.Logger;

/**
*
* @author HC
* @date 2017年11月6日
*
*/

public class LoggerUtil {
	public static void LogException(Logger logger, Exception e) {
		logger.error("Exception: {}", e.getMessage());
		logger.error("Cause: {}", e.getCause().toString());
		logger.error("StackTrace: ");
		
		StackTraceElement[] stackTraceElement = e.getStackTrace();
		for (StackTraceElement element : stackTraceElement) {
			logger.error(element);
		}
	}
}
