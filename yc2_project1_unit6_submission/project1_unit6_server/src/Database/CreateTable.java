package Database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.spi.DirStateFactory.Result;

//read commands from CreateTable.txt to create table
public class CreateTable extends JDBCAdapter{
//connect to the DB
	public CreateTable(String url, String driverName, String user,
			String password) {
		super(url, driverName, user, password);
		// TODO Auto-generated constructor stub
	}
//connect to the DB
	public CreateTable(String filename){
		super(filename);
	}
	
 //read file and create the table
	public  void CreateTableShema(String fileName){
		// createTable schema
		try {
			 System.out.println("you create a new table");
			BufferedReader br=new BufferedReader(new FileReader(new File(fileName)));
			String line;
			StringBuffer sb;
			boolean eof=false;
			while(!eof){
				
				   sb = new StringBuffer();
	                line = br.readLine();
	                if (line == null){
	                    eof = true;
	                }else if (line.length()!=0){
	                   while(line.indexOf(';')==-1){
	                       sb.append(line);
	                       line = br.readLine();
	                   }
	                    sb.append(line);
	                 
	                    this.stmt.executeUpdate(sb.toString());
			  }
	           
			}     
	        br.close();
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void dropTable(){
		//drop the table if the table exist
		
		try{
			
			System.out.println("delete tables if exists");
			String sql="DROP TABLE IF EXISTS `Options`;";
			this.stmt.execute(sql);
			
		    if(hasTable("Options")){
		    	sql="DROP TABLE IF EXISTS `OptionSet`;";
		    	this.stmt.execute(sql);
		     
		    	if(hasTable("OptionSet")){
		    		this.stmt.execute("DROP TABLE IF EXISTS `Automobile`;");
		    	}
		    }

		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	public boolean hasTable(String tableName){
		DatabaseMetaData dbm;
		try {
			dbm = connection.getMetaData();
			ResultSet tables = dbm.getTables(null, null, tableName, null);
			if (tables.next()) {
			  // Table exists
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// check if "employee" table is there
		return true;
	
	}

	
}
