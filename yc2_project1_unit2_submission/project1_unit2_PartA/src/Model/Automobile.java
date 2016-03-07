package Model;

import java.io.Serializable;
import java.util.Arrays;

import Model.OptionSet.Option;



public class Automobile implements Serializable{ // This class will represent the Model. String name;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;// the name of model
	private OptionSet opset[];//the possible option set of the model
    private float baseprice;
	//constructor to initiate the attributes
	public  Automobile(String name,float baseprice, int setSize, int[] optSize) {
		this.baseprice=baseprice;
		this.name=name;
		opset=new OptionSet[setSize];
		for(int i=0;i<setSize;i++ ){
			opset[i]=new OptionSet(optSize[i]);
		}
		
		
	}
	
	public Automobile() {
		super();
	}
    
	public String getName() {
		return name;
	}
	
	public OptionSet[] getOpset() {
		return opset;
	}
	//to get the OptionSet info according to setIndex
	public OptionSet getOpsetByindex(int index) {
		//the index is the index number of the attribute
		return opset[index];
	}
	//to get OptionSet info according to the name of Opset
	public OptionSet getOpsetByName(String name){
		int i=0;
		while(!(opset[i].getName().equals(name))) ++i;
		if(i<opset.length){
			return opset[i];
		}else{
			return null;
		}
	}
	//to get the baseprice
    public double getBaseprice(){
    	return baseprice;
    }
//to get the setIndex by the Name of Optionset
   public int findOpsetByName(String name){
	   int index=0;
	   while(!(opset[index].getName().equals(name))){
		   ++index;
	   }
	   
	   return index;
   }
   //to get the optionIndex by the name of option Name
   public int findOpsByName(String opsName, int setIndex){
	  
	  return opset[setIndex].findOptionByName(opsName);
   }
   //set the name of the model
	public void setName(String name) {
		this.name = name;
	}
   //set the Optionset of the model
	public void setOpset(OptionSet[] opset) {
		this.opset = opset;
	}
	//set the OptionSet Name by setIndex 
	public void setOpSetName(int index, String name){
		opset[index].setName(name);
	}
	//set the options name and price by the setIndex and optIndex
   public void setOpts(int setIndex, int optIndex, String opName, float opPrice){
	   opset[setIndex].setOptByIndex(optIndex, opName, opPrice);
   }
	//set the baseprice of the model
	public void setBaseprice(float baseprice) {
		this.baseprice = baseprice;
	}
     //delete the option set by the name of the opset
    public void deleteOptionSetByName(String name){
    	 
    	  opset[findOpsetByName(name)]=null;
    }	
    //delete the Option  by the setIndex and optIndex 
    public void deletOptionByIndex(int setIndex, int opindex){
    	  opset[setIndex].deleteOptByIndex(opindex);
    }
    //delete the Option by the setIndex and optName
    public void delteOptionByIndex(int setIndex, String optName){
    	opset[setIndex].deleteOptByName(optName);
    	
    }
    //delete the option by the setName and optName
    public void deletOptionByName(String setName, String optName){
    	opset[findOpsetByName(setName)].deleteOptByName(optName);
    }
    //delete the Option by the setName and optIndex
    public void deletOptionByName(String setName, int index){
    	opset[findOpsetByName(setName)].deleteOptByIndex(index);
    }
    //update the OptionSet's info by the setName and optIndex
    public void updateOptset(int optIndex,String setname,float price,String optName){
    	int index=findOpsetByName(setname);
    	opset[index].setOptByIndex(optIndex, optName, price);
    }
    //update the OptionSetName Name by the setName 
    public void updateOpsetName(String setname, String newopsetName){
    	
    	int index=findOpsetByName(setname);
    	opset[index].setName(newopsetName);
    }
    //update the OptionSet's option price by the OptionName and the setName
    public void updateOpsprice( String Optionname, String SetName, float newprice){
    	int setindex= findOpsetByName(SetName);
    	int optindex=opset[setindex].findOptionByName(Optionname);
        Option[] opt=opset[setindex].getOpt();
        opt[optindex].setPrice(newprice);
    }
    //printCar info
   public void printCar(){
	   System.out.println("car name:"+name);
	   for(int i=0;i<opset.length;i++){
		   System.out.println("the OptionSet name:"+opset[i].getName());
		   for(int j=0;j<opset[i].getOpt().length;j++){
			   System.out.println(opset[i].getOpt()[j]);
		   }
	   }
	   
	   
   }

    
	
}
