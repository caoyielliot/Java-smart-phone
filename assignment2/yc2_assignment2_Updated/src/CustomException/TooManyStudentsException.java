package CustomException;

public class TooManyStudentsException extends Exception {
 public TooManyStudentsException(){
	 super();
 }
 public TooManyStudentsException(String message){
	 super(message);
 }
}
