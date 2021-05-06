package Piattaforma_cv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Test_credenziali {
	
	private String User;
	private String Password;
	//private Boolean logged= false;
	
	public Test_credenziali(String user, String password) {
		User=user;
		Password=password;
	}
	
	
	public Boolean tryConnection() throws  ClassNotFoundException {
		
		Class.forName("org.postgresql.Driver");
		 try {
			 Connection c = DriverManager.getConnection(
                 "jdbc:postgresql://localhost:5432/postgres",User, Password); 
		 }
         
		 catch (SQLException e) {
			 return false;
	}
		return true;

}
}
