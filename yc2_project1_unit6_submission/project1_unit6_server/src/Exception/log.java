package Exception;

import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;

public class log{
	public String  log(int errno){
		
		String time=null;
		try{

		RandomAccessFile raf=new RandomAccessFile("log.txt","rw");
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		time=sdf.format(date);

		raf.write((time+"  Err Messge:").getBytes());
		switch(errno){
//		case 1: help.fixMissingBasePrice();break;
//		case 2: help.fixMissingfileName(); break;
//		case 3: help.fixMissingOpsetData(); break;
//		case 4: help.fixMissingOpsetName(); break;
//		case 5: help.fixMissingOptionName();break;
//		case 6: help.fixMissingOptionPrice(); break;
//		case 7: help.fixWrongfileName(); break;
//		case 8: help.fixCarModelNotFound(); break;
		case 1:raf.write("MissingBasePrice".getBytes());break;
		case 2: raf.write("MissingFileName".getBytes());break;
		case 3: raf.write("MissingOptionsetdata".getBytes()); break;
		case 4: raf.write("MissingOptionSetname".getBytes()); break;
		case 5: raf.write("MissingOptionname".getBytes()); break;
		case 6: raf.write("MissingOptionPrice".getBytes());break;
		case 7: raf.write("WrongFileName".getBytes()); break;
		case 8: raf.write("CarNotFound".getBytes()); break;
		
		}
		
		
		
		
		
		raf.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return time;
	}
  
	
}
