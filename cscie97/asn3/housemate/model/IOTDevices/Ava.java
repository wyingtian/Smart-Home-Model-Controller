package cscie97.asn3.housemate.model.IOTDevices;

import cscie97.asn3.housemate.model.Room;

public class Ava extends Sensor{
	private String status = "listening";
	public Ava(String name, String type, Room location) {
		super(name, type, location);
	}
	@Override
	public String showInfo(){
		return "The name of the "+ type + "is "+name +", It is in Room "+ location.roomInfo();
	}

	@Override
	public String showStatus() {
		
		return "Ava is listening for command";
	}
}
