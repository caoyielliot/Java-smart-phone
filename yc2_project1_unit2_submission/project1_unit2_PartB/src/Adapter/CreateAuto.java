package Adapter;

import Model.Automobile;

/**
 * 
 * @author Yi Cao
 * @andrewid yc2
 */
public interface CreateAuto {
	//given the filename and read from the file to build as auto
	   public void BuildAuto(String filename);
	//given the model name and print it 
	   public void PrintAuto(String ModelName);
}
