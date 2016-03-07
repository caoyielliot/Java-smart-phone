package Driver;

import Adapter.BuildAuto;
import Exception.AutoException;
import Model.Automobile;
import Util.FileIO;

public class driver {
  public static void main(String[]args) throws AutoException{
	  System.out.println(" ******** Print autoinfo with txt *********\n");
	  BuildAuto ba=new BuildAuto();
	  ba.BuildAuto("Focus_Wagon_ZTW.txt", "txt");
	  ba.PrintAuto("Focus Wagon ZTW");
	  System.out.println(" ******** Print autoinfo with properties *********\n");
	  ba.BuildAuto("Focus_Wagon_ZTW.properties", "properties");
	  ba.PrintAuto("Focus Wagon ZTW");
	  
//	  System.out.println("====================");
//	 /**************test OptionSet*******************/
//	  System.out.println("********  Test OptionSet *********\n" );
//	  ba.updateOptionSetName(null, "Focus Wagon ZTW", "Color","ColorSet");
//	  ba.PrintAuto("Focus Wagon ZTW");
//	  System.out.println("====================");
//	  /***************test Option*******************/
//	  System.out.println("********  Test Option *********\n" );
//	  ba.updateOptionPrice(null, "Focus Wagon ZTW", "Transmission","Automatic", 200);
//	  ba.PrintAuto("Focus Wagon ZTW");
//      /******************test choice***************************/
//	  System.out.println("********  Test Choice *********\n" );
//		Automobile auto = new FileIO().buildAutoOject("Focus_Wagon_ZTW.txt");
//		auto.setOptionChoice("Color", "Fort Knox Gold Clearcoat Metallic");
//		auto.setOptionChoice("Transmission", "Automatic");
//		auto.setOptionChoice("Brakes/Traction Control", "ABS");
//		auto.setOptionChoice("Side Impact Airbags", "Selected");
//		auto.setOptionChoice("Power Moonroof", "Selected");
//		System.out.println("Base price is : " + auto.getBasePrice());
//		auto.printChoice();
//		System.out.println("The total price is " + auto.getTotalPrice());
//              
  }
}
