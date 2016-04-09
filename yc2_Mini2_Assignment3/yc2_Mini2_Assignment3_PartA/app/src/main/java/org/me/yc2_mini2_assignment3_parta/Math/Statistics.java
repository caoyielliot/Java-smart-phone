package org.me.yc2_mini2_assignment3_parta.Math;

import org.me.yc2_mini2_assignment3_parta.Model.Student;

import java.util.ArrayList;

/**
 * Created by caoyi on 16/3/23.
 */
public class Statistics {
    int[] lowscores=new int[5];
    int[] highscores=new int[5];
    float[] avgscores=new float[5];
    findLow low=new findLow();
    findHigh high=new findHigh();
  public int[] findlow(ArrayList<Student> arrayList){
      lowscores=low.compare(arrayList);
      return lowscores;
  }

  public int[] findhigh(ArrayList<Student> arrayList){
      highscores=high.compare(arrayList);
      return  highscores;
  }


  public  float[] findavg(ArrayList<Student> arrayList){
      int[][] scores=new int[arrayList.size()][];
      for(int i=0;i<arrayList.size();i++){
          scores[i]=arrayList.get(i).getScores();
      }

      for(int i=0;i<scores[0].length;i++){
          int sum=0;
          for(int j=0;j<arrayList.size();j++){
              sum+=scores[j][i];
          }
          avgscores[i]=sum/arrayList.size();
      }
      return  avgscores;
  }
}
