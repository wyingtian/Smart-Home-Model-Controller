package cscie97.asn2.housemate.model.IOTDevices;

import cscie97.asn2.housemate.model.Room;
/**
 * Door class 
 * @author ying
 */
public class Door extends Appliance{
	private String state;
	public Door(String name, String type, Room location) {
		super(name, type, location);
	}
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String showInfo() {
		return "The name of the " + type + " is " + name + ", It is in Room "
				+ location.roomInfo();
	}
	@Override
	public void showAllStatus() {
		System.out.print("The door: ");
		System.out.println(name);
		System.out.print("Door is now: ");
		System.out.println(getState());
		
		
	}
	@Override
	public void configMode() {
		
	}
	@Override
	public void changeStatus(String status, String value) {
		if(value.equals("on")){
			setState("ON");
		}else if(value.equals("off")){
			setState("OFF");
		}else if(value.equals("locked")){
			setState("LOCKED");
		}else{
			System.out.println("Invalid door state");
		}
		
	}
	@Override
	public void showStatus(String status) {
		System.out.println("The door is now" +getState());
		
	}
	@Override
	public void setDefault() {
		setState("OFF");
	}

}
