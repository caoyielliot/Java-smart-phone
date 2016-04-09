package org.me.yc2_mini2_assignment3_parta.UI;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ContentValues;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.me.yc2_mini2_assignment3_parta.Model.Student;
import org.me.yc2_mini2_assignment3_parta.R;
import org.me.yc2_mini2_assignment3_parta.Util.DBIO;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class CalScoreActivity extends AppCompatActivity implements CalStudentScore.OnItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cal_score);
        FragmentManager fragmentManager = getFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        DisplayStudentScore display_student_score = new DisplayStudentScore();
       // DisplayCalScore dis_cal_score = new DisplayCalScore();
        CalStudentScore cal_student_score=new CalStudentScore();
        fragmentTransaction.replace(R.id.button1, cal_student_score);
      //  fragmentTransaction.replace(R.id.calscore, dis_cal_score);
        fragmentTransaction.replace(R.id.displayscore, display_student_score);
        fragmentTransaction.commit();
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
        //the arraylist is all the student info then here we should save them to database
        //save all data into databse
        DBIO db=new DBIO(this, "Student.db", null, 1);
        SQLiteDatabase database=db.getWritableDatabase();
        for(int i=0;i<arrayList.size();i++){
            db.setStudent(arrayList.get(i));
            ContentValues values=db.insertValues();
            database.insert("Student", null, values );
        }
    }



    @Override
    public void onRssItemSelected(Fragment fragment) {
        FragmentManager fragmentManager=getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.button1,fragment).commit();

    }
}
