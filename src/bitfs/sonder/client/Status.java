package bitfs.sonder.client;

public enum Status {
	
	LEECHING(0),
	SEEDING(1),
	PAUSED(2),
	HALTED(3),
	UNKNOWN(99),
	INIT(-1);
	
	int code;
	
	private Status(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}

}
