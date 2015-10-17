package cscie97.asn3.housemate.model;

import cscie97.asn1.knowledge.engine.Importer;
import cscie97.asn1.knowledge.engine.KnowledgeGraph;
import cscie97.asn1.knowledge.engine.QueryEngine;
import cscie97.asn3.housemate.model.IOTDevices.Appliance;
import cscie97.asn3.housemate.model.IOTDevices.Sensor;
import cscie97.asn3.housemate.model.Occupants.Occupant;

import java.util.HashMap;
import java.util.List;

public interface ServiceInterface {
	public HashMap<String, Occupant> getAllOccupantMap();
	public HashMap<String, House> getHomeMap();

	/**
	 * This method create a house object
	 * add added the house object to HouseMateModel allHouseMap.
	 * @param tokens the String[] is the tokenized command
	 * @param auth_token for access control  
	 */
	public Importer getImporter();
	public QueryEngine getQueryEngine();
	public KnowledgeGraph getKnowledgeGraph();
	public abstract void defineHouse(String houseName, String auth_token);

	/**
	 * Create room object
	 * @param tokens
	 * @param auth_token
	 */
	public abstract void defineRoom(String roomName, String floor,  String type, String houseName, String auth_token);

	/**
	 * create occupant object
	* @param tokens the String[] is the tokenized command
	 * @param auth_token for access control 
	 */
	public abstract void defineOccupant(String occuName,String occuType, String auth_token);
	/**
	 * add occupant to house
	 * @param tokens the String[] is the tokenized command
	 * @param auth_token for access control 
	 */
	public abstract void addOccupant2House(String occName,String houseName, String auth_token);

	/**
	 * create sensor object
	 * Note: sensor is an abstract class, it create its subclass based on input,
	 * roomName is in the form of house:room1;
	 * @param tokens the String[] is the tokenized command
	 * @param auth_token for access control 
	 */
	public abstract void defineSensor(String sensorName,String sensorType,String roomName, String auth_token);
	
	/**
	 *create appliance object
	 * Note: appliance is an abstract class, it create its subclass based on input
	 * @param tokens the String[] is the tokenized command
	 * @param auth_token for access control
	 */
	public abstract void defineAppliance(String appName,String appType,String roomName, String auth_token);

//	/**
//	 * set the status of the sensor or appliance
//	 * @param tokens the String[] is the tokenized command
//	 * @param auth_token for access control
//	 */
//	public abstract void setSenOrApp(String[] tokens, String auth_token);

	/**
	 * show the status of the sensor or appliance 
	 * @param tokens the String[] is the tokenized command
	 * @param auth_token for access control
	 */
	public abstract void showSensor(String sensorName, String auth_token);
	/**
	 * show all the configuration of the house
	 * including all the rooms and devices and their status
	 * @param tokens
	 * @param auth_token
	 */

	public abstract void showApplianceStatus(String appName,String statusName,String auth_token);
	public abstract void showAllApplianceStatus(String appName,String auth_token);
	public abstract void showConfigHouse(String houseName, String auth_token);
	public abstract void setApplianceStatus(String appName,String statusName,String value,String auth_token);
	/**
	 * show all the configuration of the room
	 * including all the  devices and their status
	 * @param tokens
	 * @param auth_token
	 */
	public abstract void showConfigRoom(String roomName, String auth_token);
	/**
	 * show all the configuration of all the houses
	 * including all the rooms and devices and their status
	 * @param tokens
	 * @param auth_token
	 */

	public abstract void showConfigAllHouse(String auth_token);

	/**
	 * find a house based on name
	 * @param houseName
	 * @return
	 */
	public abstract House findHouse(String houseName,String auth_token);

	/**
	 * find the room based on the location
	 * <houseName>:<roomName> 
	 * @param roomName
	 * @return
	 */
	public abstract Room findRoom(String roomName,String auth_token);

	/**
	 * find the sensor based on the location
	 * @param sensorName in the form of<houseName>:<roomName>:<sensorName>
	 * @return
	 */
	public abstract Sensor findSensor(String sensorName,String auth_token);

	/**
	 * find the sensor based on the location
	 * @param applianceName in the form of <houseName>:<roomName>:<applianceName>
	 * @return
	 */
	public abstract Appliance findAppliance(String applianceName,String auth_toekn);
	public abstract List<Appliance> findApplianceByType(String location, String type, String auth_token);
	public abstract void openDoors(List<Appliance> list);
	public abstract void turnOnAllLights(List<Appliance> list);
	public abstract void turnOffAllLights(List<Appliance> list);
	public abstract void dimmerAllLights(List<Appliance> list);
	public abstract void coolerThermostat(List<Appliance> list);
	public abstract void warmerThermostat(List<Appliance> list);
}