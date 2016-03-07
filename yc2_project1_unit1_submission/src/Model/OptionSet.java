package Model;

import java.io.Serializable;
import java.util.Arrays;

public class OptionSet implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Option opt[];//possible options of each attribute
	private String name;//the name of car attribute

	 protected OptionSet(String n, int size) {
		this.opt = new Option[size];
		this.name = n;
		for (int i = 0; i < size; i++) {
			opt[i] = new Option();
		}
	 
	}
	 protected OptionSet(int size) {
			this.opt = new Option[size];
			for (int i = 0; i < size; i++) {
				opt[i] = new Option();
			}
		 
		}

	protected  OptionSet() {
		super();
	}
	
	protected Option getOptionByName(String name){
		int i=0;
		while(!(opt[i].name.equals(name))) ++i;
		return opt[i];
	}
	protected Option getOptionByIndex(int index){
		return opt[index];
	}
	protected int findOptionByName(String name){
		int index = 0;
		while(!(opt[index].name.equals(name))) ++index;
		if(index<opt.length){
			return index;
		}
		return -1;
	}
	
	    
       
		protected  void deleteOptByIndex(int index){
			opt[index]=null;
			
		}
		
		protected void deleteOptByName(String name){
			int i=0;
			while(!(opt[i].name.equals(name))){
				++i;
			}
			opt[i]=null;
			
		}
		
		
		
		protected Option[] getOpt() {
			return opt;
		}

		protected void setOpt(Option[] opt) {
			this.opt = opt;
		}
		
		protected  void setOpt(int optSize, String opName, float opPice) {
			opt[optSize].setName(opName);
			opt[optSize].setPrice(opPice);
		}

		protected String getName() {
			return name;
		}

		protected void setName(String name) {
			this.name = name;
		}
        protected void updateOpt(String optName,float price){
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
     
     
     
     
     
     
     
	
