package util;

import java.sql.*;

public class DBConnUtil {
	
	public static Connection getDBConn(String connectionString) {
		String[] properties=connectionString.split(" ");	
		String Url=properties[3];
		String User=properties[0];
		String Password=properties[1];
		String Driver=properties[2];
			
		try {
			Class.forName(Driver);
			System.out.println("Connection Established");
			Connection con = DriverManager.getConnection(Url, User, Password);
			return con;
		}catch(Exception e) {
			System.out.println("Exception Occured : "+e.getMessage());
		}
		return null;
		}
				
}
