package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server  {
	private ServerSocket serverSocket;
	public Server(){
		try {
			serverSocket=new ServerSocket(9999);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Could not listen on port:8088");
			System.exit(1);//exception
			//e.printStackTrace();
		}
		
	}
	public void start() {
		try{
			
			while (true){
				//for multiple clients' connection
				System.out.println("waiting for the client to connect...");
				Socket socket=serverSocket.accept();
				System.out.println("Client successfully connected!");
			//	System.out.println(socket.getInputStream().toString()+"ddddd");
				DefaultSocketServer cHandler=new DefaultSocketServer(socket);
				Thread thread=new Thread(cHandler);
				thread.start();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	 public static void main(String[]args){
	    	Server server=new Server();
	    	server.start();
	    }

}
