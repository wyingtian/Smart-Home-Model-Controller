package cscie97.asn2.housemate.model;

import cscie97.asn2.housemate.model.IOTDevices.Appliance;
import cscie97.asn2.housemate.model.IOTDevices.Sensor;

public interface ServiceInterface {

	/**
	 * This method create a house object
	 * add added the house object to HouseMateModel allHouseMap.
	 * @param tokens the String[] is the tokenized command
	 * @param auth_token for access control  
	 */
	public abstract void defineHouse(String[] tokens, String auth_token);

	/**
	 * Create room object
	 * @param tokens
	 * @param auth_token
	 */
	public abstract void defineRoom(String[] tokens, String auth_token);

	/**
	 * create occupant object
	* @param tokens the String[] is the tokenized command
	 * @param auth_token for access control 
	 */
	public abstract void defineOccupant(String[] tokens, String auth_token);
	/**
	 * add occupant to house
	 * @param tokens the String[] is the tokenized command
	 * @param auth_token for access control 
	 */
	public abstract void addOccupant2House(String[] tokens, String auth_token);

	/**
	 * create sensor object
	 * Note: sensor is an abstract class, it create its subclass based on input
	 * @param tokens the String[] is the tokenized command
	 * @param auth_token for access control 
	 */
	public abstract void defineSensor(String[] tokens, String auth_token);
	
	/**
	 *create appliance object
	 * Note: appliance is an abstract class, it create its subclass based on input
	 * @param tokens the String[] is the tokenized command
	 * @param auth_token for access control
	 */
	public abstract void defineAppliance(String[] tokens, String auth_token);

	/**
	 * set the status of the sensor or appliance 
	 * @param tokens the String[] is the tokenized command
	 * @param auth_token for access control
	 */
	public abstract void setSenOrApp(String[] tokens, String auth_token);

	/**
	 * show the status of the sensor or appliance 
	 * @param tokens the String[] is the tokenized command
	 * @param auth_token for access control
	 */
	public abstract void showSenOrApp(String[] tokens, String auth_token);
	/**
	 * show all the configuration of the house
	 * including all the rooms and devices and their status
	 * @param tokens
	 * @param auth_token
	 */
	public abstract void showConfigHouse(String[] tokens, String auth_token);
	/**
	 * show all the configuration of the room
	 * including all the  devices and their status
	 * @param tokens
	 * @param auth_token
	 */
	public abstract void showConfigRoom(String[] tokens, String auth_token);
	/**
	 * show all the configuration of all the houses
	 * including all the rooms and devices and their status
	 * @param tokens
	 * @param auth_token
	 */

	public abstract void showConfigAllHouse(String auth_token);

	/**
	 * find a house based on name
	 * @param house
	 * @return
	 */
	public abstract House findHouse(String house,String auth_token);

	/**
	 * find the room based on the location
	 * <houseName>:<roomName> 
	 * @param input
	 * @return
	 */
	public abstract Room findRoom(String input,String auth_token);

	/**
	 * find the sensor based on the location
	 * @param input
	 * @return
	 */
	public abstract Sensor findSensor(String input,String auth_token);

	/**
	 * find the sensor based on the location
	 * @param input
	 * @return
	 */
	public abstract Appliance findAppliance(String input,String auth_toekn);

}