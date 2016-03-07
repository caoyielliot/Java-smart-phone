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
import java.net.UnknownHostException;
import java.util.ArrayList;

import org.eclipse.jdt.internal.compiler.ast.ThisReference;

import Model.Automobile;

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
   private Object resultObject;
   String modelName=null;
   String  inString=null;
   String outString=null;
   public DefaultSocketClient(Socket socket){
	   this.socket=socket;
   }
   public DefaultSocketClient(Socket socket,String inString){
	   this.socket=socket;
	   this.inString=inString;
   }
   
   public DefaultSocketClient(String strHost, int iPort, String inString) {
	try {
		this.socket=new Socket(strHost, iPort);
		this.inString=inString;
		
	} catch (UnknownHostException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
   public DefaultSocketClient(String strHost, int iPort, String inString, String modelName) {
		try {
			this.socket=new Socket(strHost, iPort);
			this.inString=inString;
			this.modelName=modelName;
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	
	try{
		BufferedReader bReader=new BufferedReader(new InputStreamReader(System.in));
		if(inString==null){
			System.out.println("please input your request: Exit, Upload, Get, Select");
			inString=bReader.readLine();
		}

		
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
			 ArrayList<String> arrayList=new ArrayList<String>();
			arrayList=carModelOptionsIO.getAllAuto(objectInputStream);
			resultObject=arrayList;
			//System.out.println("resultobjectinner"+resultObject);
	   if(arrayList.size()==0){
			  System.out.println("Server:List is Empty");
		  }else{
			 for(String str:arrayList){
				 System.out.println(str);
			 }
		  }
			objectOutputStream.flush();
		}
		if(inString.equals("Select")){
			// select the auto and output the details in console
			//System.out.println("You can choose the following auto models");
			objectOutputStream.writeObject(inString);
			carModelOptionsIO.verify(objectInputStream);
			objectOutputStream.flush();
			 ArrayList<String> arrayList=new ArrayList<String>();
			arrayList=carModelOptionsIO.getAllAuto(objectInputStream);
			 if(arrayList.size()==0){
				  System.out.println("Server:List is Empty");
			  }else{
				 for(String str:arrayList){
					 System.out.println(str);
				 }
			  }
			objectOutputStream.flush();
			System.out.println("please choose one model:");
			if(modelName==null){
				modelName=bReader.readLine();
			}
			objectOutputStream.writeObject(modelName);//write it to the serverside
        //	carModelOptionsIO.verify(objectInputStream);
			objectOutputStream.flush();
			Automobile automobile=carModelOptionsIO.selectAuto(objectInputStream);//receive from the server side
			System.out.print("baseprice"+automobile.getBasePrice());
			automobile.print();
			resultObject=automobile;
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
//public void setResult(Object resultObject){
//	this.resultObject=resultObject;
//}
//return the result;
public Object getResult(){
	//System.out.println("resultobjectoutter"+resultObject);
    return resultObject;
}
   
}
