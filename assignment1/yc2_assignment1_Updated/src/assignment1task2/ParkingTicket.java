package assignment1task2;

public class ParkingTicket {
	


   //To report the make, model, color 
	//and license number of an illegally parked car.
    void illegalcar(ParkedCar car){
    		System.out.println(car.toString());
    }
    void policeofficer(PoliceOfficer po){
    	System.out.println(po.toString());
    }
    void fine(int time,int purtime){
		int finetime=(time-purtime)/60+1;
		if(finetime<=0){
			System.out.println("the fine is:"+0.0);;
		}else{
			if(finetime>0&&finetime<=1){
		    	  System.out.println("the fine is:"+25.0);
		    	}else{
		    		System.out.println("the fine is:"+(25.0+(finetime-1)*10.0));
		    	}
		}
    }
}
