package cscie97.asn3.housemate.model;
import cscie97.asn3.housemate.controller.HouseMateControllerFactory;
import cscie97.asn3.knowledge.engine.Importer;
import cscie97.asn3.knowledge.engine.KnowledgeGraph;
import cscie97.asn3.knowledge.engine.QueryEngine;
import cscie97.asn3.housemate.model.IOTDevices.Appliance;
import cscie97.asn3.housemate.model.IOTDevices.Thermostat;
import cscie97.asn3.housemate.model.Occupants.Pet;
import cscie97.asn3.housemate.model.Occupants.Occupant;
import cscie97.asn3.housemate.model.Occupants.Unknown;
import cscie97.asn3.housemate.controller.exception.AppNotFoundException;
import cscie97.asn3.housemate.controller.exception.HouseNotFoundException;
import cscie97.asn3.housemate.controller.exception.RoomNotFoundException;
import cscie97.asn3.housemate.controller.exception.SensorNotFoundException;
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
import cscie97.asn3.housemate.model.IOTDevices.Window;
import cscie97.asn3.housemate.model.Occupants.Adult;
import cscie97.asn3.housemate.model.Occupants.Child;

import java.util.*;

/**
 * This class is a singleton class perform define, show, set, add command 
 * for the objects of the house.
 * @author ying
 *
 */
public class HouseMateModel  implements ServiceInterface {

	private static final HouseMateModel MODEL = new HouseMateModel();
	KnowledgeGraph knowledgeGraph = KnowledgeGraph.getInstance();
	Importer importer = new Importer();
	QueryEngine queryEngine = new QueryEngine();

	HashMap<String, House> AllHouseMap;
	HashMap<String, Occupant> allOccupantMap;

	/**
	 * constructor it creates two map for house and occupants
	 */
	private HouseMateModel() {
		AllHouseMap = new HashMap<String, House>();
		allOccupantMap = new HashMap<String, Occupant>();
	}

