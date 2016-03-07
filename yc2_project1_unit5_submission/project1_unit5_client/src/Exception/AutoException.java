package Exception;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import Adapter.FixAuto;

public class AutoException extends Exception implements FixAuto{
    /**
	 * author: Yi Cao
	 * andrew id : yc2
	 */
	private static final long serialVersionUID = 1L;
	private String err;
    private int no;
    //constructor
    public AutoException(String err, int no){
    	this.err=err;
    	this.no=no;
    }
	
	
    //constructor according the exception
	public AutoException(ExceptionNum exception) {
		// TODO Auto-generated constructor stub
		err=exception.name();
		no=exception.getNum();
	}

	public String getErr() {
		return err;
	}
	public void setErr(String err) {
		this.err = err;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}

	public String toString() {
		return "err: " + err + ", no: " + no ;
	}
	// Time to record the err
	
	//fix the exception and get a time long info 
	public void fix(int errno) {
		Helpers help=new Helpers();
		try{
		switch(errno){
		case 1: help.fixMissingBasePrice();break;
		case 2: help.fixMissingfileName(); break;
		case 3: help.fixMissingOpsetData(); break;
		case 4: help.fixMissingOpsetName(); break;
		case 5: help.fixMissingOptionName();break;
		case 6: help.fixMissingOptionPrice(); break;
		case 7: help.fixWrongfileName(); break;
		case 8: help.fixCarModelNotFound(); break;
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		

	}
		
	
	
	
		
	}
    

