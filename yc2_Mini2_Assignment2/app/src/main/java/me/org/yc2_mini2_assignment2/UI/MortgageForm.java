package me.org.yc2_mini2_assignment2.UI;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.Serializable;
import java.sql.SQLData;

import me.org.yc2_mini2_assignment2.Exception.InvalidNumberException;
import me.org.yc2_mini2_assignment2.Exception.fixException;
import me.org.yc2_mini2_assignment2.Model.Mortgage;
import me.org.yc2_mini2_assignment2.R;
import me.org.yc2_mini2_assignment2.Util.DB;

public class MortgageForm extends AppCompatActivity {
    private Mortgage mortgage;

    public  static final String EXTRA_MORTGAGE="me.org.yc2_mini2_assignment2.Mortgage";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mortgage_form);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        //handle the customer Exception
        Button fab = (Button) findViewById(R.id.calculate);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //find by id
                int purchasePrice;
                int mortgageTerm;
                double interestRate;
                EditText editPurchasePrice = (EditText)findViewById(R.id.purchasePrice);
                EditText editMortgageTerm = (EditText)findViewById(R.id.mortgageTerm);
                EditText editInterestRate = (EditText)findViewById(R.id.interestRate);
                Spinner spinnerYear = (Spinner)findViewById(R.id.yearSpinner);
                Spinner spinnerMonth = (Spinner)findViewById(R.id.monthSpinner);
                FileOutputStream fos=null ;
                try {
                    fos = getBaseContext().openFileOutput("log.txt", Context.MODE_APPEND);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                try{
                    if(editPurchasePrice.getText().toString().equals("")){
                        throw new InvalidNumberException("You did not input any value! Now we use default value '0'");
                    }else{
                        purchasePrice = Integer.parseInt(editPurchasePrice.getText().toString());
                    }
                }catch(InvalidNumberException ex){
                    try {


                        Log.d("Exception:",ex.getmessage());
                        fos.write(ex.getmessage().getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Log.e("InvalidNumberException:",ex.toString());
                    fixException fix=new fixException();
                    purchasePrice=fix.fixInvalidNumberException1();
                }

                try{
                    if(editMortgageTerm.getText().toString().equals("")){
                        throw new InvalidNumberException("You did not input any value! Now we use default value '0'");
                    }else{
                        mortgageTerm = Integer.parseInt(editMortgageTerm.getText().toString());
                    }
                }catch (InvalidNumberException ex){
                    try {
                        fos.write(ex.getmessage().getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Log.e("InvalidNumberException:",ex.toString());
                        fixException fix=new fixException();
                        mortgageTerm=fix.fixInvalidNumberException1();
                }

                try{
                    if(editInterestRate.getText().toString().equals("")){
                        throw new InvalidNumberException("You did not input any value! Now we use default value '0'");
                    }else{
                        interestRate = Double.parseDouble(editInterestRate.getText().toString());
                    }

                }catch (InvalidNumberException ex){
                    try {
                        fos.write(ex.getmessage().getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Log.e("InvalidNumberException:",ex.toString());
                    fixException fix=new fixException();
                    interestRate=fix.fixInvalidNumberException2();
                }
               // Log.d("Info::::", fos.getFilesDir().toString());
                int firstPaymentYear = Integer.parseInt(spinnerYear.getSelectedItem().toString());
                int firstPaymentMonth = Integer.parseInt(spinnerMonth.getSelectedItem().toString());
                // modelize those inputs
                Log.d("purchaseprice",String.valueOf(purchasePrice));
                mortgage=new Mortgage(purchasePrice,  mortgageTerm,  interestRate,  firstPaymentYear,  firstPaymentMonth);
                //insert this model to db
                DB db=new DB(MortgageForm.this, "Mortgage.db", null, 2);
                db.setMortgage(mortgage);
                ContentValues values=db.insertValues();
                //Log.d("values",values.get("purchasePrice"));
                SQLiteDatabase database=db.getWritableDatabase();
                database.insert("Mortgage",null,values);
                //bridge the class with DisplayMortgage.class
                Intent intent=new Intent(MortgageForm.this,DisplayMortgage.class);
                intent.putExtra(EXTRA_MORTGAGE, mortgage);
                startActivity(intent);
            }
        });

    }


}
