package Database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.naming.spi.DirStateFactory.Result;

public class JDBCAdapter {
   //this class is used to connect to the database; it also includes the operations for car
	
	Connection connection;
	Statement stmt;
	ResultSet rSet;
	//constructor connect to the database
	public JDBCAdapter(String url, String driverName, String user, String password){
		   try {
	            Class.forName(driverName);
	            connection = DriverManager.getConnection(url, user, password);
	            stmt = connection.createStatement();
	            
	        }
	        catch (ClassNotFoundException e) {
	            System.err.println("Cannot find the database driver classes.");
	            e.printStackTrace();
	        }
	        catch (SQLException e) {
	            System.err.println("Cannot connect to this database.");
	            e.printStackTrace();
	        }
}
    public JDBCAdapter(String fileName) {
	// TODO Auto-generated constructor stub
	   //connect to the DataBase with the dbInit.properties
    	Properties properties=new Properties();
    	
    	try {
    		
    		//FileReader file=new FileReader(fileName);
			properties.load(new FileInputStream(fileName));
			String url=properties.getProperty("url");
			String Driver=properties.getProperty("Driver");
			String username=properties.getProperty("username");
			String password=properties.getProperty("password");
			 Class.forName(Driver);
	         connection = DriverManager.getConnection(url, username, password);
	         stmt = connection.createStatement();
			
		} catch (IOException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
}
     public void CloseDB(){
    	  try {
			this.connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     }
	
	
	
	
}
