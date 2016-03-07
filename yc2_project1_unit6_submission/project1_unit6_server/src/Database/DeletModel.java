package Database;

import java.sql.ResultSet;

import javax.naming.spi.DirStateFactory.Result;

import Model.Automobile;

public class DeletModel extends JDBCAdapter{

	
	//constructor
	public DeletModel(String url, String driverName, String user,
			String password) {
		super(url, driverName, user, password);
		// TODO Auto-generated constructor stub
	}
	//constructor
	public DeletModel(String fileName) {
		super(fileName);
		// TODO Auto-generated constructor stub
	}


   //delete the model from the database
	//since the design of the database is cascade thus we need only delete the Automobile  id
	public void DeleteModel(String id){
		//find the id of the auto
//		System.out.print("ddddddddddddd"+auto.getModelName());
//		String sql="select id from Automobile where model="+auto.getModelName()+";";
		try{
			      String modelString=null;
			      String sql="select model from Automobile where id="+id+";";
			      ResultSet rSet=this.stmt.executeQuery(sql);
			      if(rSet.next()){
			    	 modelString=rSet.getString(1);
			      }
				  sql="delete from Automobile where id="+id+";";
				 
				  this.stmt.executeUpdate(sql);
				 System.out.println("you have successfully delete the model:"+modelString);
				 System.out.println("+++++++++++++++here is the new Automobile table after delete"+modelString+":++++++++++++++++");
				 
				 // System.out.println("delete the auto named as"+auto.getModelName());
			//  }
			  
		}catch(Exception e){
			e.printStackTrace();
		}
	  
	}
	
	
}
