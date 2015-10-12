package cscie97.asn3.housemate.model;

import java.util.HashMap;

import cscie97.asn3.housemate.model.Occupants.Occupant;

/**
 * the House class, has all the room information and is able to get the all the
 * device info from the room.
 * 
 * @author ying
 *
 */
public class House {
	private String name;
	HashMap<String, Room> roomMap;
	HashMap<String, Occupant> occuMap;

	// constructor
	public House(String name) {
		this.name = name;
		roomMap = new HashMap<String, Room>();
		occuMap = new HashMap<String, Occupant>();
	}

	/**
	 * if the house has the room room, check if the house has the room based on
	 * room name;
	 * 
	 * @param String
	 * @return boolean
	 */
	public boolean hasRoom(String room) {
		return this.roomMap.containsKey(room);
	}

	/**
	 * return the Room Object if it exists, else return null;
	 * 
	 * @param String
	 * @return room object;
	 */
	public Room getRoom(String room) {
		if (hasRoom(room)) {
			return roomMap.get(room);
		} else
			return null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * addoccupant to the House
	 * 
	 * @param Occupant
	 */
	public void addOccupant(Occupant occ) {

		this.occuMap.put(occ.getName(), occ);

	}

	/**
	 * show all the occupants of the house
	 */
	public void showOccupInHouse() {
		for (String item : occuMap.keySet()) {
			System.out.println("The House " + name + " has the occupants:"
					+ item);
		}
	}
	/**
	 * show all the room of the house, devices and their status
	 */
	public void showRoomInHouse() {
		for (String room : roomMap.keySet()) {
			System.out.println(getName() + " has room: " + room);
			roomMap.get(room).showSenInRoom();
			roomMap.get(room).showAppInRoom();
		}
	}
}
