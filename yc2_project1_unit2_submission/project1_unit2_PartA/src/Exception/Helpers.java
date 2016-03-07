package Exception;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;


public class Helpers {
	private String defaultfilename="Focus_Wagon_ZTW.txt";
	log logTime=new log();
	//to fix MissingBasePrice Exception
  public float fixMissingBasePrice(){
	  String message="Missing the price for Automobile in text file!Pls input the price";
	  System.err.println("Time:"+logTime.log(1)+"Missing the price for Automobile in text file!Pls input the price");
	  float baseprice=0;
	  @SuppressWarnings("resource")
	Scanner scan=new Scanner(System.in);
	  baseprice=scan.nextFloat();
	  return baseprice;
  }
  // to fix MissingOptionPrice Exception
  public  float fixMissingOptionPrice(){
	  System.err.println("Time:"+logTime.log(6)+"Missing the Option price! Please input the Option price");
	  float optionprice=0;
	  @SuppressWarnings("resource")
	Scanner scan=new Scanner(System.in);
	  optionprice=scan.nextFloat();
	  return optionprice;
  }
  // to fix MissingOptionName Exception
  public String fixMissingOptionName(){
	  System.err.println("Time:"+logTime.log(5)+"Missing the Option Name!Please input the Option Name");
	  String optionName;
	  @SuppressWarnings("resource")
	Scanner scan=new Scanner(System.in);
	  optionName=scan.next();
	  return optionName;
  }
  //to fix MissingfileName Exception
  public String fixMissingfileName(){
	  System.err.println("Time:"+logTime.log(2)+"Missing the file name!Fix the filename as default!");
	   return defaultfilename;
  }
  //to fix WrongfileName Exception
  public String fixWrongfileName(){
	  System.err.println("Time:"+logTime.log(7)+"Wrong filename! Fix the filename as default!");
	  return defaultfilename;
  }
  //to fix MissingOpsetName Exception
  public String fixMissingOpsetName(){
	  System.err.println("Time:"+logTime.log(4)+"Missing the opsetName! Please input the opsetname");
	  String opsetName;
	  @SuppressWarnings("resource")
	Scanner scan=new Scanner(System.in);
	  opsetName=scan.next();
	  return opsetName;
  }
  //to fix MissingOpsetData(or part of data) Exception
  public String fixMissingOpsetData() throws IOException{
	
	  BufferedReader br=null;
	  String str=null;
	try {
		  System.err.println("Time:"+logTime.log(3)+"Missing the opsetdata! please input the opsetdata!");
		  br=new BufferedReader(new InputStreamReader(System.in));
		  str=br.readLine();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		br.close();
	}
	return str;
  }
  //to fix CarModelNotFound Exception
  public String fixCarModelNotFound() {
		System.err
				.println("Time:"+logTime.log(8)+"Missing the car model in test file ! Please enter the value.");
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in));
			return br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
  
  
  
  
  
  
  
  
  
  
}

