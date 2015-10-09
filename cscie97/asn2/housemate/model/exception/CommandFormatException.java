package cscie97.asn2.housemate.model.exception;

public class CommandFormatException extends Exception{
	
	public CommandFormatException(String message,int LineNum) {
		super(message);
	}	
}
