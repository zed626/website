package com.aiwac.tool;

/**
*
* @author LMC
* @date 2017年10月26日
*
*/

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextUtils {
	private static ApplicationContext ctx;
	
	public static ApplicationContext getApplicationContext() {
		if(ctx == null) {
			ctx = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		}
		return ctx;
	}
}
