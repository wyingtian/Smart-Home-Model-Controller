package cscie97.asn3.housemate.model.exception;

public class HouseNotFoundException extends Exception {

	public HouseNotFoundException() {
		super();
		
	}
	public HouseNotFoundException(String houseSearched) {
		super(houseSearched);
		System.err.println("The house "+ houseSearched +" is not found!");
	}

	
}
