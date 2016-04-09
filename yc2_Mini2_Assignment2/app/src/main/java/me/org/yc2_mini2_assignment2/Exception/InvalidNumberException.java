package me.org.yc2_mini2_assignment2.Exception;

/**
 * Created by caoyi on 16/3/20.
 */
public class InvalidNumberException extends Exception {
       String message;
       public InvalidNumberException(String message){
           this.message=message;

       }

       public String getmessage(){
           return  message;
       }
}
