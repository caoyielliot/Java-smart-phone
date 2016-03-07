package assignment1task2;
/**
 * author: Yi Cao
 * andrew ID: yc2
 * email: yc2@andrew.cmu.edu
 */
public class ParkedCar {
	//To know the car's make, model, color, license number and 
	//number of minutes the car has been parked.
	String make;
	String model;
	String color;
	int license;
	int time;
	public ParkedCar() {
		super();
		this.make = make;
		this.model = model;
		this.color = color;
		this.license = license;
		this.time = time;
	}
	public ParkedCar(String make, String model, String color, int license, int time) {
		super();
		this.make = make;
		this.model = model;
		this.color = color;
		this.license = license;
		this.time = time;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getLicense() {
		return license;
	}
	public void setLicense(int license) {
		this.license = license;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "ParkedCar [make=" + make + ", model=" + model + ", color=" + color + ", license=" + license + ", time="
				+ time + "]";
	}
	
	
}
