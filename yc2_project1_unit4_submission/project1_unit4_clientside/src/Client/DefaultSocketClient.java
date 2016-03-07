package Client;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class DefaultSocketClient implements Runnable {
	
	/**
	 * handle the client request:
	 * if multiple threads (not only one client connect to server) , we may need to consider the 
	 * implementation of multithreading
	 */
   private Socket socket;
   private ObjectOutputStream objectOutputStream;
   private ObjectInputStream objectInputStream;
   
   private CarModelOptionsIO carModelOptionsIO=new CarModelOptionsIO();
   
   public DefaultSocketClient(Socket socket){
	   this.socket=socket;
   }
@Override
public void run() {
  if(openConnection()){
	  handlerSession();
	  closeSession();
  }
}
public boolean openConnection(){
	try{
		
		objectOutputStream=new ObjectOutputStream(socket.getOutputStream());//broadcast information to the client
		objectInputStream=new ObjectInputStream(socket.getInputStream());//receive information from client
		
	}catch (Exception e){
		e.printStackTrace();
		return false;
	}
	
	return true;
}
   
public void handlerSession(){
	//get the request information
	 String  inString=null;
	 String outString=null;
	try{
		System.out.println("please input your request: Exit, Upload, Get, Select");
		BufferedReader bReader=new BufferedReader(new InputStreamReader(System.in));
	    
		inString=bReader.readLine();
		
		if(inString.equals("Exit")){
			//if the client input Exit , the client would quit
			objectOutputStream.writeObject(inString);
			carModelOptionsIO.verify(objectInputStream);
			objectOutputStream.flush();
			return ;
		}
		if(inString.equals("Upload")){
			//if the client input Upload and specify the url, the client side would write it to outputstream
			objectOutputStream.writeObject(inString);
			carModelOptionsIO.verify(objectInputStream);
			objectOutputStream.flush();
			String filename="";
			System.out.println("please input the filename...");
			filename=bReader.readLine();
			if(filename.contains("txt")){
				//if client upload txt file, then server would parse it in txt way
				objectOutputStream.writeObject(filename);
				carModelOptionsIO.verify(objectInputStream);
				objectOutputStream.flush();
				carModelOptionsIO.transferTxtToServer(objectOutputStream, filename);
				carModelOptionsIO.verify(objectInputStream);
			}
			else{
				//if the client upload properties file, then the server would parse it in properties way
				objectOutputStream.writeObject(filename);
				carModelOptionsIO.verify(objectInputStream);
				objectOutputStream.flush();
				carModelOptionsIO.transferPropertiesToServer(objectOutputStream, filename);
				carModelOptionsIO.verify(objectInputStream);
			}
			
		}
		if(inString.equals("Get")){
			//Get all the autos in the autolist
			objectOutputStream.writeObject(inString);
			carModelOptionsIO.verify(objectInputStream);
			objectOutputStream.flush();
			carModelOptionsIO.getAllAuto(objectInputStream);
			objectOutputStream.flush();
		}
		if(inString.equals("Select")){
			// select the auto and output the details in console
			//System.out.println("You can choose the following auto models");
			objectOutputStream.writeObject(inString);
			carModelOptionsIO.verify(objectInputStream);
			objectOutputStream.flush();
			carModelOptionsIO.getAllAuto(objectInputStream);
			objectOutputStream.flush();
			System.out.println("please choose one model:");
			String modelName=bReader.readLine();
			objectOutputStream.writeObject(modelName);//write it to the serverside
        //	carModelOptionsIO.verify(objectInputStream);
			objectOutputStream.flush();
			carModelOptionsIO.selectAuto(objectInputStream);//receive from the server side
			//carModelOptionsIO.verify(objectInputStream);
			objectOutputStream.flush();
		}
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
}
public void closeSession(){
	// close all the  session
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
