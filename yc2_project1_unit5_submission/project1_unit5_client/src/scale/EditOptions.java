package scale;

import Adapter.EditThreads;
import Exception.AutoException;
import Exception.ExceptionNum;
import Model.AutoList;
import Model.Automobile;

public class EditOptions extends Thread implements EditThreads{
   AutoList autoList;
   private OptionNum number;
   String modelname;
   String optionsetName;
   String newoptionsetName;
   float newprice;
   String optionName;
   String optionNewName;
   String threadId;
   EditOptions t;
   //constructor
   public EditOptions(String threadId){
	   this.threadId=threadId;
   }
   
   ///Getters and Setters
   public String getthreadId(EditOptions edit){
	   return edit.threadId;
   }
   
	public void setNumber(OptionNum number) {
	this.number = number;
}



public void setModelname(String modelname) {
	this.modelname = modelname;
}



public void setOptionsetName(String optionsetName) {
	this.optionsetName = optionsetName;
}



public void setNewoptionsetName(String newoptionsetName) {
	this.newoptionsetName = newoptionsetName;
}



public void setNewprice(float newprice) {
	this.newprice = newprice;
}



public void setOptionName(String optionName) {
	this.optionName = optionName;
}



public void setOptionNewName(String optionNewName) {
	this.optionNewName = optionNewName;
}
//random wait between two threads
void randomWait(){
   	try {
			Thread.currentThread().sleep((long)(3000*Math.random()));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   }
//to set the details of the option
public void editOptionPrice(EditOptions t,String Modelname, String OptionSetName, String OptionName, float newprice){
	this.t=t;
	t.setModelname(Modelname);
	t.setOptionsetName(OptionSetName);
	t.setOptionName(OptionName);
	t.setNewprice(newprice);
	t.setNumber(OptionNum.updateOptionPrice);
	randomWait();
}
//to set the deatils of the option
public void editOptionName(EditOptions t,String Modelname, String OptionSetName, String OptionName, String optionNewName){
	this.t=t;
	t.setModelname(Modelname);
	t.setOptionsetName(OptionSetName);
	t.setOptionName(OptionName);
	t.setOptionNewName(optionNewName);
	t.setNumber(OptionNum.updateOptionName);
	randomWait();
}
//to set the details of the optionset
public void editOptionSetName(EditOptions t,String modelname, String optionsetname, String newopsetname){
	this.t=t;
	t.setModelname(modelname);
	t.setOptionsetName(optionsetname);
    t.setNewoptionsetName(newopsetname);
	t.setNumber(OptionNum.updateOptionSetName);
	randomWait();
}
   //run opertaion override to implement all the update operations
	public void run(){
		switch(number.getNum()){
		case 1: 
			updateOptionPrice(t,modelname, optionsetName, optionName, newprice);break;
		case 2:
			updateOptionName(t,modelname, optionsetName, optionName, optionNewName);break;
		case 3:
			updateOptionSetName(t,modelname, optionsetName, newoptionsetName);break;
		default:
			break;
		}
	}
	
	
	
	///update the optionsetName with synchronized method
	
	public synchronized void  updateOptionSetName(EditOptions t,String modelname, String optionsetname, String newopsetname) {
		// TODO Auto-generated method stub
		this.t=t;
		 try{
			 Automobile a1 = autoList.getAutoListAutoMobileByName(modelname);
			if(a1==null){
				throw new AutoException(ExceptionNum.CarModelNotFound);
			}
			//catch the MissingOpsetName Exception
			if(a1.getopsetByName(optionsetname)==null){
				throw new AutoException(ExceptionNum.MissingOpsetName);
			}
					
			 a1.updateOptionSetName(optionsetname, newopsetname);
			 autoList.setAutoList(a1);
		 }catch(AutoException e){
			 //fix the exception accordingly
			 e.fix(e.getNo());
		 }
			System.out.println("the current Thread:"+getthreadId(t)+"   the opsetName changed from "+optionsetname+" to "+newopsetname);

	}
///update the option price with synchronized method
	
	public synchronized void updateOptionPrice(EditOptions t,String Modelname, String OptionSetName, String OptionName, float newprice) {
		// TODO Auto-generated method stub
		this.t=t;
		try{
			Automobile a1 = autoList.getAutoListAutoMobileByName(Modelname);
			if (a1 == null) {
				throw new AutoException(ExceptionNum.CarModelNotFound);
			}
			a1.updateOptionPrice( OptionSetName, OptionName,newprice);
			//autoList.setAutoList(a1);
		}catch(AutoException ae){
			//fix exception accordingly
			ae.fix(ae.getNo());
		}
		System.out.println("the current Thread:"+getthreadId(t)+"  the price of the option "+OptionName+" changed to "+newprice);

	}
 ///update the optionName with synchronized method
	
	public synchronized void updateOptionName(EditOptions t,String modelname, String optionsetName, String OptionName, String optionNewName) {
		// TODO Auto-generated method stub
		this.t=t;
		try{
			Automobile a1 = autoList.getAutoListAutoMobileByName(modelname);
			if (a1 == null) {
				throw new AutoException(ExceptionNum.CarModelNotFound);
			}
			a1.updateOptionName(optionsetName,OptionName,optionNewName);
			//autoList.setAutoList(a1);
		}catch(AutoException ae){
			//fix exception accordingly
			ae.fix(ae.getNo());
		}
		System.out.println("the current Thread:"+getthreadId(t)+"  the optionname changed from "+OptionName+" to "+optionNewName);

	}
     
}
