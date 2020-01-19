package com.yyl.walle.station.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StationUISpringContext {
	private static final Logger logger = LoggerFactory.getLogger(StationUISpringContext.class);
	
	private static ApplicationContext applicationContext;
	
	public static ApplicationContext getContext() {
		if (applicationContext == null) {
			synchronized(StationUISpringContext.class) {
				if (applicationContext == null) {
					logger.debug("init spring context");
					applicationContext = new ClassPathXmlApplicationContext("spring/StationUISpringConfig.xml");
					logger.debug("init spring context done");
				}
			}
		}
		
		return applicationContext;
	}
}
