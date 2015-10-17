package cscie97.asn3.housemate.model;
import cscie97.asn1.knowledge.engine.Importer;
import cscie97.asn1.knowledge.engine.KnowledgeGraph;
import cscie97.asn1.knowledge.engine.QueryEngine;
import cscie97.asn3.housemate.controller.command.*;
import cscie97.asn3.housemate.model.IOTDevices.Appliance;
import cscie97.asn3.housemate.model.IOTDevices.Ava;
import cscie97.asn3.housemate.model.IOTDevices.Camera;
import cscie97.asn3.housemate.model.IOTDevices.Door;
import cscie97.asn3.housemate.model.IOTDevices.Light;
import cscie97.asn3.housemate.model.IOTDevices.Oven;
import cscie97.asn3.housemate.model.IOTDevices.Pandora;
import cscie97.asn3.housemate.model.IOTDevices.Refrigerator;
import cscie97.asn3.housemate.model.IOTDevices.Sensor;
import cscie97.asn3.housemate.model.IOTDevices.SmokeDetector;
import cscie97.asn3.housemate.model.IOTDevices.TV;
import cscie97.asn3.housemate.model.IOTDevices.Thermostat;
import cscie97.asn3.housemate.model.IOTDevices.Window;
import cscie97.asn3.housemate.model.Occupants.Adult;
import cscie97.asn3.housemate.model.Occupants.Child;
import cscie97.asn3.housemate.model.Occupants.Occupant;
import cscie97.asn3.housemate.model.Occupants.Pet;
import cscie97.asn3.housemate.model.Occupants.Unknown;
import cscie97.asn3.housemate.model.exception.AppNotFoundException;
import cscie97.asn3.housemate.model.exception.HouseNotFoundException;
import cscie97.asn3.housemate.model.exception.RoomNotFoundException;
import cscie97.asn3.housemate.model.exception.SensorNotFoundException;

import java.util.*;


/**
 * This class is a singleton class perform define, show, set, add command 
 * for the objects of the house.
 * @author ying
 *
 */
public class HouseMateModel implements ServiceInterface {

	private static final ServiceInterface MODEL = new HouseMateModel();
	KnowledgeGraph knowledgeGraph = KnowledgeGraph.getInstance();
	Importer importer = new Importer();
	QueryEngine queryEngine = new QueryEngine();


	HashMap<String, House> homeMap ;
	HashMap<String, Occupant> allOccupantMap;
	public HashMap<String, House> getHomeMap() {
		return homeMap;
	}

	public HashMap<String, Occupant> getAllOccupantMap() {
		return allOccupantMap;
	}

	public  KnowledgeGraph getKnowledgeGraph(){
		return this.knowledgeGraph;
	}
	public  Importer getImporter(){
		return this.importer;
	}
	public  QueryEngine getQueryEngine(){
		return this.queryEngine;
	}
	private HouseMateModel() {
		homeMap = new HashMap<String, House>();
		allOccupantMap = new HashMap<String, Occupant>();
	}

	public static ServiceInterface getInstance() {
		return MODEL;
	}

	public static boolean isApplianceType(String type){
		HashSet<String> ApplianceTypeSet = new HashSet<String>();
		ApplianceTypeSet.add("door");
		ApplianceTypeSet.add("light");
		ApplianceTypeSet.add("oven");
		ApplianceTypeSet.add("pandora");
		ApplianceTypeSet.add("refrigerator");
		ApplianceTypeSet.add("thermostat");
		ApplianceTypeSet.add("tv");
		ApplianceTypeSet.add("Window");
		return ApplianceTypeSet.contains(type);
	}
	/**
	 * This method create a house object
	 * add added the house object to HouseMateModel allHouseMap.
	 * @param tokens the String[] is the tokenized command
	 * @param auth_token for access control  
	 */
	@Override
	public void defineHouse(String houseName, String auth_token ) {
		// check the format of the input, the length has to be 3;


			if (homeMap.containsKey(houseName)) {
				// if the name already exist
				System.out.println(houseName + " this Name exists ");
			} else {
				// create the object and then put it in the house map
				House house1 = new House(houseName);
				homeMap.put(houseName, house1);
				System.out.println("The house defined! House name is " + "\""
						+ houseName + "\"");
			}
	}

