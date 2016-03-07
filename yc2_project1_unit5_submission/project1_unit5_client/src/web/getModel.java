package web;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.webkit.ThemeClient;

import Client.CarModelOptionsIO;
import Client.Client;
import Client.DefaultSocketClient;

/**
 * Servlet implementation class servlet
 */
public class getModel extends MyServlet  {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getModel() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)  {
		// TODO Auto-generated method stub
		ArrayList<String> resArrayList=new ArrayList<String>();
		String autos=null;
		DefaultSocketClient defaultSocketClient=new DefaultSocketClient("localhost",9999,"Get");
		Thread thread=new Thread(defaultSocketClient);
		thread.start();
		 boolean sockIsAlive = true;

	        do {
	            if(sockIsAlive && !thread.isAlive()) {
	                sockIsAlive = false;
	                System.out.println("sock is dead.");
	            }

	        } while(sockIsAlive);
	        
	    resArrayList=(ArrayList<String>)defaultSocketClient.getResult();
		if(resArrayList==null){
			System.out.println("the list is empty!");
		}else{
			System.out.println("the auto list is:");
		  for(String str:resArrayList){
			  autos+="<option>" + str +"</option>";
			  System.out.println(str);
		  }
		}
		
		//JSP 
		request.setAttribute("data", autos);
		try {
			request.getRequestDispatcher("/GetAllAuto.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
		
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)  {
		// TODO Auto-generated method stub
	
		
		
		
	}

	

}
