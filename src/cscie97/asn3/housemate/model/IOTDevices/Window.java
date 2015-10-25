package cscie97.asn3.housemate.model.IOTDevices;

import cscie97.asn3.housemate.model.Room;

/**
 * window class
 * @author ying
 *
 */
public class Window extends Appliance {
	private String state;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Window(String name, String type, Room location) {
		super(name, type, location);

	}

	@Override
	public String showInfo() {
		return "The name of the " + type + " is " + name + ", It is in Room "
				+ location.roomInfo();
	}

	@Override
	public void showAllStatus() {
		System.out.print("The light: ");
		System.out.println(name);
		System.out.print("Window is now: ");
		System.out.println(getState());

	}

	@Override
	public void configMode() {
		// TODO Auto-generated method stub

	}

	@Override
	public void changeStatus(String status, String value) {
		if (value.equals("open")) {
			setState("open");
		} else if (value.equals("close")) {
			setState("close");
		} else {
			System.out.println("Invalid window state");
		}
	}

	@Override
	public void showStatus(String status) {
		System.out.println("The window is now " +getState());

	}

	@Override
	public void setDefault() {
		setState("OFF");

	}

}
