package assignment1task2;
/**
 * author: Yi Cao
 * andrew id: yc2
 * email: yc2@andrew.cmu.edu
 */
public class PoliceOfficer{
    String name;
    int badge;
    
    public PoliceOfficer() {
  		this.name = name;
  		this.badge = badge;
  	}
    public PoliceOfficer(String name, int badge) {
		super();
		this.name = name;
		this.badge = badge;
	}
    
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getBadge() {
		return badge;
	}
	public void setBadge(int badge) {
		this.badge = badge;
	}

	//To examine a parked car and parking meter object and determine whether the car's time has expired,issue a parking ticket if the car time has expired.
//    ParkedCar car=new ParkedCar();
//    ParkingMeter pm=new ParkingMeter();
	boolean issueTicket(int time,int purtime){  
		if(time>purtime)
    	{
			return true;
    	}
		else{
			return false;
		}
    }
	public String toString() {
		return "PoliceOfficer [name=" + name + ", badge=" + badge + "]";
	}


}

