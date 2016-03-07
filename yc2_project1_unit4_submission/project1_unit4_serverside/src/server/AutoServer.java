package server;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import Model.Automobile;
public interface AutoServer {
 
	
//Accept properties object from client socket over an ObjectStream and create an Automobile and add it to the AutoList
	
	public void BuildAutoByProperties(ObjectInputStream objectInputStream);
	
	
//Accept txt file from client socket over an ObjectStream and create an Automobile and add it to the AutoList
	public void BuildAutoByTxt(ObjectInputStream objectInputStream);

//Get all the AutoList in LinkedHashMap
	public ArrayList<String> getAllAuto();
	
//Select the auto 
	public Automobile selectAuto(ObjectInputStream objectInputStream);
}
