package Database;

import scale.EditOptions;

public interface Updaters {
    
	//Update the model Name or Price
	public void updateModelName(String ModelName, String newModelName);
	public void updateBasePrice(String ModelName, float newBasePrice);
	
	//Update the Optionset Name
	public void updateOpsetName(String modelName,String setName, String newSetName);
	
	
	//Update the OptionsName or Price
	public void updateOptionPrice(String Modelname, String OptionSetName, String OptionName, float newprice);
	public  void updateOptionName(String modelname, String optionsetName, String OptionName, String optionNewName);
}
