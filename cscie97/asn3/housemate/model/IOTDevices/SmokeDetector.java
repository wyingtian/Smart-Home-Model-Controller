package cscie97.asn3.housemate.model.IOTDevices;

import cscie97.asn3.housemate.model.Room;
/**
 * smoke detector class
 * @author ying
 *
 */
public class SmokeDetector extends Sensor {
	private String status;

	public SmokeDetector(String name, String type, Room location) {
		super(name, type, location);

	}

	//
	public void setDefault() {
		this.status = "OK";
		System.out.println("the smoke detector has been set OK");
	}

	// when the smoke detector triggered;
	public void setStatus(String state) {
		if (state.equals("OK")) {
			this.status = state;
			System.out.println("the smoke detector has been set OK");
		} else if (state.equals("FIRE")) {
			this.status = state;
			 evacProce();
		} else{
			System.out.println("invalid smoke detector input,can only be OK or FIRE");
		}
	}
	// the evacualtion procedure
	public void evacProce(){
		System.out.println("FIRE!! Starting evaculate procedure");
	}
	// This is to show the name type and location for the smoke detector
	public String showInfo() {
		return "The name of the " + type + "is " + name + ", It is in Room "
				+ location.roomInfo();
	}

	// show the status of the smokie detector;
	public String showStatus() {
		return "The status of the smoke detector is " + status;
	}

}
