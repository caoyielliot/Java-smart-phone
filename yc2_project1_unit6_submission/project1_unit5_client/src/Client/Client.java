package Client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	public Socket socket;
	   public Client(){
		   try {
			   //constructor to initiate the client socket;
			socket=new Socket("localhost",9999);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	   
	  public void start(){
		  
		 
			  try{
				  //use the defaultSocketClient to access the server's 
				  //outputstream and write the reply to the client's inputstream 
				  DefaultSocketClient defaultSocketClient=new DefaultSocketClient(socket);
				  Thread thread=new Thread(defaultSocketClient);
				//  thread.setDaemon(true);
				  thread.start();
				
			  }catch(Exception e){
				  e.printStackTrace();
			  }
		   
	  }
	  
	  public static void main(String[]args){
		   Client client=new Client();//call the constructor to initiate the client
	       client.start();//start the client side;
	   }
}
