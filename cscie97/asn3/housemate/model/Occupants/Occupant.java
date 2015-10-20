package cscie97.asn3.housemate.model.Occupants;

import java.util.HashMap;

import cscie97.asn3.housemate.component.House;

/**
 * This is the Occupant class that represents the Occupant in one Home
 * 
 * @author ying
 *
 */
public abstract class Occupant {
	protected String name;
	protected String type;
	protected String status;
	protected House houseIn;
//	protected Room roomIn;
	public HashMap<String, House> houseHeGoMap = new HashMap<String, House>();

	public Occupant(String name, String type) {

		this.name = name;
		this.type = type;
	}

	public String getName() {
		return this.name;
	}
/**
 * add the occupant to the house
 * @param house
 */
	public void addHouse(House house) {

		this.houseHeGoMap.put(house.getName(), house);

	}

}