	public HashMap<String, House> getHomeMap() {
		return AllHouseMap;
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

	/**
	 * The singleton to return the only instance
	 * @return the only instance of HouseMateModel is self
	 */
	public static HouseMateModel getInstance() {
		return MODEL;
	}

	/**
	 * check if a string is a appliance type
	 * @param type
	 * @return
	 */
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
	 *  This method create a house object
	 * add added the house object to HouseMateModel allHouseMap.
	 * @param houseName
	 * @param authToken
	 */
	@Override
	public void defineHouse(String houseName, String authToken) {
		// check the format of the input, the length has to be 3;
			if (AllHouseMap.containsKey(houseName)) {
				// if the name already exist
				System.out.println(houseName + " this Name exists ");
			} else {
				// create the object and then put it in the house map
				House house1 = new House(houseName);
				AllHouseMap.put(houseName, house1);
				System.out.println("The house defined! House name is " + "\""
						+ houseName + "\"");
			}
	}

	/**
	 *  Create room object
	 * @param roomName
	 * @param floor
	 * @param type
	 * @param houseName
	 * @param authToken
	 */
	@Override
	public void defineRoom(String roomName, String floor,  String type, String houseName, String authToken) {
		// check if the the format is right

			// try to find the house it belongs to
			if (!AllHouseMap.containsKey(houseName)){
				System.out
						.println("The House have not been created");
			} else {
				House roomIn;
				roomIn = AllHouseMap.get(houseName); // find the house it is in;
				// check if the room name already exist
				if (roomIn.roomMap.containsKey(roomName)) {
					System.out.println(roomName + " name already exist!");
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
	 *  create occupant object
	 * @param occuName
	 * @param occuType
	 * @param authToken
	 */
	@Override
	public void defineOccupant(String occuName,String occuType, String authToken) {


			if (allOccupantMap.containsKey(occuName)) {
				// if the name already exist
				System.out
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
					System.out.println("Unknown type of occupant!");
				}

			}

	}

	/**
	 *  add occupant to house
	 * @param occName
	 * @param houseName
	 * @param authToken
	 */

	@Override
	public void addOccupant2House(String occName,String houseName ,String authToken) {
		// if the person is not defined yet
		if (!allOccupantMap.containsKey(occName)) {
			System.out.println("Can't find " + occName);
		}
		// if the room is not defined yet
		if (!AllHouseMap.containsKey(houseName)) {
			System.out.println("Can't find " + houseName);
		}
		// if the house and person are both defined
		if (allOccupantMap.containsKey(occName)
				&& AllHouseMap.containsKey(houseName)) {
			Occupant theOccup;
			House theHouse;
			theOccup = allOccupantMap.get(occName);
			theHouse = AllHouseMap.get(houseName);
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
	public House findHouse(String house, String authToken) {
		try {
			if (AllHouseMap.keySet().contains(house)) {
				return AllHouseMap.get(house);
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
	public Room findRoom(String input, String authToken) {
		House house;

		String[] loca = input.split(":");

		try {
			if (loca.length == 2) {
				house = findHouse(loca[0], authToken);
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
	public Sensor findSensor(String input,String authToken) {
		String[] loca = input.split(":");
		String[] locaNSen = new String[2];
		Room sensorInRoom;

		try {
			if (loca.length != 3) {
				throw new SensorNotFoundException(
						"the findSensor input has wrong format: " + input);
			} else {
				locaNSen[0] = loca[0] + ":" + loca[1];
				sensorInRoom = findRoom(locaNSen[0], authToken);
				if (sensorInRoom == null) {
					throw new SensorNotFoundException(
							"the sensor not found because the location of the sensor --"
									+ locaNSen[0] + "--is not found");
				} else if (!sensorInRoom.hasSensor(loca[2])) {
					throw new SensorNotFoundException("the location--"
							+ locaNSen[0] + " exist but the Sensor--"
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
	public Appliance findAppliance(String input, String authToekn) {
		String[] loca = input.split(":");
		String[] locaNSen = new String[2];
		Room AppInRoom;

		try {
			if (loca.length != 3) {
				throw new AppNotFoundException(
						"the findAppliance input has wrong format: " + input);
			} else {
				locaNSen[0] = loca[0] + ":" + loca[1];
				AppInRoom = findRoom(locaNSen[0], authToekn);
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
	 * create appliance object
	 * Note: appliance is an abstract class, it create its subclass based on input
	 * @param sensorName
	 * @param sensorType
	 * @param roomName
	 * @param authToken
	 */
	@Override
	public void defineSensor(String sensorName,String sensorType,String roomName, String authToken) {
		// theRoom variable to find the Room that the user give
		Room theRoom;
		Sensor theSensor;


		 if ((theRoom = findRoom(roomName, authToken)) == null) {

			System.out
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
	 * @param appName
	 * @param appType
	 * @param roomName
	 * @param authToken
	 */

	@Override
	public void defineAppliance(String appName,String appType,String roomName, String authToken) {
		// theRoom variable to find the Room that the user give
		Room theRoom;

		if ((theRoom = findRoom(roomName, authToken)) == null) {

			System.out
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
				System.out.println("Unknown type of appliance!");
			}
		}
	}

	/**
	 *  show the status of the sensor
	 * @param sensorName
	 * @param authToken
	 */
	@Override
	public void showSensor(String sensorName, String authToken) {
			Sensor theSensor;
			theSensor = findSensor(sensorName, authToken);
			if (theSensor != null) {
				System.out.println(theSensor.showStatus());
			}
	}

	/**
	 * show the status of the appliance
	 * @param appName
	 * @param statusName
	 * @param authToken
	 */
	@Override
	public void showApplianceStatus(String appName,String statusName,String authToken){
		Appliance theApp;
		theApp = findAppliance(appName, authToken);
		if (theApp != null  ) {
			theApp.showStatus(statusName);
		}
	}

	/**
	 * show all appliance status
	 * @param appName
	 * @param authToken
	 */
	public void showAllApplianceStatus(String appName,String authToken){
		Appliance theApp;
		theApp = findAppliance(appName, authToken);
		if (theApp != null  ) {
			theApp.showAllStatus();
		}
	}

	/**
	 *  show all the configuration of the house
	 * including all the rooms and devices and their status
	 * @param houseName
	 * @param authToken
	 */

	@Override
	public void showConfigHouse(String houseName, String authToken) {
		House theHouse = findHouse(houseName, authToken);
		if (theHouse != null) {
			theHouse.showOccupInHouse();
			theHouse.showRoomInHouse();
		}
	}

	/**
	 * set appliance status based on the appliance name
	 * @param appName
	 * @param statusName
	 * @param value
	 * @param authToken
	 */
	@Override
	public void setApplianceStatus(String appName, String statusName, String value,String authToken) {
		Appliance theApp;
		theApp = findAppliance(appName, authToken);
		if (theApp != null ) {
			theApp.changeStatus(statusName,value);
			theApp.showStatus(statusName);
			HouseMateControllerFactory.getInstance().updateAppliance(theApp, statusName);
			// theApp.configMode();
		}else{
			try {
				throw new AppNotFoundException(statusName+ " is not Found" );
			} catch (AppNotFoundException e) {

			}
		}
	}

	/**
	 *  show all the configuration of the room
	 * including all the  devices and their status
	 * @param roomName
	 * @param authToken
	 */
	@Override
	public void showConfigRoom(String roomName, String authToken) {
		Room theRoom = findRoom(roomName, authToken);
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
	public void showConfigAllHouse(String authToken) {
		for (String house : AllHouseMap.keySet()) {
			AllHouseMap.get(house).showOccupInHouse();
			AllHouseMap.get(house).showRoomInHouse();
		}
	}


	/**
	 *  set the status of the sensor or appliance
	 * @param sensorName
	 * @param statusName
	 * @param value
	 * @param tokens
	 * @param authToken
	 */
	public void setSensor(String sensorName, String statusName, String value, String[] tokens, String authToken){
		Sensor theSensor;
		theSensor = findSensor(sensorName, authToken);
		if(theSensor != null ){
			HouseMateControllerFactory.getInstance().updateSensor(theSensor, statusName,value,tokens, authToken);
		}else{
			try {
				throw new SensorNotFoundException(sensorName+ " is not Found" );
			} catch (SensorNotFoundException e) {
			}
		}
	}

	/**
	 * This method find a type of appliance based on the room name and the type of appliance
	 * @param location
	 * @param type
	 * @param authToken
	 * @return
	 */
	public List<Appliance> findApplianceByType(String location, String type, String authToken){
		List<Appliance> appList = new ArrayList<Appliance>();
		Room theRoom =findRoom(location, authToken);
		for(Appliance app: theRoom.getApplianceMap().values()){
			if(app.getType().equals(type)){
				appList.add(app);
			}
		}
		return appList;
	}


	/**
	 *This method find a type of appliance based on the house name location and the type of appliance
	 * @param houseName
	 * @param applianceType
	 * @param authToken
	 * @return
	 */
	public List<Appliance> findApplianceInHouse(String houseName, String applianceType, String authToken){
		List<Appliance> appListInHouse = new ArrayList<>();
		House house = findHouse(houseName,authToken);
		for(Room room : house.getRoomMap().values()){
			for(Appliance app :room.getApplianceMap().values()){
				if(app.getType().equals(applianceType)){
					appListInHouse.add(app);
				}
			}
		}
		return appListInHouse;
	}
	/**
	 * This method find a type of sensor in a house based on the house name and the type of sensor
	 * @param houseName
	 * @param sensorType
	 * @param authToken
	 * @return
	 */
	public List<Sensor> findSensorInHouse(String houseName, String sensorType, String authToken){
		List<Sensor> sensorListInHouse = new ArrayList<>();
		House house = findHouse(houseName,authToken);
		for(Room room : house.getRoomMap().values()){
			for(Sensor sensor :room.getSensorMap().values()){
				if(sensor.getType().equals(sensorType)){
					sensorListInHouse.add(sensor);
				}
			}
		}
		return sensorListInHouse;
	}

	/**
	 *This method find a type of sensor in a room based on the room name and the type of sensor
	 * @param roomName
	 * @param sensorType
	 * @param authToken
	 * @return
	 */
	public List<Sensor> findSensorInRoom(String roomName, String sensorType, String authToken){
		List<Sensor> sensorListInRoom = new ArrayList<>();
		Room room = findRoom(roomName,authToken);

			for(Sensor sensor :room.getSensorMap().values()){
				if(sensor.getType().equals(sensorType)){
					sensorListInRoom.add(sensor);
				}

		}
		return sensorListInRoom;
	}
}
