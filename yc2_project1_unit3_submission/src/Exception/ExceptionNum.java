package Exception;

public enum ExceptionNum{
	//enum the different exceptions
	MissingBasePrice(1),MissingfileName(2),MissingOpsetData(3),MissingOpsetName(4),MissingOptionName(5),MissingOptionPrice(6),WrongfileName(7),CarModelNotFound(9);
	
	
	
	private int num;
	
	// Constructor with parameter
	private ExceptionNum(int num){
		this.num = num;
	}
	

	public int getNum() {
		return num;
	}

	
	public void setNum(int num) {
		this.num = num;
	}
	
}