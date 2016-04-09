package org.me.yc2_mini2_assignment3_parta.Util;

import android.content.Context;

import org.me.yc2_mini2_assignment3_parta.Exception.TooManyStudentsException;
import org.me.yc2_mini2_assignment3_parta.Model.Student;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by caoyi on 16/3/22.
 */
public class FileIO {
    //this class is used to readFile from asserts
    public static Student[] readFile(String studentinfo,Student[] stu,int number,String error) throws TooManyStudentsException{

        FileInputStream fis=null;
        InputStreamReader isr=null;
        BufferedReader br=null;
        try {

            int i=0;
            fis = new FileInputStream(studentinfo);
            isr=new InputStreamReader(fis);
            br=new BufferedReader(isr);
            String len=null;
            len=br.readLine();
            while((len=br.readLine())!=null){
                String[] buff=len.split(" ");
                int[] score=new int[buff.length-1];
                for(int j=0;j<buff.length-1;j++){
                    score[j]=Integer.parseInt(buff[j+1]);
                }
                stu[i]=new Student(Integer.parseInt(buff[0]),score);
                i++;
                if(i==40){
                    break;
                }
            }
            if(number>41){
                throw new TooManyStudentsException("the number of students is greater than 40!");

            }

        } catch(TooManyStudentsException e){
            error=e.getmessage();

        }catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            try {
                br.close();
                isr.close();
                fis.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        return  stu;

    }



}
