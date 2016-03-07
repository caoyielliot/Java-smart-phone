package Client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Properties;
import java.util.prefs.BackingStoreException;

import Adapter.BuildAuto;
import Adapter.CreateAuto;
import Model.Automobile;
import Model.OptionSet;

public class CarModelOptionsIO {
   
	
	
	/**
	 * Read data from the Properties file; create properties object, using the
	 * load method, which transfers the object from the client to server, using
	 * ObjectStream.
	 */
	public void transferPropertiesToServer(ObjectOutputStream objectOutputStream,String fileName){
		Properties properties=new Properties();
		FileInputStream fis=null;
		try{
			//load the file
			fis=new FileInputStream(fileName);
			properties.load(fis);
			fis.close();
			objectOutputStream.writeObject(properties);
	        objectOutputStream.flush();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
    
	/**
	 * Read data from the txt file; create properties object, using the
	 * load method, which transfers the object from the client to server, using
	 * ObjectStream.
	 */
	
	public void transferTxtToServer(ObjectOutputStream objectOutputStream,String fileName){
	
		File file=null;
		try{
			file=new File(fileName);
			objectOutputStream.writeObject(file);
			objectOutputStream.flush();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	 /**
	  * Receive a response from the Server,
	  *  verifying that the Car Model object is created successfully.
	  */
	public void verify(ObjectInputStream objectInputStream){
		try{
		
				String info=(String)objectInputStream.readObject();
				System.out.println("Server:"+info);
			
		
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
     /**
      * Use CreateAuto interface to build Automobile and handle different type of files,
      *  passed in filetype.
      */
	 public void buildAuto(String filename,String fileType){
		 CreateAuto createAuto=new BuildAuto();
		 createAuto.BuildAuto(filename, fileType);
		 
	 }
	 /**
	  * get all the auto name in the autolist
	  * @param objectInputStream
	  */
  public void getAllAuto(ObjectInputStream objectInputStream){
	  try{
		  ArrayList<String> arrayList= (ArrayList<String>)objectInputStream.readObject();
		  if(arrayList.size()==0){
			  System.out.println("Server:List is Empty");
		  }else{
			 for(String str:arrayList){
				 System.out.println(str);
			 }
		  }
		  
	  }catch(Exception e){
		  e.printStackTrace();
	  }
  }
  /***
   * select the auto within autolist and print it on the client side
   * @param objectInputStream
   */
 public void selectAuto(ObjectInputStream objectInputStream){
	 try{
		
		Automobile auto=(Automobile)objectInputStream.readObject();
		auto.setOptionChoice("Color", "Fort Knox Gold Clearcoat Metallic");
		auto.setOptionChoice("Transmission", "Automatic");
		auto.setOptionChoice("Brakes/Traction Control", "ABS");
		auto.setOptionChoice("Side Impact Airbags", "Selected");
		auto.setOptionChoice("Power Moonroof", "Selected");
		System.out.println("Base price is : " + auto.getBasePrice());
		auto.printChoice();
		System.out.println("The total price is " + auto.getTotalPrice());
	 }catch(Exception e){
		 e.printStackTrace();
	 }
	
	 
 }
	
	
}
