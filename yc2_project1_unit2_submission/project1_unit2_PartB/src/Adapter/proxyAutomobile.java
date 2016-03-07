package Adapter;


import Exception.AutoException;
import Model.AutoList;
/**
 * author : Yi Cao
 * andrew id: yc2
 */
import Model.Automobile;

import Util.FileIO;
import Exception.ExceptionNum;
import Exception.Helpers;
/***
 * 
 * @author Yi Cao
 * @andrewid  yc2
 */


public class proxyAutomobile {
   private Automobile a1;
   private String defaultfilename="Focus_Wagon_ZTW.txt";
   
   private static AutoList autoList = new AutoList();
   @SuppressWarnings("unused")
	//implement the BuildAuto method of interface CreateAuto 
	public void BuildAuto(String filename) {
		//Given a text file name a method called BuildAuto can be written to build an instance of 
		//Automobile. This method does not have to return the Auto instance.
	  // FileIO file=new FileIO();
	   
	   try {
			AutoList.setAutoListAutoMobile(filename);
		} catch (AutoException e) {
			System.out.println("Error" + e.toString());
			Helpers helper = new Helpers();
			String newfilename = helper.fixMissingfileName();
			try {
				AutoList.setAutoListAutoMobile(newfilename);
			} catch (AutoException error) {
				error.printStackTrace();
			}
		}
	   
	   	   
	}

   //implement updateOptionSetName method of interface UpdateAuto , to update the option set name
   public void updateOptionSetName(String modelname, String optionsetname, String newopsetname) {
	 try{
//		 FileIO file=new FileIO();
//		 a1=file.buildAutoOject(defaultfilename);
		 //catch the CarModelNotFound Exception
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
	  
	}
   //implement updateOptionPrice method of interface UpdateAuto , to update the option price
	public void updateOptionPrice(String Modelname, String OptionSetName, String OptionName, float newprice) {
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
		
		
		
	}


	

	//implement the PrintAuto method of interface CreateAuto , to print the model info
	public void PrintAuto(String ModelName) {
		    
		Automobile auto = autoList.getAutoListAutoMobileByName(ModelName);
		try {
			if (auto == null) {
				throw new AutoException(ExceptionNum.CarModelNotFound);
			}
			auto.print();
		} catch (AutoException ae) {
			Helpers help =new Helpers();
			String modelName=help.fixCarModelNotFound();
			
			System.out.println("Error " + ae.toString());
		}
		
		 
		
	}
   
}
