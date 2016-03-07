package Driver;

import Exception.AutoException;
import Model.Automobile;

import Util.FileIO;

public class driver {
	 public static void main(String[]args) throws AutoException{
		 System.out.println("**************output the car's info**************");
		   String url="Focus_Wagon_ZTW.txt";
		   FileIO Car=new FileIO();
		   Automobile model=Car.buildAutoOject(url);
		   model.printCar();
		 System.out.println("****************test update: update the setName of Color to ColorSet*****************");
		   model.updateOpsetName("Color", "ColorSet");
		   model.printCar();
		     
		 System.out.println("**************test update: update the option price**************");
		   model.updateOpsprice("Automatic", "Transmission", 200);
		   model.printCar();
		   
		   Car.Serialization(model);
		   System.out.println("======================================");
		   Automobile newModel= Car.Deserilization("model.ser");
		   newModel.printCar();
		   
		  	   
		
		   
	   }
}
