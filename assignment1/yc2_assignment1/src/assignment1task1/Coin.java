package assignment1task1;
/**
 * author: Yi Cao;
 * andrewID: yc2
 * email: yc2@andrew.cmu.edu
 */
import java.util.Random;

public class Coin {
 String sideUp;

public Coin() {
	super();
	Random ran=new Random();
	int index=ran.nextInt(2);
	if(index==1){
		 sideUp="heads";
	}else{
		sideUp="tails";
	}
	this.sideUp = sideUp;
}

public String getSideUp() {
	return sideUp;
}
 
public void toss(){
	//randomly determine the side of the coin
	Random ran=new Random();
	int index=ran.nextInt(2);
	if(index==1){
		 sideUp="heads";
	}else{
		sideUp="tails";
	}
}
 

}
