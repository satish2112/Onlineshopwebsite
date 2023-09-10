package com.satish.dbconnection;
import java.sql.*;
public class DbConnection {
	private static Connection con=null; 
	public static Connection getDatabaseConnection() {
		String dirverClassName="com.mysql.cj.jdbc.Driver";
		try {	
			if(con ==null) {
				Class.forName(dirverClassName);
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/OnlineshopeeApp",
						"root","123456");
			}
		} catch (ClassNotFoundException e) {
			System.out.println("Check Id libarary added");
			System.out.println("or check class name loder"+dirverClassName);
			e.printStackTrace();
		}catch (SQLException e ) {
			System.out.println("Check Url or username and password");
			e.printStackTrace();
		}
		return con;

	}
}
