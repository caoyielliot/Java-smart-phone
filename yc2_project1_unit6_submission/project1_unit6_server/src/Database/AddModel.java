package Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.spi.DirStateFactory.Result;

import Model.Automobile;
import Model.OptionSet;

public class AddModel extends JDBCAdapter {

	public AddModel(String url, String driverName, String user, String password) {
		super(url, driverName, user, password);
		// TODO Auto-generated constructor stub
	}
	public AddModel(String fileName){
		super(fileName);
	}
//this class is used to add a new model to the database
	public  void AddModel(Automobile auto) throws SQLException{
	String sql=null;
	//add the auto to the database
		String model=auto.getModelName();
		float baseprice=auto.getBasePrice();
		//insert to the Automobile table
		sql="insert into Automobile (model, base_price) values ('"+model+"',"+baseprice+");";
		try {
			
			getId(sql);
			while(rSet.next()){
				
				int auto_id=rSet.getInt(1);
				//get the optionset and insert the opset into the table OptionSet
				 ArrayList<OptionSet> arrayList=auto.getopsetList();
				    for(OptionSet opset:arrayList){
				    	String opsetName=opset.getOptionSetName();
				    	//insert the opsetName and auto_id into the OptionSet
				    	
				    	sql="insert into OptionSet(name,auto_id) values ('"+opsetName+"',"+auto_id+");";
				    	getId(sql);
				    	while(rSet.next()){
				    		int opset_id=rSet.getInt(1);
				    		//insert into  Options table
				    		//get the optionname and price
				    		ArrayList<String> opsName=auto.getOptionsName(opsetName);
				    		for(String name:opsName){
				    			sql="insert into Options(name,price,optset_id) values ('"+name+"',"+auto.getOptionPrice(opsetName, name)+","+opset_id+");";
				    		    this.stmt.executeUpdate(sql);
				    		}
				    		
				    		
				    	}
				    }
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	
	}
    public  void getId(String sql){
	  try {
		this.stmt.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
		rSet=this.stmt.getGeneratedKeys();
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
		
  }
	
}
