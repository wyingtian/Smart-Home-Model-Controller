package cscie97.asn3.housemate.model.IOTDevices;

import cscie97.asn3.housemate.component.Room;

public class Camera extends Sensor {
	private String status = "Occupant Leaving";

	public Camera(String name, String type, Room location) {
		super(name, type, location);

	}

	@Override
	public String getValue() {
		return null;
	}

	@Override
	public void setStatus(String statusName, String value) {

	}

	// if the person is in the room ,status is "Occupant detected"
	// set the status of the Camera
	public void setStatus(boolean input) {
		if (input) {
			status = "Occupant Detected";
		} else {
			status = "Occupant Leaving";
		}
	}

	// show the status of the Camera
	public String showStatus() {
		return status;
	}

	// show the name type and location of the camera
	public String showInfo() {
		return "The name of the " + type + "is " + name + ", It is in Room "
				+ location.roomInfo();
	}
}
