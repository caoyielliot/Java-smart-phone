package Adapter;


import Exception.AutoException;
/**
 * author : Yi Cao
 * andrew id: yc2
 */
import Model.Automobile;

import Util.FileIO;
import Exception.ExceptionNum;
/***
 * 
 * @author Yi Cao
 * @andrewid  yc2
 */


public class proxyAutomobile {
   private Automobile a1;
   private String defaultfilename="Focus_Wagon_ZTW.txt";
   
   //implement updateOptionSetName method of interface UpdateAuto , to update the option set name
   public void updateOptionSetName(String modelname, String optionsetname, String newopsetname) {
	 try{
		 
		 //catch the CarModelNotFound Exception
		if(a1==null){
			throw new AutoException(ExceptionNum.CarModelNotFound);
		}
		//catch the MissingOpsetName Exception
		if(a1.findOpsetByName(optionsetname)==-1){
			throw new AutoException(ExceptionNum.MissingOpsetName);
		}
				
		 a1.updateOpsetName(optionsetname, newopsetname);
	 }catch(AutoException e){
		 //fix the exception accordingly
		 e.fix(e.getNo());
	 }
	  
	}
   //implement updateOptionPrice method of interface UpdateAuto , to update the option price
	public void updateOptionPrice(String Modelname, String Optionname, String OptionsetName, float newprice) {
		try{
			//catch the CarModelNotFound Exception
			if(!(a1.getName().equals(Modelname))){
				throw new AutoException(ExceptionNum.CarModelNotFound);
			}
			//catch the MissingOpsetName exception
			if(a1.findOpsetByName(OptionsetName)==-1){
				throw new AutoException(ExceptionNum.MissingOpsetName);
			}
			//catch the MissingOptionName Exception
		    if(a1.findOpsByName(Optionname, a1.findOpsetByName(OptionsetName))!=-1){
		    	throw new AutoException(ExceptionNum.MissingOptionName);
		    }
		   
			
			a1.updateOpsprice(Optionname, OptionsetName, newprice);
		}catch(AutoException ae){
			//fix exception accordingly
			ae.fix(ae.getNo());
		}
		
		
		
	}


	@SuppressWarnings("unused")
	//implement the BuildAuto method of interface CreateAuto 
	public void BuildAuto(String filename) {
		//Given a text file name a method called BuildAuto can be written to build an instance of 
		//Automobile. This method does not have to return the Auto instance.
	   FileIO file=new FileIO();
	   try{
		   //catch the Wrongfilename exception
		   if(!(filename.equals(defaultfilename))){
			   throw new AutoException(ExceptionNum.WrongfileName);
		   }
		   //catch the MissingFileName exception
		   if(filename==null){
			   throw new AutoException(ExceptionNum.MissingfileName);
		   }
		   file.buildAutoOject(filename);	
	   }catch(AutoException ae){
		   //fix the exception accordingly
		   ae.fix(ae.getNo());
	   }
	   	   
	}

	//implement the PrintAuto method of interface CreateAuto , to print the model info
	public void PrintAuto(String ModelName) {
		
		try{
			//catch the CarModelNotFound Exception
			if(!(a1.getName().equals(ModelName))){
			throw new AutoException(ExceptionNum.CarModelNotFound);
			
		} a1.printCar();
			
		}catch(AutoException ae){
			//fix the exception accordingly
			ae.fix(ae.getNo());
		}
		
		
		 
		
	}
   
}
