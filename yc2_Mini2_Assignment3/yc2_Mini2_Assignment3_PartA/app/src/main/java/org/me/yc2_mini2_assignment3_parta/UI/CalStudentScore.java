package org.me.yc2_mini2_assignment3_parta.UI;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.me.yc2_mini2_assignment3_parta.Math.Statistics;
import org.me.yc2_mini2_assignment3_parta.Model.Student;
import org.me.yc2_mini2_assignment3_parta.R;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by caoyi on 16/3/22.
 * This fragment is used to display the calculate Button
 */
public class CalStudentScore extends Fragment {
    private OnItemSelectedListener listener;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.cal_student_score,container,false);
        Button button=(Button)view.findViewById(R.id.cal_button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                updateDetail("fake");
            }
        });
        return view;
    }
    public interface  OnItemSelectedListener{
        public void onRssItemSelected(Fragment fragment);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnItemSelectedListener) {
            listener = (OnItemSelectedListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement MyListFragment.OnItemSelectedListener");
        }

    }



    //trigger update of the details fragment
    public void updateDetail(String uri){
        // create fake data
       DisplayCalScore displayCalScore=new DisplayCalScore();
        // send data to activity
        listener.onRssItemSelected(displayCalScore);
    }

}
