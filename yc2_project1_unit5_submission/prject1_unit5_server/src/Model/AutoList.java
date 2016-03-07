package Model;

import java.util.ArrayList;
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
public static void setAutoListAutoMobileByTxt(String filename) throws AutoException{
	Automobile auto = new FileIO().buildAutoOjectByTxt(filename);
	setAutoList(auto);
	//auto.print();
}
//set the Automobile
public static void setAutoListAutoMobileByProperties(String filename) throws AutoException{
	//需要改
	Automobile auto =new FileIO().buildAutoObjectByProperties(filename);
	setAutoList(auto);
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
/***print the AutoList***/
public void printAutoList(){
	for(Automobile auto:autoList.values()){
		auto.print();
	}
}
//get all the name of AutoList
public ArrayList<String> getNames(){
	ArrayList<String> arrayList=new ArrayList<String>();
	for(Automobile auto:autoList.values()){
		arrayList.add(auto.getModelName());
	}
	return arrayList;
}


}
