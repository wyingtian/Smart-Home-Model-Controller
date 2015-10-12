package cscie97.asn3.housemate.model.exception;

public class AppNotFoundException extends Exception {

	public AppNotFoundException() {
		super();

	}

	public AppNotFoundException(String message) {
		super(message);
		System.out.println(message);
	}
}
