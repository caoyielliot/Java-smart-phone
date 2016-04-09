package org.me.yc2_mini2_assignment3_parta.Util;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.me.yc2_mini2_assignment3_parta.Model.Student;

/**
 * Created by caoyi on 16/3/22.
 */
public class DBIO extends SQLiteOpenHelper {

    private static  final String createStudent = "create table Student ("
            + "id integer primary key autoincrement, "
            + "sid integer,"
            + "Q1 integer, "
            + "Q2 integer, "
            + "Q3 integer, "
            + "Q4 integer,"
            + "Q5 integer)";


    private Student student;
    public void setStudent(Student student) {
        this.student= student;
    }
    public DBIO(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        Log.d("Database Opreations", "Create Database!");
    }

    public ContentValues insertValues(){
        //like a hashmap to encapsulate data
        ContentValues values = new ContentValues();

        values.put("sid",student.getSID());
        values.put("Q1", student.getScores()[0]);
        values.put("Q2", student.getScores()[1]);
        values.put("Q3", student.getScores()[2]);
        values.put("Q4", student.getScores()[3]);
        values.put("Q5", student.getScores()[4]);
        return values;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //this method is used to create the database
        db.execSQL(createStudent);
        Log.d("Database Operations","Create Tables!");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Student");
        onCreate(db);
    }
}