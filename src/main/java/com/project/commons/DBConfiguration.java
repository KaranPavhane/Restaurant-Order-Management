package com.project.commons;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DBConfiguration  {
	
	private static Logger logger = LogManager.getLogger(DBConfiguration.class);
	
	
	private static Connection con;
	private static PreparedStatement ps;
	private static ResultSet rs;
	private static DBConfiguration db=null;
	
	private DBConfiguration() {
		try {
			Properties prop=new Properties();
			File f=new File("src\\main\\resources\\application.properties");
			FileInputStream fin=new FileInputStream(f);
			prop.load(fin);
			String driver=prop.getProperty("driver");
			String url=prop.getProperty("url");
			String username=prop.getProperty("username");
			String password=prop.getProperty("password");
			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);
			
			
		}catch(Exception ex) {
			ex.printStackTrace();
			logger.fatal("Unknown Exception...");
		}
		
	}
	
	
	public static DBConfiguration getInstance() {
		if(db==null) {
			new DBConfiguration();
		}
		return db;
	}
	
	public static Connection getConnection() {
		return con;
	}
	
	public static PreparedStatement getStatement() {
		return ps;
	}
	
	public static ResultSet getResult() {
		return rs;
	}
	
}
