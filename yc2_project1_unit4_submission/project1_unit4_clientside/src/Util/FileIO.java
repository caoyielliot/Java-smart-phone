package Util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Properties;

import Exception.AutoException;
import Exception.ExceptionNum;
import Exception.Helpers;
import Model.Automobile;



public class FileIO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//build the Auto Object from reading the source file
   public Automobile buildAutoOjectByTxt(String url) throws AutoException{

		BufferedReader buff = null;
		String line = null;
		Automobile auto = new Automobile();
		

		String[] info = new String[2];// Basic information: name, base price
		String optionSetName;// Store the optionSet's name
		String[] optionInfo; // Store the options
		String[] optionDetail;

		try {
			// Open the file using FileReader Object.
			File f = new File(url);
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
				  

			   }
			
			}
			

		} catch (IOException e) {		
			e.printStackTrace();
//			try{
//			throw new AutoException(ExceptionNum.WrongfileName);}
//			catch(AutoException ex){
//				Helpers help=new Helpers();
//				String str=help.fixWrongfileName();
//				auto.setModelName("Focus Wagon ZTW");
//			}
		} 
        
		return auto;

   }
   
   public Automobile buildAutoObjectByProperties(String url) throws AutoException{
	   Properties props=new Properties();
	   Automobile auto =new Automobile();
	   try {
		props.load(new FileInputStream(url));
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
	     
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   
	return auto;
	   
   }
   
   
   
    // serialize the source file 
    public String Serialization(Automobile mode){
    	String filename="model.ser";
    	FileOutputStream out=null;
    	ObjectOutputStream oos=null;
    	try {
			 out=new FileOutputStream(filename);
			 oos=new ObjectOutputStream(out);
			oos.writeObject(mode);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				out.close();
				oos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    	System.out.println("serilization successful!");
		return filename;
    }
    //deserialize the source file
    public Automobile Deserilization(String filename){
      Automobile model = null;
		
		try {
			FileInputStream in = new FileInputStream(filename);
			ObjectInputStream ois = new ObjectInputStream(in);
			model = (Automobile) ois.readObject();
			ois.close();
		}
		catch(IOException i) {
			i.printStackTrace();
		}
		catch(ClassNotFoundException c) {
			c.printStackTrace();
		}
		System.out.println("now beagin deserilization!");
		return model;
	}

    }

