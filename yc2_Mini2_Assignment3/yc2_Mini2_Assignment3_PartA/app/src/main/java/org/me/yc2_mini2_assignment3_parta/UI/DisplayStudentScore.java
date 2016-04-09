package org.me.yc2_mini2_assignment3_parta.UI;

import android.app.Fragment;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.me.yc2_mini2_assignment3_parta.Exception.TooManyStudentsException;
import org.me.yc2_mini2_assignment3_parta.Model.Student;
import org.me.yc2_mini2_assignment3_parta.R;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by caoyi on 16/3/22.
 */
public class DisplayStudentScore extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.display_student_score,container,false);
        //set the  fisplay_student_score
        //read file and display students score
        AssetManager manager=getResources().getAssets();
        InputStream isr=null;
        BufferedReader br=null;

        ArrayList<Student> arrayList=new ArrayList<Student>();
        try{

            isr=manager.open("studentinfo2.txt");
            br=new BufferedReader(new InputStreamReader(isr,"utf-8"));
            //here use fileIo to read file and return the students sid and scores array
            String len=null;
            len=br.readLine();
            while((len=br.readLine())!=null){
                String[] buff=len.split(" ");
                int[] score=new int[buff.length-1];
                for(int j=0;j<buff.length-1;j++){
                    score[j]=Integer.parseInt(buff[j+1]);
                }

                arrayList.add(new Student(Integer.parseInt(buff[0]),score));


                if(arrayList.size()==40){
                    break;
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        //now input result into the fragment dynamically

        TableLayout stk = (TableLayout) view.findViewById(R.id.table_main);
        TableRow tbrow0 = new TableRow(this.getContext());
        TextView tv0 = new TextView(this.getContext());
        tv0.setText(" SID ");
        tv0.setTextColor(Color.BLACK);
        tbrow0.addView(tv0);
        TextView tv1 = new TextView(this.getContext());
        tv1.setText(" Q1 ");
        tv1.setTextColor(Color.BLACK);
        tbrow0.addView(tv1);
        TextView tv2 = new TextView(this.getContext());
        tv2.setText(" Q2 ");
        tv2.setTextColor(Color.BLACK);
        tbrow0.addView(tv2);
        TextView tv3 = new TextView(this.getContext());
        tv3.setText(" Q3 ");
        tv3.setTextColor(Color.BLACK);
        tbrow0.addView(tv3);
        TextView tv4 = new TextView(this.getContext());
        tv4.setText(" Q4 ");
        tv4.setTextColor(Color.BLACK);
        tbrow0.addView(tv4);
        TextView tv5 = new TextView(this.getContext());
        tv5.setText(" Q5 ");
        tv5.setTextColor(Color.BLACK);
        tbrow0.addView(tv5);
        stk.addView(tbrow0);
        for (int i = 0; i < arrayList.size(); i++) {
            TableRow tbrow = new TableRow(this.getContext());
            TextView t1v = new TextView(this.getContext());
            t1v.setText("" + arrayList.get(i).getSID());
            t1v.setTextColor(Color.BLACK);
            t1v.setGravity(Gravity.LEFT);
            tbrow.addView(t1v);
            TextView t2v = new TextView(this.getContext());
            t2v.setText(""+arrayList.get(i).getScores()[0]);
            t2v.setTextColor(Color.BLACK);
            t2v.setGravity(Gravity.LEFT);
            tbrow.addView(t2v);
            TextView t3v = new TextView(this.getContext());
            t3v.setText("" + arrayList.get(i).getScores()[1]);
            t3v.setTextColor(Color.BLACK);
            t3v.setGravity(Gravity.LEFT);
            tbrow.addView(t3v);
            TextView t4v = new TextView(this.getContext());
            t4v.setText("" + arrayList.get(i).getScores()[2]);
            t4v.setTextColor(Color.BLACK);
            t4v.setGravity(Gravity.LEFT);
            tbrow.addView(t4v);
            TextView t5v = new TextView(this.getContext());
            t5v.setText("" + arrayList.get(i).getScores()[3]);
            t5v.setTextColor(Color.BLACK);
            t5v.setGravity(Gravity.LEFT);
            tbrow.addView(t5v);
            TextView t6v = new TextView(this.getContext());
            t6v.setText("" + arrayList.get(i).getScores()[4]);
            t6v.setTextColor(Color.BLACK);
            t6v.setGravity(Gravity.LEFT);
            tbrow.addView(t6v);
            stk.addView(tbrow);
        }

        return  view;
    }


}
