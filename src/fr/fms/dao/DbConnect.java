package fr.fms.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DbConnect {
	
	private static Properties prop;
	private static String url;
	private static String user;
	private static String password;
	private static Connection connection = null;
	
	
	static {
		try {
		prop = new Properties();
		FileInputStream in = new FileInputStream("config.properties");
		
		prop.load(in);
		in.close();
		
		url = prop.getProperty("db.url");
		user = prop.getProperty("db.user");
		password= prop.getProperty("db.password");
		
			Class.forName(prop.getProperty("db.driver.class"));
			connection = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public static Connection getConnection() {
		return connection;
		
	}

}
