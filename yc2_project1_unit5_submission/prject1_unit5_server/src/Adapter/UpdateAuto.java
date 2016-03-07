package Adapter;

import scale.EditOptions;

public interface UpdateAuto {
	//This function searches the Model for a given OptionSet and sets the name of OptionSet to newName.
  public void updateOptionSetName(EditOptions edit,String modelname, String optionsetname, String newname);
    //This function searches the Model for a given OptionSet and Option name, and sets the price to newPrice
  public void updateOptionPrice(EditOptions edit,String Modelname, String Optionname, String Option, float newprice);
	//This function searches the Model for a given optionsetname and optionname and replace the optionname with a newname 
  public void updateOptionName(EditOptions edit,String modelname,String optionsetName,String OptionName, String optionNewName);

}
