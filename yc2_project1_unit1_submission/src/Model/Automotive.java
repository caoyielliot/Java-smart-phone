package Model;

import java.io.Serializable;
import java.util.Arrays;



public class Automotive implements Serializable{ // This class will represent the Model. String name;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;// the name of model
	private OptionSet opset[];//the possible optset of the model
    private float baseprice;
	
	public  Automotive(String name,float baseprice, int setSize, int[] optSize) {
		this.baseprice=baseprice;
		this.name=name;
		opset=new OptionSet[setSize];
		for(int i=0;i<setSize;i++ ){
			opset[i]=new OptionSet(optSize[i]);
		}
		
		
	}
	public Automotive() {
		super();
	}
    
	public String getName() {
		return name;
	}

	public OptionSet getOpsetByindex(int index) {
		//the index is the index number of the attribute
		return opset[index];
	}
	public OptionSet getOpsetByName(String name){
		int i=0;
		while(!(opset[i].getName().equals(name))) ++i;
		if(i<opset.length){
			return opset[i];
		}else{
			return null;
		}
	}
    public double getBaseprice(){
    	return baseprice;
    }
//    public OptionSet findOpset(String name){
//    	OptionSet opt;
//        for(int i=0;i<opset.length;i++){
//        	opt=opset[i];
//        	if(opt.name==name){
//        		return opt;
//        	}
//        }
//        return null;
//    }
   public int findOpsetByName(String name){
	   int index=0;
	   while(!(opset[index].getName().equals(name))){
		   ++index;
	   }
	   return index;
   }
   
	public void setName(String name) {
		this.name = name;
	}

	public void setOpset(OptionSet[] opset) {
		this.opset = opset;
	}
	public void setOpSetName(int index, String name){
		opset[index].setName(name);
	}
   public void setOpts(int setIndex, int optIndex, String opName, float opPrice){
	   opset[setIndex].setOpt(optIndex, opName, opPrice);
   }
	
	public void setBaseprice(float baseprice) {
		this.baseprice = baseprice;
	}

    public void deleteOptionSetByName(String name){
    	 
    	  opset[findOpsetByName(name)]=null;
    }	
    public void deletOptionSetByIndex(int setIndex, int opindex){
    	  opset[setIndex].deleteOptByIndex(opindex);
    }
    public void delteOptionSetByName(int setIndex, String optName){
    	opset[setIndex].deleteOptByName(optName);
    	
    }
    public void deletOptionSetByName(String setName, String optName){
    	opset[findOpsetByName(setName)].deleteOptByName(optName);
    }
    public void deletOptionSetByName(String setName, int index){
    	opset[findOpsetByName(setName)].deleteOptByIndex(index);
    }
    public void updateOptset(int optIndex,String setname,float price,String optName){
    	int index=findOpsetByName(setname);
    	opset[index].setOpt(optIndex, optName, price);
    }
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
