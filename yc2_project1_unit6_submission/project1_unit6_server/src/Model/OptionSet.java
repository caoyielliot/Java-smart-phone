package Model;

import java.io.Serializable;
import java.util.ArrayList;


public class OptionSet implements Serializable {
	/**
	 * properties are private
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Option> opts;// possible options of each attribute
	private String name;// opsetName
	private Option choice;	
	/***OptionSet***/
	//constructor without parameter
	protected OptionSet(){
		
	}
	//constructor with name and option
	protected OptionSet(String name){
		this.name=name;
		opts=new ArrayList<Option>();
	}
	// Getters and Setters for OptionSet name

	public String getOptionSetName() {
			return name;
		}

	protected void setOptionSetName(String name) {
			this.name = name;
		}
	protected ArrayList<Option> getOptionSet(){
		return this.opts;
	}
	
	/***Option***/
	//set Option
	protected void setOption(String name, float price){
		opts.add(new Option(name,price));
	}
	//get the option price
	protected float getOptionPrice(String opname){
		for(Option opt:opts){
			if(opt.getName().equals(opname)){
				return opt.getPrice();
			}
		}
		return -1;
	}
	//get the option
	protected Option getOption(String opname){
		for(Option opt:opts){
			if(opt.getName().equals(opname)){
				return opt;
			}
		}
		return null;
	}
	//delete the Option
	protected void deleteOption(String opName){
		for(Option opt:opts){
			if(opt.getName().equals(opName)){
				opts.remove(opt);
			}
		}
	}
	//update the OptionPrice
	protected void updateOptionPrice(String opName,float price){
		for(Option opt:opts){
			if(opt.getName().equals(opName)){
				opt.setPrice(price);
			}
		}
	}
	
	/***choice***/
    // set the choice
	protected void setChoice(Option choice){
		opts.add(choice);
	}
	//get the choice price
	protected float getChoicePrice(){
		return choice.getPrice();
	}
	//get the choice name
	protected String getChoiceName(){
		return choice.getName();
	}

	
	@Override
	public String toString() {
		return "OptionSet [opts=" + opts + ", name=" + name + ", choice=" + choice + "]";
	}

  

	protected class Option implements Serializable {
		private static final long serialVersionUID = 1L;
		String name;// name of the option
		float price;// the price of option

		/***** Setters And Getters *****/
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

		// Constructors
		protected Option(String name, float price) {
			super();
			this.name = name;
			this.price = price;
		}

		protected Option() {
			this("no Name", 0);

		}
	}


  // print all options
	protected void printAllOptions() {
		for (Option opt : opts) {
			System.out.println(opt.getName() + ":Price "
					+ opt.getPrice());
		}

	}
	//get certain opset's Option Names
	public ArrayList<String> getOptionName(){
		ArrayList<String> arrayList=new ArrayList<String>();
		for(Option opt:opts){
			arrayList.add(opt.getName());
		}
		return arrayList;
	}

}
