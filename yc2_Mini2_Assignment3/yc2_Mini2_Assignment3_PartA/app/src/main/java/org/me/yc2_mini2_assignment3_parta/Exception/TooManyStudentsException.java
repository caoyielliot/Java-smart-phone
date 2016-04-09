package org.me.yc2_mini2_assignment3_parta.Exception;

/**
 * Created by caoyi on 16/3/22.
 */
public class TooManyStudentsException extends Exception {
    String message;
    public TooManyStudentsException(){
        super();
    }
    public TooManyStudentsException(String message){
        this.message=message;
    }
    public String getmessage(){
        return message;
    }
}