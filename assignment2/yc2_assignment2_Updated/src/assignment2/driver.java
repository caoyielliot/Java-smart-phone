package assignment2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import Math.Statistics;
import People.Student;
import ReadFile.Util;

public class driver {
  @SuppressWarnings("unused")
public static void main(String[]args){
	  //judge the number of students if more than 40 then throw an exception
	  FileInputStream fis=null;
	  InputStreamReader isr=null;
	  BufferedReader br=null;
	  String len=null;
	 
	  
	  String[] url=new String[3];
	  url[0]="studentinfo1.txt";
	  url[1]="studentinfo2.txt";
	  url[2]="studentinfo3.txt";

	  for(int j=0;j<3;j++){
		  int number=0;
		  int count=0;
		  try {
			  fis=new FileInputStream(url[j]);
			  isr=new InputStreamReader(fis);
			  br=new BufferedReader(isr);
			while((len=br.readLine())!=null){
				  count++;
				  number++;
			  }
		
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  System.out.println("the students number is:"+(count-1));
		  if(count>41){
			  count=41;
		  }
		
		  Student[] lab2 = new Student[count-1];
		  
		  try {
			//print the info of students
			lab2=Util.readFile(url[j], lab2,number);
			System.out.println("Stud"+"\t"+"Qu1"+"\t"+"Qu2"+"\t"+"Qu3"+"\t"+"Qu4"+"\t"+"Qu5");
			for(int i=0;i<lab2.length;i++){
				  int sid=lab2[i].getSID();
				  int[] scores=lab2[i].getScores();
				 count++;
				 lab2[i].printstudentlist(sid, scores);
			  }
			  System.out.println();
			  Statistics statlab2=new Statistics();
			  statlab2.findlow(lab2);
			  statlab2.findhigh(lab2);
			  statlab2.findavg(lab2);
			  System.out.println("===================================================");
		} catch (Exception e) {
			e.printStackTrace();
		}
	  }
		  
			
		  try {
				fis.close();
				isr.close();
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
	  }

	  
  }

