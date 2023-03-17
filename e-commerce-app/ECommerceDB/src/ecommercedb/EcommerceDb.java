package ecommercedb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class EcommerceDb implements IEcommerceDb {

	
	private Connection connection;
	private static String driver;
	private static String databaseConnectLink;
	private static String databaseUser;
	private static String databasePassword;
	
	public EcommerceDb(){
		this.driver = "com.mysql.jdbc.Driver";
		this.databaseConnectLink = "jdbc:mysql://localhost:3306/ecommercedb?characterEncoding=utf8";
		this.databaseUser = "ecomadmin";
		this.databasePassword = "admin#123";
	}
	
	@Override
	public Connection connection() {
		Connection con = null;
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(databaseConnectLink,databaseUser, databasePassword);
			System.out.println("Connected!");
		}catch(ClassNotFoundException | SQLException e) {
			System.out.println(e + "");
		}
		return con;
	}

	
}
