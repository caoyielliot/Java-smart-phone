package Database;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class printTable extends JDBCAdapter{
    public printTable(String fileName) {
		super(fileName);
		// TODO Auto-generated constructor stub
	}


	public void printTable(String name){
       System.out.println("*****************here is the table "+name+"*****************");
		try {
			   String sqlString="select * from "+name+";";
	           rSet=this.stmt.executeQuery(sqlString);
			  // Table exists
				//print the details of the table;
	           boolean flag=true;
	           boolean flag1=true;
	           boolean flag2=true;
	           while(rSet.next()){
	        	   if(name.equals("Automobile")){
	        		 
	        		   /*
	        		    * The second string begins after 40 characters. The dash means that the
	        		    * first string is left-justified.
	        		    */
	        		   String format = "%-6d %-20s %-6f %n";
	        		   String format2="%-6s %-20s %-6s %n";
	        		   if(flag2){
	        			   System.out.printf(format2,"id","model","base_price");
	        		   }
	        		   flag2=false;
	        		   System.out.printf(format, rSet.getInt(1),rSet.getString(2),rSet.getDouble(3));
	        		   
					}
					if(name.equals("OptionSet")){
						
						String format = "%-6d %-40s %-6d%n";
						if(flag){
							 String format2="%-6s %-40s %-6s%n";
							 System.out.printf(format2, "id","name","auto_id");
						}
						flag=false;
						 
		        		   System.out.printf(format, rSet.getInt(1),rSet.getString(2),rSet.getInt(3));
					}
				
					if(name.equals("Options")){
						String format = "%-6d %-40s %-30s %-6d%n";
						if(flag1){
							 String format2="%-6s %-40s  %-30s  %-6s%n";
							 System.out.printf(format2, "id","name","price","opset_id");
						}
						flag1=false;
						
		        		   System.out.printf(format, rSet.getInt(1),rSet.getString(2),rSet.getFloat(3), rSet.getInt(4));
						
					}
	           }
				
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
    }
} 
