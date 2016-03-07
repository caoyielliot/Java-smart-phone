package Model;

import java.io.Serializable;
import java.util.Arrays;

 public class OptionSet implements Serializable{
	/**
	 * properties are private
	 */
	private static final long serialVersionUID = 1L;
    private Option opt[];//possible options of each attribute
	private String name;//the name of car attribute
   //constructor to the properties are not null
	 protected OptionSet(String n, int size) {
		this.opt = new Option[size];
		this.name = n;
		for (int i = 0; i < size; i++) {
			opt[i] = new Option();
		}
	 
	}
	 //constructor of the size property
	 protected OptionSet(int size) {
			this.opt = new Option[size];
			for (int i = 0; i < size; i++) {
				opt[i] = new Option();
			}
		 
		}

	public  OptionSet() {
		super();
	}
	//to get the Option by name
	protected Option getOptionByName(String name){
		int i=0;
		while(!(opt[i].name.equals(name))) ++i;
		return opt[i];
	}
	//to get the Option by its index
	protected Option getOptionByIndex(int index){
		return opt[index];
	}
	//to find the Option By its name, if not found return -1;
	protected int findOptionByName(String name){
		int index = 0;
		while(!(opt[index].name.equals(name))) ++index;
		if(index<opt.length){
			return index;
		}
		return -1;
	}
	
	    
       //delete the Option By its index
		protected  void deleteOptByIndex(int index){
			opt[index]=null;
			
		}
		//delete the Option By its Name
		protected void deleteOptByName(String name){
			int i=0;
			while(!(opt[i].name.equals(name))){
				++i;
			}
			opt[i]=null;
			
		}
		
		
		// get all the options
		public Option[] getOpt() {
			return opt;
		}
        //set the Option 
		protected void setOpt(Option[] opt) {
			this.opt = opt;
		}
		//set the Option price and name by its index
		protected  void setOptByIndex(int optIndex, String opName, float opPice) {
			opt[optIndex].setName(opName);
			opt[optIndex].setPrice(opPice);
		}

		public String getName() {
			return name;
		}

		protected void setName(String name) {
			this.name = name;
		}
		
		//update the option price and name by optName
        protected void updateOptByName(String optName,float price){
        int optIndex=findOptionByName(optName);
        opt[optIndex].setName(optName);
        opt[optIndex].setPrice(price);
        }
		@Override
		public String toString() {
			return "OptionSet [opt=" + Arrays.toString(opt) + ", name=" + name + "]";
		}

       
		 protected class Option implements Serializable{
		    /**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			String name;// name of the option
			float price;//the price of option
			/***** Setters And Getters    *****/
			protected String getName() {
				return name;
			}
			protected void setName(String name) {
				this.name = name;
			}
			protected float getPrice() {
				return price;
			}
			protected void setPrice(float price) {
				this.price = price;
			}
			@Override
			public String toString() {
				return "Option [name=" + name + ", price=" + price + "]";
			}
			//Constructors
			protected Option(String name, float price) {
				super();
				this.name = name;
				this.price = price;
			}
			protected Option() {
				this("no Name",0);
				
			}
		}


		
       	
       	
       }
     
     
     
     
     
     
     
	
