package org.me.yc2_mini2_assignment3_parta.UI;


import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;

import android.os.Bundle;
import android.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.me.yc2_mini2_assignment3_parta.Exception.TooManyStudentsException;
import org.me.yc2_mini2_assignment3_parta.Exception.fixException;
import org.me.yc2_mini2_assignment3_parta.Math.Statistics;
import org.me.yc2_mini2_assignment3_parta.Model.Student;
import org.me.yc2_mini2_assignment3_parta.R;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class DisplayCalScore extends Fragment {

    // listener is the activity itself



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //this fragment is used to calculate the highscore lowscore and average score of students
        //first get the calculated scores

        // ...
        // Define the listener of the interface type


        View view=inflater.inflate(R.layout.display_cal_score,container,false);

        Statistics statistics=new Statistics();

        AssetManager manager=getResources().getAssets();
        InputStream isr=null;
        BufferedReader br=null;
        ArrayList<Student> arrayList=new ArrayList<Student>();
        FileOutputStream fos=null ;
        try {
            fos = getContext().openFileOutput("log.txt", Context.MODE_APPEND);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
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


                if(arrayList.size()>40){
                    throw new TooManyStudentsException("Too many students!");
                }
            }

        }catch (Exception e){
            try {
                fos.write(e.toString().getBytes());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            fixException fixex=new fixException();
            arrayList=fixex.fix(arrayList);
            e.printStackTrace();
        }

        //here is call statistic to calculate scores
        int[] lowscore=statistics.findlow(arrayList);
        int[] highscore=statistics.findhigh(arrayList);
        float[] avgscore=statistics.findavg(arrayList);


        //here to display the result to activity
        TableLayout stk = (TableLayout) view.findViewById(R.id.table_vice);
        TableRow tbrow1 = new TableRow(this.getContext());
        TextView t0v = new TextView(this.getContext());
        t0v.setText("low");
        t0v.setTextColor(Color.BLACK);
        t0v.setGravity(Gravity.LEFT);
        tbrow1.addView(t0v);

        TextView t1v = new TextView(this.getContext());
        t1v.setText("" + lowscore[0]);
        t1v.setTextColor(Color.BLACK);
        t1v.setGravity(Gravity.LEFT);
        tbrow1.addView(t1v);

        TextView t2v = new TextView(this.getContext());
        t2v.setText(""+lowscore[1]);
        t2v.setTextColor(Color.BLACK);
        t2v.setGravity(Gravity.LEFT);
        tbrow1.addView(t2v);

        TextView t3v = new TextView(this.getContext());
        t3v.setText(""+lowscore[2]);
        t3v.setTextColor(Color.BLACK);
        t3v.setGravity(Gravity.LEFT);
        tbrow1.addView(t3v);

        TextView t4v = new TextView(this.getContext());
        t4v.setText(""+lowscore[3]);
        t4v.setTextColor(Color.BLACK);
        t4v.setGravity(Gravity.LEFT);
        tbrow1.addView(t4v);

        TextView t5v = new TextView(this.getContext());
        t5v.setText(""+lowscore[4]);
        t5v.setTextColor(Color.BLACK);
        t5v.setGravity(Gravity.LEFT);
        tbrow1.addView(t5v);

        TableRow tbrow2 = new TableRow(this.getContext());
        TextView t20v = new TextView(this.getContext());
        t20v.setText("high");
        t20v.setTextColor(Color.BLACK);
        t20v.setGravity(Gravity.LEFT);
        tbrow2.addView(t20v);

        TextView t21v = new TextView(this.getContext());
        t21v.setText("" + highscore[0]);
        t21v.setTextColor(Color.BLACK);
        t21v.setGravity(Gravity.LEFT);
        tbrow2.addView(t21v);

        TextView t22v = new TextView(this.getContext());
        t22v.setText(""+highscore[1]);
        t22v.setTextColor(Color.BLACK);
        t22v.setGravity(Gravity.LEFT);
        tbrow2.addView(t22v);

        TextView t23v = new TextView(this.getContext());
        t23v.setText(""+highscore[2]);
        t23v.setTextColor(Color.BLACK);
        t23v.setGravity(Gravity.LEFT);
        tbrow2.addView(t23v);

        TextView t24v = new TextView(this.getContext());
        t24v.setText(""+highscore[3]);
        t24v.setTextColor(Color.BLACK);
        t24v.setGravity(Gravity.LEFT);
        tbrow2.addView(t24v);

        TextView t25v = new TextView(this.getContext());
        t25v.setText(""+highscore[4]);
        t25v.setTextColor(Color.BLACK);
        t25v.setGravity(Gravity.LEFT);
        tbrow2.addView(t25v);


        TableRow tbrow3 = new TableRow(this.getContext());
        TextView t30v = new TextView(this.getContext());
        t30v.setText("avg");
        t30v.setTextColor(Color.BLACK);
        t30v.setGravity(Gravity.LEFT);
        tbrow3.addView(t30v);

        TextView t31v = new TextView(this.getContext());
        t31v.setText("" + avgscore[0]);
        t31v.setTextColor(Color.BLACK);
        t31v.setGravity(Gravity.LEFT);
        tbrow3.addView(t31v);

        TextView t32v = new TextView(this.getContext());
        t32v.setText(""+avgscore[1]);
        t32v.setTextColor(Color.BLACK);
        t32v.setGravity(Gravity.LEFT);
        tbrow3.addView(t32v);

        TextView t33v = new TextView(this.getContext());
        t33v.setText(""+avgscore[2]);
        t33v.setTextColor(Color.BLACK);
        t33v.setGravity(Gravity.LEFT);
        tbrow3.addView(t33v);

        TextView t34v = new TextView(this.getContext());
        t34v.setText(""+avgscore[3]);
        t34v.setTextColor(Color.BLACK);
        t34v.setGravity(Gravity.LEFT);
        tbrow3.addView(t34v);

        TextView t35v = new TextView(this.getContext());
        t35v.setText(""+avgscore[4]);
        t35v.setTextColor(Color.BLACK);
        t35v.setGravity(Gravity.LEFT);
        tbrow3.addView(t35v);

        stk.addView(tbrow1);
        stk.addView(tbrow2);
        stk.addView(tbrow3);


        return view;
    }


}
