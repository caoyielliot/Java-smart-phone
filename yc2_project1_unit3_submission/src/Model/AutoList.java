package Model;

import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Set;

import Adapter.BuildAuto;
import Exception.AutoException;
import Exception.ExceptionNum;
import Model.OptionSet.Option;
import Util.FileIO;

public class AutoList {
  private static LinkedHashMap<String,Automobile> autoList;
  private static String make;
  //constructor without parameter
  public AutoList(){
	  autoList=new LinkedHashMap<String,Automobile>();
  }
  //constructor for autolist
  public AutoList(LinkedHashMap<String,Automobile> autolist){
	  autoList=autolist;
  }
  public AutoList(LinkedHashMap<String,Automobile> autolist,String make){
	  autoList=autolist;
	  this.make=make;
  }
  public static LinkedHashMap<String,Automobile> getAutoList() {
	return autoList;
}
  public String getmake(){
	return make;
}
  
  public static void setAutoList(LinkedHashMap<String,Automobile> autoList) {
	AutoList.autoList = autoList;
}
  
/***Automobile***/
  //set the autoList
public static void setAutoList(Automobile auto){
	autoList.put(auto.getModelName(), auto);
}

//set the Automobile
public static void setAutoListAutoMobile(String filename) throws AutoException{
	Automobile auto = new FileIO().buildAutoOject(filename);
//	System.out.println(auto.getModelName());
//	System.out.println("Base Price :"
//			+  auto.getBasePrice());
//	System.out.println();
//	for(OptionSet opset:auto.getopsetList()){
//		System.out.println(opset.getOptionSetName()+":");
//	   for(Option op: opset.getOptionSet()){
//		   System.out.println(op.name+":"+op.price);
//	   }
//	}
	//auto.print();
	setAutoList(auto);
	//auto.print();
}
//get the Automobile
public static Automobile getAutoListAutoMobileByName(String modelName){
	return autoList.get(modelName);
}
//delete the Automobile
public static void deleteAutoListAutoMobile(String modelName){
	 autoList.remove(getAutoListAutoMobileByName(modelName));
}
//update the Automobile 
public static void updateAutoListAutoMobileByName(String modelName,Automobile opset){
 Set<Entry<String, Automobile>> entryset=autoList.entrySet();
 for(Entry<String, Automobile> entry:entryset){
	if(entry.getKey().equals(modelName)){
		entry.setValue(opset);
	}
 }
}


/***OptionSet***/
//update the OptionSet
public void updateOptionSetName(String modelName, String setName,
		String newName) throws AutoException {
	Automobile auto = autoList.get(modelName);
	if (auto != null) {
		auto.updateOptionSetName(setName, newName);
		autoList.put(modelName, auto);
	} else {
		throw new AutoException(ExceptionNum.CarModelNotFound);
	}
}

/***Option***/
//update the OptionPrice
public void updateAutoListOptionPrice(String ModelName, String setname,
		String optionname, int price) throws AutoException {
	Automobile auto = autoList.get(ModelName);
	if (auto != null) {
		auto.updateOptionPrice(setname, optionname, price);
		autoList.put(ModelName, auto);
	} else {
		throw new AutoException(ExceptionNum.CarModelNotFound);
	}
   }
}