	/**
	 * Create room object
	 * @param tokens
	 * @param auth_token
	 */
	@Override
	public void defineRoom(String roomName, String floor,  String type, String houseName, String auth_token) {
		// check if the the format is right


			// try to find the house it belongs to
			if (!homeMap.containsKey(houseName)){
				System.err
						.println("The House have not been created");
			} else {
				House roomIn;
				roomIn = homeMap.get(houseName); // find the house it is in;
				// check if the room name already exist
				if (roomIn.roomMap.containsKey(roomName)) {
					System.err.println(roomName + " name already exist!");
				}
				// if name not exist create new object.
				else {
					Room room = new Room(roomName, floor, type,
							roomIn);
					// add the room to the house map
					roomIn.roomMap.put(roomName, room);
					System.out.println("The Room has been created! "
							+ room.roomInfo());
				}
			}
	}
	/**
	 * create occupant object
	* @param tokens the String[] is the tokenized command
	 * @param auth_token for access control 
	 */

	@Override
	public void defineOccupant(String occuName,String occuType, String auth_token) {


			if (allOccupantMap.containsKey(occuName)) {
				// if the name already exist
				System.err
						.println(occuName
								+ "this Name exists, If there are people with same name, Please differentiate them ");
			} else {
				// create the object and then put it in the house map
				Occupant occupant;
				switch (occuType) {
				case "adult":

					occupant = new Adult(occuName, occuType);
					allOccupantMap.put(occuName, occupant);
					System.out.println("Name: " + occuName + "  Type: "
							+ occuType + " defined!");
					break;
				case "child":
					occupant = new Child(occuName, occuType);
					allOccupantMap.put(occuName, occupant);
					System.out.println("Name: " + occuName + "  Type: "
							+ occuType + " defined!");
					break;
				case "pet":
					occupant = new Pet(occuName, occuType);
					allOccupantMap.put(occuName, occupant);
					System.out.println("Name: " + occuName + "  Type: "
							+ occuType + " defined!");
					break;
				case "unknown":

					occupant = new Unknown(occuName, occuType);
					allOccupantMap.put(occuName, occupant);
					System.out.println("Name: " + occuName + "  Type: "
							+ occuType + " defined!");
					break;
				default:
					System.err.println("Unknow type of occupant!");
				}

			}

	}

	/**
	 * add occupant to house
	 * @param tokens the String[] is the tokenized command
	 * @param auth_token for access control 
	 */

	@Override
	public void addOccupant2House(String occName,String houseName ,String auth_token) {
		// if the person is not defined yet
		if (!allOccupantMap.containsKey(occName)) {
			System.err.println("Can't find " + occName);
		}
		// if the room is not defined yet
		if (!homeMap.containsKey(houseName)) {
			System.err.println("Can't find " + houseName);
		}
		// if the house and person are both defined
		if (allOccupantMap.containsKey(occName)
				&& homeMap.containsKey(houseName)) {
			Occupant theOccup;
			House theHouse;
			theOccup = allOccupantMap.get(occName);
			theHouse = homeMap.get(houseName);
			theOccup.addHouse(theHouse);
			theHouse.addOccupant(theOccup);
			System.out.println("Occupant " + occName + " is added to " + " House "
					+ houseName);
		}
	}

