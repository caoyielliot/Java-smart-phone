package People;

public class Student extends People{
   

public Student(int sID, int[] scores) {
		super(sID, scores);
		// TODO Auto-generated constructor stub
	}

//print the values of instance variables
public void printstudentlist(int sid,int[] scores){
try {
	//System.setOut(new PrintStream(new FileOutputStream("studentinfo.txt")));
	//System.out.println("Stud"+" "+"Qu1"+" "+"Qu2"+" "+"Qu3"+" "+"Qu4"+" "+"Qu5");
	if(scores.length!=0){
		System.out.print(sid+"\t");
		  for(int i=0;i<scores.length;i++){
			  System.out.print(scores[i]+"\t");
		  }
		  System.out.println();
	}
	
	  
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
  
}

}
