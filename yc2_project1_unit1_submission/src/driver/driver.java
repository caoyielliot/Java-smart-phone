package driver;
import Model.Automotive;
import Util.FileIO;

public class driver {
   public static void main(String[]args){
	   String url="Focus_Wagon_ZTW.txt";
	   FileIO Car=new FileIO();
	   Automotive model=Car.buildAutoOject(url);
	   model.printCar();
	   Car.Serialization(model);
	   System.out.println("======================================");
	   Automotive newModel= Car.Deserilization("model.ser");
	   newModel.printCar();
   }
}
