package util;


import java.io.FileInputStream;
import java.util.Properties;

public class DBPropertyUtil {

	public static String getConnectionString(String propertyFileName) {
		
		try {
		FileInputStream fileInputStream = new FileInputStream(propertyFileName);
		Properties props =new Properties();
		props.load(fileInputStream);
		String propstr=props.getProperty("user")+" "+props.getProperty("password")+" "+props.getProperty("driver")+" "+props.getProperty("url");
		return propstr;
		
		}catch(Exception e) {
			System.out.println("Exception occured : "+e.getMessage());
		}
		return null;
		
	}

}