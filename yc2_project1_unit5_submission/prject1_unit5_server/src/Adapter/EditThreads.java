package Adapter;

import scale.EditOptions;

public interface EditThreads {
	//this interface is used to bridge between the BuildAuto and EditOptions class
	public void updateOptionSetName(EditOptions edit, String modelname, String optionsetname, String newopsetname);
	public void updateOptionPrice(EditOptions edit,String Modelname, String OptionSetName, String OptionName, float newprice);
	public void updateOptionName(EditOptions edit,String modelname,String optionsetName,String OptionName, String optionNewName);
}
