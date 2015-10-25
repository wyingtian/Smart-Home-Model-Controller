package cscie97.asn3.housemate.model;


import cscie97.asn3.knowledge.engine.Importer;
import cscie97.asn3.knowledge.engine.KnowledgeGraph;
import cscie97.asn3.knowledge.engine.QueryEngine;
import cscie97.asn3.housemate.model.IOTDevices.Appliance;
import cscie97.asn3.housemate.model.IOTDevices.Sensor;
import cscie97.asn3.housemate.model.Occupants.Occupant;

import java.util.HashMap;
import java.util.List;

/**
 * This is a service Interface implemented by HouseMateModel to provide methods
 * to make changes and check status.
 */
public interface  ServiceInterface   {


	/**
	 *Set Sensor Status based on sensor name
	 * @param sensorName
	 * @param statusName
	 * @param value
	 * @param tokens
	 * @param authToken
	 */
	public  void setSensor(String sensorName, String statusName, String value, String[] tokens, String authToken);

	/**
	 *This method return the importer for KG inside the HouseMateModel
	 * @return Importer
	 */
	public  Importer getImporter();

	/**
	 *This method return the QueryEngine for KG inside the HouseMateModel
	 * @return QueryEngine
	 */
	public  QueryEngine getQueryEngine();

	/**
	 *This method return the KG inside the HouseMateModel
	 * @return KnowledgeGraph
	 */
	public  KnowledgeGraph getKnowledgeGraph();

	/**
	 * define house method
	 * @param houseName
	 * @param authToken
	 */
	public  void defineHouse(String houseName, String authToken);

	/**
	 *define room method
	 * @param roomName
	 * @param floor
	 * @param type
	 * @param houseName
	 * @param authToken
	 */
	public  void defineRoom(String roomName, String floor,  String type, String houseName, String authToken);

	/**
	 *define occupant method
	 * @param occuName
	 * @param occuType
	 * @param authToken
	 */
	public abstract void defineOccupant(String occuName,String occuType, String authToken);

	/**
	 *add occupant to house
	 * @param occName
	 * @param houseName
	 * @param authToken
	 */
	public abstract void addOccupant2House(String occName,String houseName, String authToken);

	/**
	 * create sensor object
	 * Note: sensor is an abstract class, it create its subclass based on input,
	 * roomName is in the form of house:room1;
	 * @param sensorName
	 * @param sensorType
	 * @param roomName
	 * @param authToken
	 */
	public abstract void defineSensor(String sensorName,String sensorType,String roomName, String authToken);

	/**
	 * create appliance object
	 * Note: appliance is an abstract class, it create its subclass based on input
	 * @param appName
	 * @param appType
	 * @param roomName
	 * @param authToken
	 */
	public abstract void defineAppliance(String appName,String appType,String roomName, String authToken);


	/**
	 *  show the status of the sensor or appliance
	 * @param sensorName
	 * @param authToken
	 */
	public abstract void showSensor(String sensorName, String authToken);

	/**
	 *  show all the configuration of the house
	 * including all the rooms and devices and their status
	 * @param appName
	 * @param statusName
	 * @param authToken
	 */

	public abstract void showApplianceStatus(String appName,String statusName,String authToken);
	public abstract void showAllApplianceStatus(String appName,String authToken);
	public abstract void showConfigHouse(String houseName, String authToken);
	public abstract void setApplianceStatus(String appName,String statusName,String value,String authToken);

	/**
	 *  show all the configuration of the room
	 * including all the  devices and their status
	 * @param roomName
	 * @param authToken
	 */
	public abstract void showConfigRoom(String roomName, String authToken);

	/**
	 *  show all the configuration of all the houses
	 * including all the rooms and devices and their status
	 * @param authToken
	 */

	public abstract void showConfigAllHouse(String authToken);

	/**
	 * find a house based on name
	 * @param houseName
	 * @return
	 */
	public abstract House findHouse(String houseName,String authToken);

	/**
	 * find the room based on the location
	 * <houseName>:<roomName> 
	 * @param roomName
	 * @return
	 */
	public abstract Room findRoom(String roomName,String authToken);

	/**
	 *This method find a type of sensor in a room based on the room name and the type of sensor
	 * @param roomName
	 * @param sensorType
	 * @param authToken
	 * @return
	 */
	public abstract List<Sensor> findSensorInRoom(String roomName, String sensorType, String authToken);

	/**
	 *find the sensor based on the location
	 * @param sensorName
	 * @param authToken
	 * @return
	 */
	public abstract Sensor findSensor(String sensorName,String authToken);

	/**
	 * This method find a type of sensor in a house based on the house name and the type of sensor
	 * @param houseName
	 * @param sensorType
	 * @param authToken
	 * @return
	 */
	public abstract List<Sensor> findSensorInHouse(String houseName, String sensorType, String authToken);
	/**
	 * find the appliance based on the location
	 * @param applianceName in the form of <houseName>:<roomName>:<applianceName>
	 * @return
	 */
	public abstract Appliance findAppliance(String applianceName,String authToken);

	/**
	 * This method find a type of appliance based on the room name and the type of appliance
	 * @param location
	 * @param type
	 * @param authToken
	 * @return
	 */
	public abstract List<Appliance> findApplianceByType(String location, String type, String authToken);
	public abstract HashMap<String, Occupant> getAllOccupantMap();

	/**
	 *This method find a type of appliance based on the house name location and the type of appliance
	 * @param houseName
	 * @param applianceType
	 * @param authToken
	 * @return
	 */
	public abstract List<Appliance> findApplianceInHouse(String houseName, String applianceType, String authToken);

}


