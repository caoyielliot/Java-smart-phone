package org.me.yc2_mini2_assignment4.Exception;

/**
 * Created by caoyi on 16/4/6.
 */
public class InvalidInputException extends  Exception {
    String message;
    public InvalidInputException(String message){
        this.message=message;

    }

    public String getmessage()
    {
        return  message;
    }
}

