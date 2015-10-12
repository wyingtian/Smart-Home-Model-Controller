package cscie97.asn3.housemate.model.IOTDevices;

import cscie97.asn3.housemate.model.Room;

/**
 * light class
 * 
 * @author ying
 */
public class Light extends Appliance {
	private String Power;
	private int intensity;

	public String getPower() {
		return Power;
	}

	public void setPower(String power) {
		Power = power;
	}

	public int getIntensity() {
		return intensity;
	}

	public void setIntensity(int intensity) {
		this.intensity = intensity;
	}

	public Light(String name, String type, Room location) {
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
		System.out.println("Light Power: ");
		System.out.println(getPower());
		System.out.println("Light intensity: ");
		System.out.println(getIntensity());
	}

	@Override
	public void configMode() {
		// TODO Auto-generated method stub
	}

	@Override
	public void changeStatus(String status, String value) {
		switch (status) {
		case "power":
			setPower(value);
			break;
		case "intensity":
			if (value.equals("dimmer")) {
				setIntensity(getIntensity() - 10);
			} else if (value.equals("brighter")) {
				setIntensity(getIntensity() + 10);
			} else if (Integer.parseInt(value) < 100
					&& Integer.parseInt(value) > 0)
				setIntensity(Integer.parseInt(value));
			break;
		default:
			System.out.println("Wrong light status input");
			break;
		}

	}
	
	@Override
	public void showStatus(String status) {
		switch (status) {
		case "power":
			System.out.println("The power status of the light is now "
					+ getPower());
			break;
		case "intensity":
			System.out.println("The intensity of the light is now "
					+ getIntensity());
			;
			break;
		default:
			System.out.println("Wrong light status input");
			break;
		}
	}

	@Override
	public void setDefault() {
		setIntensity(0);
		setPower("OFF");

	}

}
