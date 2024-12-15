package com.project.commons;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;

public class LoggerApp {
	 
	public static Logger getLogger() {
		Logger logger=Logger.getLogger(LoggerApp.class);
		SimpleLayout layout=new SimpleLayout();
		ConsoleAppender appender=new ConsoleAppender(layout);
		logger.addAppender(appender);
		logger.setLevel(Level.DEBUG);
		return logger;
	}
}
