package server;

import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.print.attribute.standard.ReferenceUriSchemesSupported;

import Adapter.BuildAuto;
import Model.Automobile;

public class BuildCarModelOptions implements AutoServer{
     BuildAuto ba=new BuildAuto();
	@Override
	public void BuildAutoByProperties(ObjectInputStream objectInputStream) {
		// if the input is properties file, build it to auto object
		ba.BuildAutoByProperties(objectInputStream);
		
	}

	@Override
	public void BuildAutoByTxt(ObjectInputStream objectInputStream) {
		// if the input is txt file, build it to auto object
		ba.BuildAutoByTxt(objectInputStream);
	}

	@Override
	public ArrayList<String> getAllAuto() {
		// get all the autos in the autolist
	ArrayList<String>	autos=ba.getAllAuto();
    return autos;
	}
	//select the auto with objectInputStream
	public Automobile selectAuto(ObjectInputStream objectInputStream){
		Automobile automobile=ba.selectAuto(objectInputStream);
		return automobile;
	}

	
     
}
