package moonrise.util;

public enum Colors {
	
	black(4210752);
	
	int code;
	
	private Colors(int num) {
		this.code = num;
	}
	
	public int code() {
		return this.code;
	}

}
