package cscie97.asn3.housemate.model;

import java.util.HashMap;

import cscie97.asn3.housemate.model.IOTDevices.Appliance;
import cscie97.asn3.housemate.model.IOTDevices.Sensor;

/**
 * This is the room class
 * it has all the info of the devices.
 * @author ying
 *
 */
public class Room  {
	private String type;
	private String floor;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public House getLocation() {
		return location;
	}

	private String name;
	private House location;
	HashMap<String,Sensor> sensorMap ;
	HashMap<String,Appliance> applianceMap;
	// constructor of the room
	public Room(String name, String floor, String type,House location) {
		this.name = name;
		this.floor = floor;
		this.type = type;
		this.location = location;
		sensorMap = new HashMap<String,Sensor>();
		applianceMap = new HashMap<String,Appliance>();
	}

	public HashMap<String, Appliance> getApplianceMap() {
		return applianceMap;
	}

	public HashMap<String, Sensor> getSensorMap() {
		return sensorMap;
	}

	/**
	 * check if the sensor exsist in the room
	 * @param  sensor
	 * @return if the room has the sensor
	 */
	public boolean hasSensor(String sensor){
		return sensorMap.containsKey(sensor);
	}
	/**
	 * get the sensor if it exist
	 * @param sensor
	 * @return the sensor object
	 */
	public Sensor getSensor(String sensor){
		if(hasSensor(sensor)){
			return sensorMap.get(sensor);
		}else 
			return null;
	}
	/**
	 * check if the appliance exsist in the room
	 * @param
	 * @return boolean if the room has the appliance
	 */
	public boolean hasAppliance(String app){
		return applianceMap.containsKey(app);
	}
	/**
	 * get the appliance  if it exsist
	 * @param  app
	 * @return the appliance object
	 */
	public Appliance getAppliance(String app){
		if(hasAppliance(app)){
			return applianceMap.get(app);
		}else 
			return null;
	}
	/**
	 * show room information
	 * @return the string of room info
	 */
	
	public String roomInfo(){
		return "Name is "+ name +"; floor is "+floor+"; type is " +type+"; In "+location.getName();
	}
	
	public String toString(){
		return location.getName()+":"+name;
	}
	/**
	 *  show the Sensor that the room has, and their status
	 */
	public void showSenInRoom(){
		System.out.println("***************************************");
		System.out.println("This is the list of the Sensors in Room " + name);
		for(String item : sensorMap.keySet()){
			System.out.println(sensorMap.get(item).showInfo());
			System.out.println(sensorMap.get(item).showStatus());
		}
	}
	
	/**
	 *  show the appliance that the room has, and their status
	 */
		public void showAppInRoom(){
			System.out.println("*****************************************");
			System.out.println("This is the list of the Appliance in Room " + name);
			for(String item : applianceMap.keySet()){
				System.out.println(applianceMap.get(item).showInfo());
				applianceMap.get(item).showAllStatus();
			}
		}
}

