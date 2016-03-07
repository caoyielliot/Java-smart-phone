package Driver;


import java.sql.Statement;
import java.util.Scanner;

import org.omg.CORBA.PUBLIC_MEMBER;

import Adapter.BuildAuto;
import Database.AddModel;
import Database.CreateTable;
import Database.DeletModel;
import Database.UpdateModel;
import Database.Updaters;
import Database.printTable;
import Model.Automobile;
import Util.FileIO;

public class DbDriver {
	static String path="./dbInit.txt";
	private static boolean flag=false;
	 static printTable printTable=new printTable(path);
    public static void main(String[]args){
    	//1. create the database if not exist
    	//before create the table first drop the table if the table exists
    	Scanner scanner=new Scanner(System.in);
    	
    	 StringBuilder welcome = new StringBuilder();
         welcome.append("*** Welcome to the User Interface! ***\n");
         welcome.append("*** This is a Car Configuration client. ***\n");
         welcome.append("*** To use this program, follow the instructions below. ***\n");
         System.out.println(welcome);

         System.out.println("\n* * * * * * * * * * * * * * *");
         System.out.println("1: create table");
         System.out.println("2: add model");
         System.out.println("3: delete model");
         System.out.println("4: update model");
         System.out.println("5: exit");
        while(true){
        	int commond=scanner.nextInt();
        	  switch (commond) {
        	  case 1:
        	       testCreateTable();
        	  	break;
        	  case 2: 
        		  testAddModel();
        		  break;
        	  case 3:
        		  testDeleteModel();
        		  break;
        	  case 4:
        		  testUpdateModel();
        		  break;
        	  }
        	 if(commond==5){
        		 System.out.println("you exist the db successfully!");
        		 break;
        	 }
        	  
        	      	
        }
 
    }
    	
    	public static void testDeleteModel(){
    	
    	///////////delete the model//////////////
    	try{
    		System.out.println("***************delete the model in the database*****************");
    		System.out.println("here is the available models in the db");
    		printTable.printTable("Automobile");
    		System.out.println("please input the model id you want to delete");
//          	BuildAuto ba=new BuildAuto();
//          	Scanner scanner=new Scanner(System.in);
//          	String filename=scanner.next();
//          	String fileType=null;
//          	Automobile auto=null;
//          	if(filename.contains("txt")){
//          		fileType="txt";
//          		 auto=new FileIO().buildAutoOjectByTxt(filename);
//          		 auto.print();
//          	}
//          	if(filename.contains("properties")){
//          		fileType="properties";
//          		 auto=new FileIO().buildAutoObjectByProperties(filename);
//          		 auto.print();
//          	}
    		DeletModel deletModel=new DeletModel(path);
    		Scanner scanner=new Scanner(System.in);
    		deletModel.DeleteModel(scanner.next());
    		printTable.printTable("Automobile");
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	
    	
    	
       }
    	
    	public static void testCreateTable(){
    		CreateTable createTable=new CreateTable(path);
      		createTable.dropTable();
          	createTable.CreateTableShema("./CreateTable.txt");
    	}
    	
    
    	public static void testAddModel(){
      	//2. Add model to the database
          	AddModel addModel=null;
      	try{
      	    addModel=new AddModel(path);
          	BuildAuto ba=new BuildAuto();
          	
          
          
          	while(true){
          		System.out.println("please input the filename you want to insert into table, you can add txt file or properties file( input -1 to finish the add model process)");
          		Scanner scanner=new Scanner(System.in);
              	String filename=scanner.next();
            	String fileType=null;
              	Automobile auto=null;
          		if(filename.equals("-1")) {
          			System.out.println("you successfully add models to the db!");
          			break;
          		}
          	if(filename.contains("txt")){
          		fileType="txt";
          		 auto=new FileIO().buildAutoOjectByTxt(filename);
          		 //auto.print();
          		addModel.AddModel(auto);
              	printTable.printTable("Automobile");
              	printTable.printTable("OptionSet");
              	printTable.printTable("Options");
          		 continue;
          	}
          	if(filename.contains("properties")){
          		fileType="properties";
          		 auto=new FileIO().buildAutoObjectByProperties(filename);
          		// auto.print();
          		addModel.AddModel(auto);
              	printTable.printTable("Automobile");
              	printTable.printTable("OptionSet");
              	printTable.printTable("Options");
          		 continue;
          	}
          	
          	
          	}
      	}catch(Exception e){
      		e.printStackTrace();
      	}finally{
       	   // addModel.CloseDB();
//      	    printTable.CloseDB();
      	}
    }
    public static void testUpdateModel(){
    	///3. Update the model
    	try{
    		CreateTable createTable=new CreateTable(path);
      		createTable.dropTable();
          	createTable.CreateTableShema("./CreateTable.txt");
          	AddModel addModel=new AddModel(path);
          	BuildAuto ba=new BuildAuto();
          	Automobile auto=new FileIO().buildAutoOjectByTxt("BMW.txt");
          	addModel.AddModel(auto);
    		///////update the model//////////
    		System.out.println("++++++++++++update the model name from BMW to Ford++++++++++");
    		UpdateModel updateModel=new UpdateModel(path);
    		updateModel.updateModelName("BMW", "Ford");
    		printTable.printTable("Automobile");
  
    		System.out.println("+++++++++++++update the baseprice++++++++++");
    		updateModel.updateBasePrice("Ford", 1111);
    		printTable.printTable("Automobile");
    
    		System.out.println("+++++++++++update the OptionSet name from Color to ColorSet++++++++++++++");

     		updateModel.updateOpsetName("Ford", "Color", "ColorSet");
    		printTable.printTable("OptionSet");

    		System.out.println("+++++++++++update the Option name from Automatic to Designed++++++++++++++");
    		updateModel.updateOptionName("Ford", "Transmission", "Automatic", "Designed");
    		printTable.printTable("Options");
    		System.out.println("+++++++++++update the Option price ++++++++++++++");
    	    updateModel.updateOptionPrice("Ford", "Transmission", "Designed", 1000);
    		printTable.printTable("Options");
    	}catch(Exception e){
//    		e.printStackTrace();
    	}
    }
    	
    	
    	
 
}
