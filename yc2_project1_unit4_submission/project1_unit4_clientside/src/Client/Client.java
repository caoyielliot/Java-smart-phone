package Client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client extends SocketConstants{
	private Socket socket;
	   public Client(String strHost,int iPort){
		   try {
			   //constructor to initiate the client socket;
			socket=new Socket(strHost,iPort);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if(DEBUG)
				System.out.println("could not connect to the server!");
			 System.exit(1);
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
		   Client client=new Client(PORT_IP,PORT_NUMBER);//call the constructor to initiate the client
	       client.start();//start the client side;
	   }
}
