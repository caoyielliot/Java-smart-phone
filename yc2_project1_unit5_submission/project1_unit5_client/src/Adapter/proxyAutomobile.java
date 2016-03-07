package Adapter;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Properties;
import java.util.prefs.BackingStoreException;

import Exception.AutoException;
import Model.AutoList;
/**
 * author : Yi Cao
 * andrew id: yc2
 */
import Model.Automobile;

import Util.FileIO;
import scale.EditOptions;
import scale.OptionNum;
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
   EditOptions t;
   private static AutoList autoList = new AutoList();
   @SuppressWarnings("unused")
	//implement the BuildAuto method of interface CreateAuto 
	public void BuildAuto(String filename,String fileType) {
		//Given a text file name a method called BuildAuto can be written to build an instance of 
		//Automobile. This method does not have to return the Auto instance.
	  // FileIO file=new FileIO();
	   if(fileType.equals("txt")){
		   try {
				AutoList.setAutoListAutoMobileByTxt(filename);
			} catch (AutoException e) {
				e.printStackTrace();
				Helpers helper = new Helpers();
				String newfilename = helper.fixMissingfileName();
				try {
					AutoList.setAutoListAutoMobileByTxt(newfilename);
				} catch (AutoException error) {
					error.printStackTrace();
				}
			}
	   }else if(fileType.equals("properties")){
		   
		   try {
			   AutoList.setAutoListAutoMobileByProperties(filename);
			} catch (AutoException e) {
				e.printStackTrace();
				Helpers helper = new Helpers();
				String newfilename = helper.fixMissingfileName();
				try {
					AutoList.setAutoListAutoMobileByProperties(newfilename);
				} catch (AutoException error) {
					error.printStackTrace();
				}
	     }
	   }
	   
	   	   
	}

   //implement updateOptionSetName method of interface UpdateAuto , to update the option set name
   public void  updateOptionSetName(EditOptions t,String modelname, String optionsetname, String newopsetname) {
		// TODO Auto-generated method stub
	 
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
		
	}
   //implement updateOptionPrice method of interface UpdateAuto , to update the option price
	public void updateOptionPrice(EditOptions t,String Modelname, String OptionSetName, String OptionName, float newprice) {
		
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
	public  void updateOptionName(EditOptions t,String modelname, String optionsetName, String OptionName, String optionNewName) {
		// TODO Auto-generated method stub
		
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
			
			ae.printStackTrace();
		}
	}
	
	//implement Accept properties object from client socket over an ObjectStream and create an Automobile.
	public void BuildAutoByProperties(ObjectInputStream objectInputStream){
		
		try {
			Properties props=(Properties)objectInputStream.readObject();
			//create the Auto object and add it to the AutoList
			   Automobile auto =new Automobile();
			   String[] info = new String[2];// Basic information: name, base price
			String optionSetName;// Store the optionSet's name
			String[] optionInfo; // Store the options
			String[] optionDetail;
			info[0]=props.getProperty("CarModel");
			info[1]=props.getProperty("BasePrice");
			try {
				// deal with the CarModelNotFound exception
				if (info[0].length() == 0) {
					throw new AutoException(ExceptionNum.CarModelNotFound);
				}
			} catch (AutoException e) {
				Helpers helper = new Helpers();
				info[0] = helper.fixCarModelNotFound();
			}
			
			try {
				// deal with the MissBasePrice exception
				if (info[1].length() == 0) {
					throw new AutoException(ExceptionNum.MissingBasePrice);
				}
			} catch (AutoException e) {
				Helpers helper = new Helpers();
				info[1] = Float.toString(helper.fixMissingBasePrice());
			}
			
			auto = new Automobile(info[0], (float)Double.parseDouble(info[1]));
			//set the OptionsetName
			for(char optionsetNum='1';props.getProperty("OptionSetName"+optionsetNum)!=null;optionsetNum++){
				//set the OptionSetName
			       auto.setOpset(props.getProperty("OptionSetName"+optionsetNum));
			       for(char optionNum='a';props.getProperty("OptionName"+optionsetNum+optionNum)!=null;optionNum++){
			 // auto.setOption(optionSetName,optionDetail[0],(float)Double.parseDouble(optionDetail[1]));
			    	   //set the Option Details
			          auto.setOption(props.getProperty("OptionSetName"+optionsetNum),
			        		  props.getProperty("OptionName"+optionsetNum+optionNum), 
			        		(float)Double.parseDouble(props.getProperty("OptionPrice"+optionsetNum+optionNum)));    
			       }
				
			}
			//add the auto into the autoList
			System.out.println("eeeeeeee");
			autoList.setAutoList(auto);
			autoList.printAutoList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	//implement Accept txt file from client socket over an ObjectStream and create an Automobile.
		public void BuildAutoByTxt(ObjectInputStream objectInputStream){
			 try {
				File f=(File)objectInputStream.readObject();
				BufferedReader buff = null;
				String line = null;
				Automobile auto = new Automobile();
				

				String[] info = new String[2];// Basic information: name, base price
				String optionSetName;// Store the optionSet's name
				String[] optionInfo; // Store the options
				String[] optionDetail;

				try {
					// Open the file using FileReader Object.
					
					buff = new BufferedReader(new FileReader(f));

					// Read the basic information
					for (int i = 0; i < info.length; i++) {
						info[i] = buff.readLine();
					}
					
					try {
						// deal with the CarModelNotFound exception
						if (info[0].length() == 0) {
							throw new AutoException(ExceptionNum.CarModelNotFound);
						}
					} catch (AutoException e) {
						Helpers helper = new Helpers();
						info[0] = helper.fixCarModelNotFound();
					}
					
					try {
						// deal with the MissBasePrice exception
						if (info[1].length() == 0) {
							throw new AutoException(ExceptionNum.MissingBasePrice);
						}
					} catch (AutoException e) {
						Helpers helper = new Helpers();
						info[1] = Float.toString(helper.fixMissingBasePrice());
					}
					
					
					auto = new Automobile(info[0], (float)Double.parseDouble(info[1]));
		   		while((line = buff.readLine()) != null ){
						
						optionSetName=line;
						try{
							if(optionSetName.equals("")){
								
								throw new AutoException(ExceptionNum.MissingOpsetName);
							}
						}catch(AutoException e){
							Helpers helper = new Helpers();
							String setName=helper.fixMissingOpsetName();
							optionSetName=setName;
						}
						
						//System.out.println(line);
						auto.setOpset(optionSetName);
					   line=buff.readLine();
					   optionInfo=line.split(";");
					   for(int j=0;j<optionInfo.length;j++){
						   optionDetail=optionInfo[j].split(",");
						   try{
							   if(optionDetail[0].equals("")){
								   throw new AutoException(ExceptionNum.MissingOptionName);
							   }
						   }catch(AutoException e){
							   Helpers helper = new Helpers();
							   String opName=helper.fixMissingOptionName();
							   optionDetail[0]=opName;
						   }
						   
		     			  // System.out.println(optionDetail[0]);
						  // System.out.println(optionDetail[1]);
						   try{
							   if(optionDetail.length==1){
								   throw new AutoException(ExceptionNum.MissingOptionPrice);
							   }
							   auto.setOption(optionSetName,optionDetail[0],(float)Double.parseDouble(optionDetail[1]));
						   }catch(AutoException e){
							   Helpers help=new Helpers();
							   float opPrice=help.fixMissingOptionPrice();
							 
							   auto.updateOptionPrice(optionSetName, optionDetail[0], opPrice);
							   auto.setOption(optionSetName,optionDetail[0],opPrice);
						   }
						   //add it to the autoList
						  autoList.setAutoList(auto);

					   }
					
					}
					

				} catch (IOException e) {		
					e.printStackTrace();

				} 
		        
				
				
				
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
    

}
