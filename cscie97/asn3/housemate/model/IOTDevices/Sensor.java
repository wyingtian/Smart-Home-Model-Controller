package cscie97.asn3.housemate.model.IOTDevices;

import cscie97.asn3.housemate.component.Room;

/**
 * abstract class for different types of sensors
 * 
 * @author ying
 *
 */
public abstract class Sensor extends IOTDevice {

	public Sensor(String name, String type, Room location) {
		super(name, type, location);

	}
	public abstract void setStatus(String statusName, String value);
	/**
	 * show the basic sensor info
	 * 
	 * @return
	 */
	public abstract String getValue();
	public abstract String showInfo();

	/**
	 * show the status of the sensor
	 * 
	 * @return
	 */
	public abstract String showStatus();

}
