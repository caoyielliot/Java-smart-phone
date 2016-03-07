package ReadFile;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import CustomException.TooManyStudentsException;
import People.Student;

public class Util {
	
 public static Student[] readFile(String studentinfo,Student[] stu,int number) throws TooManyStudentsException{
	 
	 FileInputStream fis=null;
	 InputStreamReader isr=null;
	 BufferedReader br=null;
	try {
	
        int i=0;
		fis = new FileInputStream(studentinfo);
		isr=new InputStreamReader(fis);
	    br=new BufferedReader(isr);  
		String len=null;
		len=br.readLine();
		while((len=br.readLine())!=null){
		String[] buff=len.split(" ");
	    int[] score=new int[buff.length-1];
		for(int j=0;j<buff.length-1;j++){
		score[j]=Integer.parseInt(buff[j+1]);      
				}
	
		stu[i]=new Student(Integer.parseInt(buff[0]),score);
	    i++;	 
	      if(i==40){
	    	  break;
	      }
		}
	 if(number>41){
    	 throw new TooManyStudentsException("the number of students is greater than 40!");
			
     }
		
	} catch(TooManyStudentsException e){
		System.out.println(e);

	}catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		try {
			br.close();
			isr.close();
			fis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		return  stu;
	
		
	
	 
 }
}
