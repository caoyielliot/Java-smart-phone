package Driver;

import Adapter.BuildAuto;
import scale.EditOptions;
import scale.OptionNum;
/***
 * 
 * @author Yi Cao
 * @andrew yc2
 * The class is used to test multithreadings
 * 
 */
public class MultiThreadsDriver {
  public static void main(String[]args){
	  System.out.println("==============print the auto info=================");
	  BuildAuto ba=new BuildAuto();
	  ba.BuildAuto("Focus_Wagon_ZTW.txt","txt");
	  ba.PrintAuto("Focus Wagon ZTW");
//	  
//	  
	  ////*****test the edit option price********
	  System.out.println("*****test the edit option price*****");
	  boolean t1state=true;//if true then t1 is running;recording last time the running status of thread
 	  boolean t2state=true;
	  EditOptions t1 = new EditOptions("t1") ;
	  EditOptions t2 = new EditOptions("t2") ;
	  
	  t1.editOptionPrice(t1,"Focus Wagon ZTW", "Transmission", "Automatic", 2000);
	  t1.start();
	  ba.PrintAuto("Focus Wagon ZTW");
	  t2.editOptionPrice(t2,"Focus Wagon ZTW", "Transmission", "Automatic", 4000);
	  t2.start();
	  ba.PrintAuto("Focus Wagon ZTW");
	  do{
   	   if(t1state&&!t1.isAlive()){
   		   t1state=false;
   		   System.out.println("t1 is dead!");
   	   }
   	   if(t2state&&!t2.isAlive()){
   		   t2state=false;
   		   System.out.println("t2 is dead!");
   	   }
      }while(t1state||t2state);
	  
	  System.out.println("*****test the edit optionset name*****");
      ///////*****test edit optionset Name*******
      boolean t3state=true;//if true then t1 is running;recording last time the running status of thread
	  boolean t4state=true;
  EditOptions t3 = new EditOptions("t3") ;
  EditOptions t4 = new EditOptions("t4") ;
//  
  t3.editOptionSetName(t3,"Focus Wagon ZTW", "Color", "ColorSet");
  t3.start();
  ba.PrintAuto("Focus Wagon ZTW");
  t4.editOptionSetName(t4,"Focus Wagon ZTW", "Transmission", "Auto2");
  t4.start();
  ba.PrintAuto("Focus Wagon ZTW");
  do{
	   if(t3state&&!t3.isAlive()){
		   t3state=false;
		   System.out.println("t3 is dead!");
	   }
	   if(t4state&&!t4.isAlive()){
		   t4state=false;
		   System.out.println("t4 is dead!");
	   }
  }while(t3state||t4state);

   
  
  ///*******test edit option name*******
  System.out.println("*****test the edit option name*****");
  boolean t5state=true;//if true then t1 is running;recording last time the running status of thread
  boolean t6state=true;
EditOptions t5 = new EditOptions("t5") ;
EditOptions t6 = new EditOptions("t6") ;

t5.editOptionName(t5,"Focus Wagon ZTW", "ColorSet", "Fort Knox Gold Clearcoat Metallic","Green");
t5.start();
ba.PrintAuto("Focus Wagon ZTW");
t6.editOptionName(t6,"Focus Wagon ZTW", "Auto2", "Automatic","newAutomatic");
t6.start();
ba.PrintAuto("Focus Wagon ZTW");
do{
   if(t5state&&!t5.isAlive()){
	   t5state=false;
	   System.out.println("t5 is dead!");
   }
   if(t6state&&!t6.isAlive()){
	   t6state=false;
	   System.out.println("t6 is dead!");
   }
}while(t5state||t6state);

	  
	  


	     
  }
  
}
  
