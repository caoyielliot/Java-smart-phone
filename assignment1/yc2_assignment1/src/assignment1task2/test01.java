package assignment1task2;

import java.util.Scanner;

public class test01 {
 public static void main(String[]args){
	 ParkedCar car=new ParkedCar();
	 ParkingMeter pm=new ParkingMeter();
	 ParkingTicket pt=new ParkingTicket();
	 PoliceOfficer po=new PoliceOfficer();
	po.setName("wang");
	po.setBadge(1111);

	car.setColor("green");
	car.setLicense(212);
	car.setMake("US");
	car.setModel("small");
	 Scanner scan=new Scanner(System.in);
	 while(true)
	 {
		 System.out.println("pls input the parking time and the purchased time:");
		 car.setTime(scan.nextInt());
		 pm.setPurtime(scan.nextInt());
		 
		 if(po.issueTicket(car.getTime(),pm.getPurtime())){
			 //illegal
			pt.fine(car.getTime(), pm.getPurtime());
		    pt.policeofficer(po);
		    pt.illegalcar(car);
		 }else{
			 System.out.println("the car is not illegal!");
		 }
		 System.out.println("=========================================================");
	 }
	 
	
 }
}