	/**
	 * find a house based on name
	 * @param house
	 * @return
	 */
	@Override
	public House findHouse(String house, String auth_token) {
		try {
			if (homeMap.keySet().contains(house)) {
				return homeMap.get(house);
			} else {
				throw new HouseNotFoundException(house);
			}
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * find the room based on the location
	 * <houseName>:<roomName>
	 * @param input
	 * @return
	 */
	@Override
	public Room findRoom(String input, String auth_token) {
		House house;

		String[] loca = input.split(":");

		try {
			if (loca.length == 2) {
				house = findHouse(loca[0], auth_token);
				if (house == null) {
					throw new RoomNotFoundException("The Room --" + loca[1]
							+ "-- is not found because the House--" + loca[0]
							+ " --is not Found");
				} else if (!house.hasRoom(loca[1])) {
					throw new RoomNotFoundException("The house " + loca[0]
							+ " exist, but the Room-- " + loca[1]
							+ "-- is not Found" ,house );
				} else {
					return house.getRoom(loca[1]);
				}
			} else
				throw new RoomNotFoundException(
						"the findRoom input has wrong format");
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * find the sensor based on the location
	 * @param input
	 * @return
	 */
	@Override
	public Sensor findSensor(String input,String auth_token) {
		String[] loca = input.split(":");
		String[] locaNSen = new String[2];
		Room sensorInRoom;

		try {
			if (loca.length != 3) {
				throw new SensorNotFoundException(
						"the findSensor input has wrong format: " + input);
			} else {
				locaNSen[0] = loca[0] + ":" + loca[1];
				sensorInRoom = findRoom(locaNSen[0],auth_token);
				if (sensorInRoom == null) {
					throw new SensorNotFoundException(
							"the sensor not found because the location of the sensor --"
									+ locaNSen[0] + "--is not found");
				} else if (!sensorInRoom.hasSensor(loca[2])) {
					throw new SensorNotFoundException("the location--"
							+ locaNSen[0] + " exsists but the Sensor--"
							+ loca[2] + " is not found");
				} else {
					return sensorInRoom.getSensor(loca[2]);
				}
			}
		} catch (SensorNotFoundException e) {
			return null;
		}
	}

	/**
	 * find the sensor based on the location
	 * @param input
	 * @return
	 */
	@Override
	public Appliance findAppliance(String input, String auth_token) {
		String[] loca = input.split(":");
		String[] locaNSen = new String[2];
		Room AppInRoom;

		try {
			if (loca.length != 3) {
				throw new AppNotFoundException(
						"the findAppliance input has wrong format: " + input);
			} else {
				locaNSen[0] = loca[0] + ":" + loca[1];
				AppInRoom = findRoom(locaNSen[0],auth_token);
				if (AppInRoom == null) {
					throw new AppNotFoundException(
							"the appliance not found because the location of the sensor --"
									+ locaNSen[0] + "--is not found");
				} else if (!AppInRoom.hasAppliance(loca[2])) {
					throw new AppNotFoundException("the location--"
							+ locaNSen[0] + " exists but the appliance--"
							+ loca[2] + " is not found");
				} else {
					return AppInRoom.getAppliance(loca[2]);
				}
			}
		} catch (AppNotFoundException e) {
			return null;
		}
	}
	/**
	 *create appliance object
	 * Note: appliance is an abstract class, it create its subclass based on input
	 * @param tokens the String[] is the tokenized command
	 * @param auth_token for access control
	 */
	@Override
	public void defineSensor(String sensorName,String sensorType,String roomName, String auth_token) {
		// theRoom variable to find the Room that the user give
		Room theRoom;
		Sensor theSensor;


		 if ((theRoom = findRoom(roomName,auth_token)) == null) {

			System.err
					.println("The room"
							+ roomName
							+ " is not found, Please check again or define the room first");
		} else {

			switch (sensorType) {
			case "smoke_detector":
				SmokeDetector theSmDe;
				theSmDe = new SmokeDetector(sensorName,sensorType, theRoom);
				theRoom.sensorMap.put(sensorName, theSmDe);
				System.out.println("the smoke_detector has been defined."
						+ theSmDe.showInfo());
				theSmDe.setDefault();
				break;
			case "camera":
				theSensor = new Camera(sensorName,sensorType, theRoom);
				theRoom.sensorMap.put(sensorName, theSensor);
				System.out.println("the camera has been defined."
						+ theSensor.showInfo());
				break;
			case "Ava":
				theSensor = new Ava(sensorName,sensorType, theRoom);
				theRoom.sensorMap.put(sensorName, theSensor);
				System.out.println("the Ava has been defined."
						+ theSensor.showInfo());
				break;
			default:
				System.out.println("Unknown type of sensor!");
			}

		}

	}

	/**
	 *create appliance object
	 * Note: appliance is an abstract class, it create its subclass based on input
	 * @param tokens the String[] is the tokenized command
	 * @param auth_token for access control
	 */


	@Override
	public void defineAppliance(String appName,String appType,String roomName, String auth_token) {
		// theRoom variable to find the Room that the user give
		Room theRoom;

		if ((theRoom = findRoom(roomName,auth_token)) == null) {

			System.err
					.println("The room"
							+ roomName
							+ " is not found, Please check again or define the room first");
		} else {

			switch (appType) {
			case "TV":
				TV theTV;
				theTV = new TV(appName, appType, theRoom);
				theRoom.applianceMap.put(appName, theTV);
				System.out.println("TV has been defined."
						+ theTV.showInfo());
				theTV.setDefault();
				break;
			case "oven":
				Oven theOven;
				theOven = new Oven(appName, appType, theRoom);
				theRoom.applianceMap.put(appName, theOven);
				System.out.println("Oven has been defined."
						+ theOven.showInfo());
				theOven.setDefault();
				break;
			case "refrigerator":
				Refrigerator theRegrigerator;
				theRegrigerator = new Refrigerator(appName, appType, theRoom);
				theRoom.applianceMap.put(appName, theRegrigerator);
				System.out.println("Regrigerator has been defined."
						+ theRegrigerator.showInfo());
				theRegrigerator.setDefault();
				break;
			case "pandora" :
				Pandora thePandora;
				thePandora = new Pandora(appName, appType, theRoom);
				theRoom.applianceMap.put(appName, thePandora);
				System.out.println("Pandora has been defined."
						+ thePandora.showInfo());
				thePandora.setDefault();
				break;
			case "light":
				Light theLight;
				theLight = new Light(appName, appType, theRoom);
				theRoom.applianceMap.put(appName, theLight);
				System.out.println("Light has been defined."
						+ theLight.showInfo());
				theLight.setDefault();
				break;
			case "door":
				Door theDoor;
				theDoor = new Door(appName, appType, theRoom);
				theRoom.applianceMap.put(appName, theDoor);
				System.out.println("Door has been defined."
						+ theDoor.showInfo());
				theDoor.setDefault();
				break;
			case "window":
				Window theWindow;
				theWindow = new Window(appName, appType, theRoom);
				theRoom.applianceMap.put(appName, theWindow);
				System.out.println("Window has been defined."
						+ theWindow.showInfo());
				theWindow.setDefault();
				break;
			case "thermostat":
				Thermostat theThermostat;
				theThermostat = new Thermostat(appName, appType, theRoom);
				theRoom.applianceMap.put(appName, theThermostat);
				System.out.println("Thermostat has been defined."
						+ theThermostat.showInfo());
				theThermostat.setDefault();
				break;
					
			default:
				System.err.println("Unknown type of appliance!");
			}
		}
	}

	/**
	 * show the status of the sensor or appliance 
	 * @param tokens the String[] is the tokenized command
	 * @param auth_token for access control
	 */
	public void showSensor(String sensorName, String auth_token) {
			Sensor theSensor;
			theSensor = findSensor(sensorName,auth_token);
			if (theSensor != null) {
				System.out.println(theSensor.showStatus());
			}
	}
	public void showApplianceStatus(String appName,String statusName,String auth_token){
		Appliance theApp;
		theApp = findAppliance(appName,auth_token);
		if (theApp != null  ) {
			theApp.showStatus(statusName);
		}
	}
	public void showAllApplianceStatus(String appName,String auth_token){
		Appliance theApp;
		theApp = findAppliance(appName,auth_token);
		if (theApp != null  ) {
			theApp.showAllStatus();
		}
	}
	/**
	 * show all the configuration of the house
	 * including all the rooms and devices and their status
	 * @param tokens
	 * @param auth_token
	 */

	@Override
	public void showConfigHouse(String houseName, String auth_token) {
		House theHouse = findHouse(houseName,auth_token);
		if (theHouse != null) {
			theHouse.showOccupInHouse();
			theHouse.showRoomInHouse();
		}
	}

	@Override
	public void setApplianceStatus(String appName, String statusName, String value,String auth_token) {
		Appliance theApp;
		theApp = findAppliance(appName, auth_token);
		if (theApp != null ) {
			theApp.changeStatus(statusName,value);
			theApp.showStatus(statusName);
			// theApp.configMode();
		}else{
			try {
				throw new AppNotFoundException(statusName+ " is not Found" );
			} catch (AppNotFoundException e) {
			}
		}
	}

	/**
	 * show all the configuration of the room
	 * including all the  devices and their status
	 * @param tokens
	 * @param auth_token
	 */
	@Override
	public void showConfigRoom(String roomName, String auth_token) {
		Room theRoom = findRoom(roomName,auth_token);
		if (theRoom != null) {
			System.out.println(theRoom.roomInfo());
			theRoom.showSenInRoom();
			theRoom.showAppInRoom();
		}
	}

	/**
	 * find a house based on name
	 * @param
	 * @return
	 */
	@Override
	public void showConfigAllHouse(String auth_token) {
		for (String house : homeMap.keySet()) {
			homeMap.get(house).showOccupInHouse();
			homeMap.get(house).showRoomInHouse();
		}
	}


	/**
	 *
	 * @param location location is in the form of house:room
	 * @param type      type is the appliance type String
	 * @return  a list of matching appliance.
	 */
	public List<Appliance> findApplianceByType(String location, String type, String auth_token){
		List<Appliance> appList = new ArrayList<Appliance>();
		Room theRoom =findRoom(location,auth_token);
		for(Appliance app: theRoom.getApplianceMap().values()){
			if(app.getType().equals(type)){
				appList.add(app);
			}
		}
		return appList;
	}

	public void openDoors(List<Appliance> list){
		if(list.isEmpty()){
			System.out.println("no doors in this room");
			return;
		}
		for(Appliance app: list){
			DoorOpenCommand com = new DoorOpenCommand((Door)app);
			com.execute();
		}
	}
	public void turnOnAllLights(List<Appliance> list){
		if(list.isEmpty()){
			System.out.println("no lights in this room");
			return;
		}
		for(Appliance app: list){
			LightOnCommand com = new LightOnCommand((Light)app);
			com.execute();
		}
	}
	public void turnOffAllLights(List<Appliance> list){
		if(list.isEmpty()){
			System.out.println("no lights in this room");
			return;
		}
		for(Appliance app: list){
			LightOffCommand com = new LightOffCommand((Light)app);
			com.execute();
		}
	}
	public void dimmerAllLights(List<Appliance> list){
		if(list.isEmpty()){
			System.out.println("no lights in this room");
			return;
		}
		for(Appliance app: list){
			LightDimmerCommand com = new LightDimmerCommand((Light)app);
			com.execute();
			app.showStatus("intensity");
		}
	}
	public void coolerThermostat(List<Appliance> list){
		if(list.isEmpty()){
			System.out.println("no thermostat in this room");
			return;
		}
		for(Appliance app: list){
			ThermostatCoolerCommand com = new ThermostatCoolerCommand((Thermostat)app);
			com.execute();
			app.showStatus("temperature");
		}
	}
	public void warmerThermostat(List<Appliance> list){
		if(list.isEmpty()){
			System.out.println("no thermostat in this room");
			return;
		}
		for(Appliance app: list){
			ThermostatWarmerCommand com = new ThermostatWarmerCommand((Thermostat)app);
			com.execute();
			app.showStatus("temperature");
		}
	}
}
