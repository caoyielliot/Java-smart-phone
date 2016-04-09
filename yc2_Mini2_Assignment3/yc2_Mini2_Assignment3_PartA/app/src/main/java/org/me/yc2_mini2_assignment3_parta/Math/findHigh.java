package org.me.yc2_mini2_assignment3_parta.Math;

import android.content.Context;

import org.me.yc2_mini2_assignment3_parta.Model.Student;

import java.util.ArrayList;

/**
 * Created by caoyi on 16/3/23.
 */
public class findHigh implements Counting {
    public int[] compare(ArrayList<Student> arrayList){
        int[] compare=new int[5];
        int[][] scores=new int[arrayList.size()][];
        for(int i=0;i<arrayList.size();i++){
            scores[i]=arrayList.get(i).getScores();
        }
        for(int j=0;j<scores[j].length;j++){
            compare[j]=scores[0][j];
        }
        for(int i=0;i<scores[0].length;i++){
            for(int j=0;j<arrayList.size();j++){
                if(compare[i]<scores[j][i]){
                    compare[i]=scores[j][i];
                }
            }
        }
        return compare;
    }
}
