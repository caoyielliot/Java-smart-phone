package Adapter;

public interface UpdateAuto {
	//This function searches the Model for a given OptionSet and sets the name of OptionSet to newName.
  public void updateOptionSetName(String modelname, String optionsetname, String newname);
    //This function searches the Model for a given OptionSet and Option name, and sets the price to newPrice
  public void updateOptionPrice(String Modelname, String Optionname, String Option, float newprice);
}
