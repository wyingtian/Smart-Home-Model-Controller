package cscie97.asn3.housemate.model.IOTDevices;

import cscie97.asn3.housemate.model.Room;
/**
 * abstract class for sensor and appliance
 * @author ying
 *
 */
public abstract class IOTDevice {
	protected String name;
	protected String type;
	protected Room location;

	/**
	 *
	 * @return a location string in the format of house:room:applianceName;
	 */
	public String getLocationPair(){
		return location.getLocation().getName() + ":"+location.getName();
	}
	public IOTDevice(String name, String type, Room location) {
		this.name = name;
		this.type = type;
		this.location = location;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Room getLocation() {
		return location;
	}

	public void setLocation(Room location) {
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
