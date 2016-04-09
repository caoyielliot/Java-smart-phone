package me.org.yc2_mini2_assignment2.UI;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import me.org.yc2_mini2_assignment2.Model.Mortgage;
import me.org.yc2_mini2_assignment2.R;

public class DisplayMortgage extends AppCompatActivity {
    /***
     * This class is used to display the results
     *
     * @param savedInstanceState
     */
    private Mortgage mortgage;
    private TextView textViewMonthlyPayment;
    private TextView textViewTotalPayment;
    private TextView textViewPayoffDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_mortgage);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //get the Intent and display it to the UI
        mortgage = (Mortgage) getIntent().getSerializableExtra(MortgageForm.EXTRA_MORTGAGE);
        textViewMonthlyPayment = (TextView) findViewById(R.id.monthlyPayment);
        textViewTotalPayment = (TextView) findViewById(R.id.totalPayment);
        textViewPayoffDate = (TextView) findViewById(R.id.payoffDate);
        textViewMonthlyPayment.setText(String.valueOf(mortgage.getMonthlyPayment()));
        textViewTotalPayment.setText(String.valueOf(mortgage.getTotalPayment()));
        textViewPayoffDate.setText(String.valueOf(mortgage.getPayoffDate()));

    }


}
