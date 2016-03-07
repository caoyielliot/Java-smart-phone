package Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.spi.DirStateFactory.Result;

import Model.Automobile;

public class UpdateModel extends JDBCAdapter implements Updaters{
	
   //constructor
	public UpdateModel(String url, String driverName, String user,
			String password) {
		super(url, driverName, user, password);
		// TODO Auto-generated constructor stub
	}
	//constructor to connect to the DB
	public UpdateModel(String fileName) {
		super(fileName);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void updateModelName(String ModelName, String newModelName) {
		// TODO Auto-generated method stub
		//update the model name in the Automobile table
		try{
			String sql="UPDATE Automobile SET model ='"+newModelName+"'where model ='"+ModelName+"';";
		    this.stmt.executeUpdate(sql);	
		}catch(Exception e){
			e.printStackTrace();
		}
	
	}
	@Override
	public void updateBasePrice(String ModelName, float newBasePrice) {
		// TODO Auto-generated method stub
		//update the base price
		try{
			String sql="UPDATE Automobile SET base_price ='"+newBasePrice+"'where model ='"+ModelName+"';";
		    this.stmt.executeUpdate(sql);	
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	@Override
	public void updateOpsetName(String modelName, String setName,
			String newSetName) {
		// TODO Auto-generated method stub
		//update the the OpsetName
		//1. select the auto_id from the Automobile table
		String sql1="select id from Automobile where model='"+modelName+"';";
		//2. update the OptionSet table according to the auto_id and setName
		try{
		    rSet=this.stmt.executeQuery(sql1);
		    if(rSet.next()){
		    	//get the auto id from the rSet
		    	int auto_id=rSet.getInt(1);
		    	String sql2="update OptionSet set name='"+newSetName+"'where auto_id="+auto_id+" and name='"+setName+"';";
		    	this.stmt.executeUpdate(sql2);
		    }
		}catch(Exception e){
			e.printStackTrace();
		}
	    
	}
	@Override
	public void updateOptionPrice(String Modelname, String OptionSetName,
			String OptionName, float newprice) {
		//  update the OptionPrice of Options table
		//get the auto_id from the Automobile table
		String sql1="select id from Automobile where model='"+Modelname+"';";
		try{
			 rSet=this.stmt.executeQuery(sql1);
			if(rSet.next()){
				int auto_id =rSet.getInt(1);
				String sql2="select id from OptionSet where auto_id="+auto_id+" and name='"+OptionSetName+"';";
				 rSet=this.stmt.executeQuery(sql2);
				if(rSet.next()){
					//update the Option price 
					int opset_id=rSet.getInt(1);
					String sql3="update Options set price="+newprice+" where optset_id="+opset_id+" and name='"+OptionName+"';";
					this.stmt.executeUpdate(sql3);
				}
				
			}
		}catch(Exception e){
			
		}
		
	}
	@Override
	public void updateOptionName(String modelname, String optionsetName,
			String OptionName, String optionNewName) {
		// update the OptionName
		String sql1="select id from Automobile where model='"+modelname+"';";
		try{
			ResultSet rSet=this.stmt.executeQuery(sql1);
			if(rSet.next()){
				int auto_id =rSet.getInt(1);
				String sql2="select id from OptionSet where auto_id="+auto_id+" and name='"+optionsetName+"';";
				//System.out.println(sql2);
				ResultSet rSet2=this.stmt.executeQuery(sql2);
				if(rSet2.next()){
					int opset_id=rSet2.getInt(1);
					String sql3="update Options set name='"+optionNewName+"' where optset_id="+opset_id+" and name='"+OptionName+"';";
					//String sql3="UPDATE Option SET name='"+optionNewName+"' where opset_id="+opset_id+" and name='"+OptionName+"';";
					//System.out.println(sql3);
					this.stmt.executeUpdate(sql3);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	

}
