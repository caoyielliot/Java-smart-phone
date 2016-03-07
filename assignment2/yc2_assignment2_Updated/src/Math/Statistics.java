package Math;

import People.Student;

public class Statistics {
	
//count[0]=new findLow();
//count[1]=new findHigh();
	
	
findLow low=new findLow();	
findHigh high=new findHigh();

int[] lowscores=new int[5];
int[] highscores=new int[5];
float[] avgscores=new float[5];

public void findlow(Student[] a){
//	//find the lowest scores
//  int[][] scores=new int[a.length][];
//  for(int i=0;i<a.length;i++){
//		  scores[i]=a[i].getScores();
//  }
//    for(int j=0;j<scores[j].length;j++){
//    	lowscores[j]=scores[0][j];
//    }
//    for(int i=0;i<scores[0].length;i++){
//    	for(int j=0;j<a.length;j++){
//    		if(lowscores[i]>scores[j][i]){
//    			lowscores[i]=scores[j][i];
//    		}
//    	}
//    }
//  
	lowscores=low.compare(a);
 System.out.print("low"+"\t");
  for(int m=0;m<lowscores.length;m++){
	  System.out.print(lowscores[m]+"\t");
  }
  System.out.println();
}

public void findhigh(Student[] a){
	
	
//	//find the highest scores
//  int[][] scores=new int[a.length][];
//  for(int i=0;i<a.length;i++){
//		  scores[i]=a[i].getScores();
//  }
//    for(int j=0;j<scores[j].length;j++){
//    	highscores[j]=scores[0][j];
//    }
//    for(int i=0;i<scores[0].length;i++){
//    	for(int j=0;j<a.length;j++){
//    		if(highscores[i]<scores[j][i]){
//    			highscores[i]=scores[j][i];
//    		}
//    	}
//    }
	highscores=high.compare(a);
    System.out.print("high"+"\t");
  for(int m=0;m<highscores.length;m++){
	  System.out.print(highscores[m]+"\t");
  }
  System.out.println();
 
}


public void findavg(Student[] a){
 int[][] scores=new int[a.length][];
 for(int i=0;i<a.length;i++){
	  scores[i]=a[i].getScores();
}

 for(int i=0;i<scores[0].length;i++){
	 int sum=0;
 	for(int j=0;j<a.length;j++){
 		sum+=scores[j][i];
 	}
 	 avgscores[i]=sum/a.length;
 }
System.out.print("avg"+"\t");
 for(int i=0;i<avgscores.length;i++){
	  System.out.print(avgscores[i]+"\t");
}
 System.out.println();
}


}
