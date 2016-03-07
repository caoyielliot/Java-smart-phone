package scale;

public enum OptionNum {
	//to enumerate all the possible edit options
	updateOptionPrice(1),updateOptionName(2),updateOptionSetName(3);
	private int num;
   
	OptionNum(int num){
		this.num=num;
	}
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
	
}
