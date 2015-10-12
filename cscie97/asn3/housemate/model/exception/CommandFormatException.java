package cscie97.asn3.housemate.model.exception;

public class CommandFormatException extends Exception{
	
	public CommandFormatException(String message,int LineNum) {
		super(message);
	}	
}
