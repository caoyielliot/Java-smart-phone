package Math;

import People.Student;

public class findHigh implements Counting{
	public int[] compare(Student[] a){
		   int[] compare=new int[5];
			int[][] scores=new int[a.length][];
			  for(int i=0;i<a.length;i++){
					  scores[i]=a[i].getScores();
			     }
			  for(int j=0;j<scores[j].length;j++){
			    	  compare[j]=scores[0][j];
			    }
		   for(int i=0;i<scores[0].length;i++){
		    	for(int j=0;j<a.length;j++){
		    		if(compare[i]<scores[j][i]){
		    			compare[i]=scores[j][i];
		    		}
		    	}
		    }
		return compare;
	   }
}
