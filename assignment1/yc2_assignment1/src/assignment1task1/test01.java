package assignment1task1;
/**
 * author: Yi Cao
 * andrew Id: yc2
 * email: yc2@andrew.cmu.edu
 */
public class test01 {
 public static void main(String[]args){
	 Coin coin=new Coin();
	 int countheads,counttails;
	  countheads=0;counttails=0;
	  System.out.println("the initial face is:"+coin.getSideUp());
	 System.out.println("===========================");
	 for(int i=0;i<20;i++){
		 coin.toss();
		 if(coin.getSideUp().equals("heads")){
			 countheads++;
		 }else{
			 counttails++;
		 }
		 System.out.println("the facing up side is:"+coin.getSideUp());
	 }
	 System.out.println("the times of heads is:"+countheads+";the times of tails is:"+counttails);
 }
}
