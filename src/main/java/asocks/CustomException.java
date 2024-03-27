package asocks;


public class CustomException extends RuntimeException{
	
	private String msgString;

	public CustomException(String msgString) {
		super(msgString);
		this.msgString = msgString;
	}
	
	public String getMsgString() {
		return msgString;
	}
}
