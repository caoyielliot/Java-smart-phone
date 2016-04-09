package org.me.yc2_mini2_assignment3_parta.Exception;

import org.me.yc2_mini2_assignment3_parta.Model.Student;

import java.util.ArrayList;

/**
 * Created by caoyi on 16/3/30.
 */
public class fixException {
    public ArrayList<Student> fix(ArrayList<Student> arrayList){
        ArrayList<Student> res= new ArrayList<Student>(arrayList.subList(0,40));
        return res;
    }
}
