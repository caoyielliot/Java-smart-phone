package server;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import Model.Automobile;

public class DefaultSocketServer implements Runnable {
	
	/**
	 * handle the client request:
	 * if multiple threads (not only one client connect to server) , we may need to consider the 
	 * implementation of multithreading
	 */
   private Socket socket;
   private ObjectOutputStream objectOutputStream;
   private ObjectInputStream objectInputStream;
   private String  inString=null;
   private String outString=null;
   public DefaultSocketServer(Socket socket){
	   this.socket=socket;
   }
@Override
public void run() {
	// TODO Auto-generated method stub
	if (openConnection()) {
		
		handlerSession();
		closeSession();
	}
	
}
public boolean openConnection() {
	try{
		
		objectOutputStream=new ObjectOutputStream(socket.getOutputStream());//broadcast information to the client
		objectInputStream=new ObjectInputStream(socket.getInputStream());//receive information from client
//		InputStream inputStream =socket.getInputStream();
//		InputStreamReader isReader=new InputStreamReader(inputStream);
//		BufferedReader brBufferedReader=new BufferedReader(isReader);
		//System.out.println(socket.getInputStream().read());
	}catch (Exception e){
		e.printStackTrace();
		return  false;
	}
	return true;
}
public void handlerSession(){
	try{
		
		BuildCarModelOptions buildCarModelOptions=new BuildCarModelOptions();
	    inString=(String)objectInputStream.readObject();
	   // System.out.println(inString);
	    if(inString.equals("Exit")){
	    	//if the client says Exit, then send log off info to the client side
	    	outString="The Client has logged off!";
	    	objectOutputStream.writeObject(outString);
	    	objectOutputStream.flush();
	    	return ;
	    }
	    if(inString.equals("Upload")){
	    	//if the client says Upload, then server access different file types and build it to auto
	    	outString="The Client is uploading...";
	    	objectOutputStream.writeObject(outString);
	    	objectOutputStream.flush();
	    	//System.out.println(outString);
	    	String filename=(String)objectInputStream.readObject();
	    	if(filename.contains("txt")){
	    		//build txt file to auto object
	    		outString="The Client choose to upload txt file";
		    	objectOutputStream.writeObject(outString);
		    	objectOutputStream.flush();
		    	buildCarModelOptions.BuildAutoByTxt(objectInputStream);
		    	outString="Successfully upload txt file";
		    	objectOutputStream.writeObject(outString);
		    	objectOutputStream.flush();
	    	}else if(filename.contains("properties")){
	    		//build properties file to auto object
	    		outString="The Client choose to upload properties file";
		    	objectOutputStream.writeObject(outString);
		    	objectOutputStream.flush();
		    	buildCarModelOptions.BuildAutoByProperties(objectInputStream);
		    	outString="Successfully upload properties file";
		    	objectOutputStream.writeObject(outString);
		    	objectOutputStream.flush();
	    	}
	    }
	    if(inString.equals("Get")){
	    	//get all the autos in the autolist
	    	outString="The client is seeking all the autos in autolist!";
	    	objectOutputStream.writeObject(outString);
	    	objectOutputStream.flush();
	    	ArrayList<String> arrayList = buildCarModelOptions.getAllAuto();
	    	objectOutputStream.writeObject(arrayList);
	    	objectOutputStream.flush();
	    }
	    if(inString.equals("Select")){
	    	// select the auto and return the object to client side
	    	outString="The client can choose the following car models!";
	    	objectOutputStream.writeObject(outString);
	    	objectOutputStream.flush();
	    	ArrayList<String> arrayList = buildCarModelOptions.getAllAuto();
	    	objectOutputStream.writeObject(arrayList);
	    	objectOutputStream.flush();
	    	Automobile auto=buildCarModelOptions.selectAuto(objectInputStream);
	    	objectOutputStream.writeObject(auto);
	    	objectOutputStream.flush();
	    	
	    }
		
	}catch (Exception e){
		e.printStackTrace();
	}
}
public void closeSession(){
	//close all the session
	if(socket!=null){
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}


   
   
}
