package cscie97.asn3.housemate.controller.exception;

public class CommandFormatException extends Exception{
	
	public CommandFormatException(String message,int LineNum) {
		super(message);
	}	
}
