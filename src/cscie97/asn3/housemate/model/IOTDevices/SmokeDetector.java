package cscie97.asn3.housemate.model.IOTDevices;

import cscie97.asn3.housemate.model.Room;

/**
 * smoke detector class
 * @author ying
 *
 */
public class SmokeDetector extends Sensor {
	private final String statusName = "mode";
	private String value;

	public SmokeDetector(String name, String type, Room location) {
		super(name, type, location);

	}

	public String getValue() {
		return value;
	}

	//
	public void setDefault() {
		this.value = "OK";
		System.out.println("the smoke detector has been set OK");
	}

	// when the smoke detector triggered;
	public void setStatus(String statusName,String value) {
		if (value.equals("OK")) {
			this.value = value;
			System.out.println("the smoke detector has been set OK");
		} else if (value.equals("FIRE")) {
			this.value = value;
		} else{
			System.out.println("invalid smoke detector input,can only be OK or FIRE");
		}
	}

	public String showInfo() {
		return "The name of the " + type + "is " + name + ", It is in Room "
				+ location.roomInfo();
	}

	// show the status of the smoke detector;
	public String showStatus() {
		return "The status of the smoke detector is " + value;
	}

}
