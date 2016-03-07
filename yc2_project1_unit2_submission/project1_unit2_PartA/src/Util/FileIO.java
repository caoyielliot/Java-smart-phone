package Util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import Exception.AutoException;
import Exception.ExceptionNum;
import Model.Automobile;


public class FileIO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//build the Auto Object from reading the source file
   public Automobile buildAutoOject(String url) throws AutoException{
	   Automobile model = null;
	   FileReader file=null;
	   BufferedReader buff=null;
	   String[] str=null;
	   
	   try {
	   file=new FileReader(url);
	   buff=new BufferedReader(file);
	   boolean eof=false;
	  
		   try {
			String line=buff.readLine();
			str=line.split("\t");
			if(str[0]==null){
				throw new AutoException(ExceptionNum.CarModelNotFound);
			}
			if(str[1]==null){
				throw new AutoException(ExceptionNum.MissingBasePrice);
			}
			
			float baseprice=(float)Double.parseDouble(str[1]);
			int setSize=Integer.parseInt(str[2]);
			if(setSize==0){
				throw new AutoException(ExceptionNum.MissingOpsetData);
			}
			int[] optSize=new int[setSize];
			for(int i=0;i<setSize;i++){
				optSize[i]=Integer.parseInt(str[3].split(" ")[i]);
				
			}
			model=new Automobile(str[0],baseprice,setSize,optSize);
			//read the details of different optionset
		    while(line!=null){
			line=buff.readLine();
		    	String[] newbuff;
		    	newbuff=line.split("\t");
		        for(int i=0;i<setSize;++i){
		        	for(int j=0;j<optSize[i]+1;++j){
		        		
		        		if(j==0){
		        			model.setOpSetName(i, line);
		        		}else{
		        			model.setOpts(i, j-1, newbuff[0], (float)Double.parseDouble(newbuff[1]));
		        		}
		        		line = buff.readLine();
						if (line != null)
							newbuff = line.split("\t");
		        	}
		        }
		        
		        
	    }
		
			
						
		} catch (IOException e) {
			//  Auto-generated catch block
			System.out.println("Error---"+e.toString());
		}finally{
			try {
				buff.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		   
	   
		
	} catch (FileNotFoundException e) {
		// Auto-generated catch block
		e.printStackTrace();
	}finally{
		try {
			file.close();
			buff.close();
		} catch (IOException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	   
	return model;
	   
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

