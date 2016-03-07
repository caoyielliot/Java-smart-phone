package Math;

import People.Student;

public interface Counting {
	public static int[] compare(Student[] a){
		 
    int[] compare=new int[5];
	int[][] scores=new int[a.length][];
	  for(int i=0;i<a.length;i++){
			  scores[i]=a[i].getScores();
	     }
	  for(int j=0;j<scores[j].length;j++){
	    	  compare[j]=scores[0][j];
	    }
	return compare;
	 }
}
