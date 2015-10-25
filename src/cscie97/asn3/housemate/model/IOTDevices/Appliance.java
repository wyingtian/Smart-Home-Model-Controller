package cscie97.asn3.housemate.model.IOTDevices;

import java.util.Scanner;

import cscie97.asn3.housemate.model.Room;

/**
 * abstract class for devices
 * 
 * @author ying
 *
 */
public abstract class Appliance extends IOTDevice {
	Scanner scan;
	   // A set contains the type string of appliance

	public Appliance(String name, String type, Room location) {
		super(name, type, location);
		scan = new Scanner(System.in);
	}
	/**
	 * show the basic appliance info
	 * 
	 * @return
	 */
	public abstract String showInfo();

	/**
	 * show all the status
	 */
	public abstract void showAllStatus();

	/**
	 * enter the configuration mode, using prompt to guide the user input
	 */
	public abstract void configMode();

	/**
	 * change status based on inpu status and the value
	 * 
	 * @param status
	 * @param value
	 */
	public abstract void changeStatus(String status, String value);

	/**
	 * show a particular status
	 * 
	 * @param status
	 */
	public abstract void showStatus(String status);
	/**
	 * set the inital status
	 */
	public abstract void setDefault();
}
