package cscie97.asn2.housemate.model.exception;

import cscie97.asn2.housemate.model.House;

public class RoomNotFoundException extends Exception {

	private House theHouse;
	
	public RoomNotFoundException() {
		super();

	}

	public RoomNotFoundException(String s) {
		super(s);
		System.err.println(s);
	}
	
	public RoomNotFoundException(String s,House house) {
		super(s);
		System.err.println(s);
	}
}
