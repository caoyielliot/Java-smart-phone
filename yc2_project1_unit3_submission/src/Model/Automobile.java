package Model;

import java.io.Serializable;
import java.util.ArrayList;

import Model.OptionSet.Option;



public class Automobile implements Serializable{ // This class will represent the Model. String name;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;// the name of model
	private float baseprice;
    private ArrayList<OptionSet> opsetList;
    private ArrayList<Option> choiceList;
	   /***Automobile***/
    //constructor without parameter
    public Automobile(){
    	
    }
	//constructor with modelname , baseprice
    public Automobile(String modelName,float baseprice){
    	this.name=modelName;
    	this.baseprice=baseprice;
    	this.opsetList=new ArrayList<OptionSet>();
    	this.choiceList=new ArrayList<Option>();
    }
    //get the modelName
    public String getModelName(){
    	return name;
    }
    //get the baseprice
    public float getBasePrice(){
    	return baseprice;
    }
    //set the ModelName
    public void setModelName(String modelName){
    	this.name=modelName;
    }
    //set the baseprice
    public void setBasePrice(float price){
    	this.baseprice=price;
    }
    public void printBaseInfo() {
		System.out.println(this.getModelName());
		System.out.println("Base Price :"
				+  this.getBasePrice());
		System.out.println();
	}

    /***OptionSet***/
    //set the OptionSet
    public void setOpset(String setName){
    	opsetList.add(new OptionSet(setName));
    }
    //get the opset
    public ArrayList<OptionSet> getopsetList(){
		return opsetList;
    }
    //get the opset by setName
    public OptionSet getopsetByName(String setName){
    	for(OptionSet opset:opsetList){
    		
    		if(opset.getOptionSetName().equals(setName)){
    			return opset;
    		}
    	}
    	return null;
    }
    //delete the OptionSet by setName
    public void deleteOptionSet(String setName){
    	for(OptionSet opset:opsetList){
    		if(opset.getOptionSetName().equals(setName)){
    			opsetList.remove(opset);
    		}
    	}
    }
    //update the OptionSet By setName
    public void updateOptionSetName(String setName, String newname) {
		for (OptionSet optset : opsetList) {
			if (optset.getOptionSetName().equals(setName)) {
				optset.setOptionSetName(newname);
				return;
			}
		}
	}
    // Method to update option price 	
   	public void updateOptionPrice(String setName, String optionName, float newprice) {
//        if(getopsetByName(setName)!=null){
//       	   getopsetByName(setName).updateOptionPrice(optionName, newprice);
//        }
        for (OptionSet optset : opsetList) {
			if (optset.getOptionSetName().equals(setName)) {
			  // optset.updateOptionPrice(optionName, newprice);
			  ArrayList<Option> opList= optset.getOptionSet();
			  for(Option op:opList){
				  if(op.getName().equals(optionName)){
					  op.setPrice(newprice);
				  }
			  }
			}
		}
   	}
//update the option Name
   	public void updateOptionName(String setName,String optionName,String opNewname){
   		for(OptionSet opset:opsetList){
   			if(opset.getOptionSetName().equals(setName)){
   				ArrayList<Option> opList=opset.getOptionSet();
   				for(Option op:opList){
   					if(op.getName().equals(optionName)){
   						op.setName(opNewname);
   					}
   				}
   			}
   		}
   	}
   
    //print the optionset
    public void printAllOptionSet() {
		for (OptionSet optset : opsetList) {
			System.out.println(optset.getOptionSetName() + ":");
			optset.printAllOptions();
			System.out.println();
		}
	}
    /***************** Option *****************/

	// Methods to get Option
	protected Option getOption(String setName, String optionName) {
		for (OptionSet optset : opsetList) {
			if (optset.getOptionSetName().equals(setName)) {
				return optset.getOption(optionName);
			}
		}
		return null;
	}

	// Methods to get Option price
	public float getOptionPrice(String setName, String optionName) {
		for (OptionSet optset : opsetList) {
			if (optset.getOptionSetName().equals(setName)) {
				return optset.getOptionPrice(optionName);
			}
		}
		return 0;
	}

	
	// Setter for option
	public void setOption(String setName,String opName, float price) {
//		for(OptionSet opset:opsetList){
//			if(opset.getOptionSetName().equals(setName)){
//		        opsetList.add(new OptionSet(setName));
//		        for(OptionSet opset:opsetList){
//					if(opset.getOptionSetName().equals(setName)){
//				    opset.setOption(opName, price);
//		   }
//	  }
		getopsetByName(setName).setOption(opName, price);
		
		
		
}
		

		

	// Method to delete option
	public void deleteOption( String optionName) {

		for(Option op:choiceList){
			if(op.getName().equals(optionName)){
				choiceList.remove(op);
			}
		}
	}


	

	/******** Printer **********/
	public void print() {
		this.printBaseInfo();
		this.printAllOptionSet();
	}
	
	/******** Choice *********/
	// Getter for choice name
	public String getOptionChoice(String setName) {
		return getopsetByName(setName).getChoiceName();
	}
	
	// Getter for choice price
	public float getOptionChoicePrice(String setName){
		return getopsetByName(setName).getChoicePrice();
	}
	
	// Setter for option choice
	public void setOptionChoice(String setName, String optionName){
		OptionSet optset = getopsetByName(setName);
		 choiceList.add(optset.getOption(optionName));
	}
	
	// Method to get the total price
	public float getTotalPrice() {
		float sum = baseprice;
		for (Option opt : choiceList) {
			sum += opt.getPrice();
		}
		return sum;
	}
	
	// Method to print choice
	public void printChoice() {
		for (Option opt : choiceList) {
			System.out.println("Option : " + opt.getName() + "Price : "
					+ opt.getPrice());
		}
	}
    
    
    
   }

    
	

