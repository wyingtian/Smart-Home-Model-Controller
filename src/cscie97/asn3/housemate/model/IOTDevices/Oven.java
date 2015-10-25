package cscie97.asn3.housemate.model.IOTDevices;

import cscie97.asn3.housemate.model.Room;

/**
 * oven class
 * @author ying
 *
 */
public class Oven extends Appliance {
	private String power;
	private String temperature;
	private String timeToCook;

	public Oven(String name, String type, Room location) {
		super(name, type, location);

	}

	public String getPower() {
		return power;
	}

	public void setPower(String power) {
		this.power = power;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getTimeToCook() {
		return timeToCook;
	}

	public void setTimeToCook(String timeToCook) {
		this.timeToCook = timeToCook;
	}

	@Override
	public String showInfo() {
		return "The name of the " + type + " is " + name + ", It is in Room "
				+ location.roomInfo();
	}

	@Override
	public void showAllStatus() {
		System.out.print("The oven: ");
		System.out.println(name);
		System.out.println("Status Power: ");
		System.out.println(getPower());
		System.out.println("Status temperature: ");
		System.out.println(getTemperature());
		System.out.println("Status TimeToCook: ");
		System.out.println(getTimeToCook());

	}
	
	@Override
	public void configMode() {
		// TODO Auto-generated method stub

	}

	@Override
	public void changeStatus(String status, String value) {
		switch (status){
		case "power":  setPower(value);break;
		case "temperature": setTemperature(value);break;
		case "timetocook":setTimeToCook(value);break;
		default:System.out.println("Wrong status input");break;
		}

	}

	@Override
	public void showStatus(String status) {
		switch (status){
		case "power":  System.out.println("The power status of the oven is now "+getPower());break;
		case "temperature": System.out.println("The temperature of the oven is now "+getTemperature());;break;
		case "timetocook":System.out.println("Time to cook of the oven is now "+getTimeToCook());;break;
		default:System.out.println("Wrong status input");break;
		}
		
	}

	@Override
	public void setDefault() {
		setTemperature("0");
		setPower("OFF");
		setTimeToCook("-1");
		
	}

}
