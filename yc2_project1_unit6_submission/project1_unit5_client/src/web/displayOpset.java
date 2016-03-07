package web;

import java.io.IOException;
import java.security.cert.PKIXRevocationChecker.Option;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Client.DefaultSocketClient;
import Model.Automobile;
import Model.OptionSet;

/**
 * Servlet implementation class displayOpset
 */
public class displayOpset extends MyServlet {
	private static final long serialVersionUID = 1L;
     String url;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public displayOpset() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		 String modelname = (String) request.getParameter("Model");
		//from ModelName to select the exact model from defaultsocketClient
		DefaultSocketClient defaultSocketClient=new DefaultSocketClient("localhost", 9999, "Select", modelname);
		Thread thread=new Thread(defaultSocketClient);
		thread.start();
		boolean sockIsAlive = true;
        do {
            if(sockIsAlive && !thread.isAlive()) {
                sockIsAlive = false;
                System.out.println("sock is dead.");
            }

        } while(sockIsAlive);
		Automobile automobile=(Automobile)defaultSocketClient.getResult();
		//get the opset detail and resend it to the jsp page
		
		
		
		ArrayList<OptionSet> opsetList=new ArrayList<OptionSet>();
		ArrayList<String> names=new ArrayList<String>();
		opsetList=automobile.getopsetList();
//		names=automobile.getOptionsName("Color");
//		System.out.print(names.toString());
		
		for(OptionSet opset:opsetList){
			
			String optionName = "";
			System.out.println("opsetName:"+opset.getOptionSetName());
			names=automobile.getOptionsName(opset.getOptionSetName());
			//System.out.println(names.toString());
			System.out.println();
			for(String str:names){
				optionName+="<option>" + str +"</option>";
			}
			System.out.println(optionName);
			
			request.setAttribute(opset.getOptionSetName(), optionName);
		}
		request.setAttribute("model", modelname);
		try {
			request.getRequestDispatcher("/displayOpset.jsp").forward(request, response);
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
		//get the selected file
		
	   String[] values=new String[6];	
	   values=request.getParameterValues("selectedchoices");
	   String modelname=values[0];
	   //get the selected file;
	   DefaultSocketClient defaultSocketClient=new DefaultSocketClient("localhost", 9999, "Select", modelname);
		Thread thread=new Thread(defaultSocketClient);
		thread.start();
		boolean sockIsAlive = true;
       do {
           if(sockIsAlive && !thread.isAlive()) {
               sockIsAlive = false;
               System.out.println("sock is dead.");
           }

       } while(sockIsAlive);
		Automobile automobile=(Automobile)defaultSocketClient.getResult();
		ArrayList<OptionSet> opsetList=new ArrayList<OptionSet>();
		opsetList=automobile.getopsetList();
		for(int i=0;i<opsetList.size();i++){
				  System.out.println("opsetName:"+opsetList.get(i).getOptionSetName());
				  System.out.println("optionName:"+values[i+1]);
				  automobile.setOptionChoice(opsetList.get(i).getOptionSetName(), values[i+1]);
		}
		automobile.getTotalPrice();
       	
		 List<String[]> list = new ArrayList<String[]> ();
		 String[] strings={automobile.getModelName(),"base price", String.valueOf(automobile.getBasePrice())};
	     list.add(strings);
	     for(int i=0;i<opsetList.size();i++){
	    	 String[] buff={opsetList.get(i).getOptionSetName(),values[i+1],String.valueOf(automobile.getOptionPrice(opsetList.get(i).getOptionSetName(),values[i+1]))};
	         list.add(buff);
	     }
	      String[] totalPrice={"Total Cost"," ",String.valueOf(automobile.getTotalPrice())};
	      list.add(totalPrice);
	      StringBuilder sb=new StringBuilder();
	      //send the list to jsp page and display it to customers
	      for(String[] row: list){
	            sb.append("<tr><td>"+ row[0]+"</td><td>"+ row[1]+"</td> <td>"+ row[2]+"</td></tr>");
	        }
	        request.setAttribute("list", sb.toString());
	        try {
				request.getRequestDispatcher("/selectedChoices.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	   
	}

}
