package cscie97.asn3.housemate.controller.exception;

public class SensorNotFoundException extends Exception {

	public SensorNotFoundException() {
		super();	
	}
	public SensorNotFoundException(String message) {
		super(message);
		System.out.println(message);
	}
	
}
