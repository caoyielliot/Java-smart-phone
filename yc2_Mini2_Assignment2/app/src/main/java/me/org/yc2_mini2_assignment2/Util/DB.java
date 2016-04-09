package me.org.yc2_mini2_assignment2.Util;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import me.org.yc2_mini2_assignment2.Model.Mortgage;
/**
 * Created by caoyi on 16/3/14.
 * This class is used to create tables saving the users' inputs
 */
public class DB extends SQLiteOpenHelper{

    private static  final String createMortgage = "create table Mortgage ("
            + "id integer primary key autoincrement, "
            + "purchasePrice integer, "
            + "mortgageTerm integer, "
            + "interestRate real, "
            + "firstPaymentDate text,"
            +  "monthlyPayment real, "
            + "totalPayment real, "
            + "payoffDate text)";

    private Mortgage mortgage;
    public void setMortgage(Mortgage mortgage) {
        this.mortgage = mortgage;
    }
    public DB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        Log.d("Database Opreations","Create Database!");
    }

    public ContentValues insertValues(){
        //like a hashmap to encapsulate data
        ContentValues values = new ContentValues();
        Log.d("purchaseprice", String.valueOf(mortgage.getPurchasePrice()));
        Log.d("contentvalues","contentvalues created!");
        values.put("purchasePrice",mortgage.getPurchasePrice());
        values.put("mortgageTerm", mortgage.getMortgageTerm());
        values.put("interestRate",mortgage.getInterestRate());
        values.put("firstPaymentDate",mortgage.getFirstPayDate());
        values.put("monthlyPayment", mortgage.getMonthlyPayment());
        values.put("totalPayment",mortgage.getTotalPayment());
        values.put("payoffDate", mortgage.getPayoffDate());
        return values;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       //this method is used to create the database
        db.execSQL(createMortgage);
        Log.d("Database Operations","Create Tables!");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Mortgage");
        onCreate(db);
    }
}
