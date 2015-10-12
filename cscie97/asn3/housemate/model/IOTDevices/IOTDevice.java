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

	public IOTDevice(String name, String type, Room location) {
		this.name = name;
		this.type = type;
		this.location = location;
	}
}
