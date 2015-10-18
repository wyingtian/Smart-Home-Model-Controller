package cscie97.asn3.housemate.model.IOTDevices;

import cscie97.asn3.housemate.model.Room;
/**
 * regrigerator class
 * @author ying
 *
 */
public class Refrigerator extends Appliance  {
	private String temperature;
	private String beerCount;
	private String Power;
	
	public Refrigerator(String name, String type, Room location) {
		super(name, type, location);
	}
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	public String getBeerCount() {
		return beerCount;
	}
	public void setBeerCount(String beerCount) {

		this.beerCount = beerCount;
	}

	public void beerCountChanged(){
		setChanged();
		notifyObservers();
	}

	public String getPower() {
		return Power;
	}
	public void setPower(String power) {
		Power = power;
	}
	@Override
	public String showInfo() {
		return "The name of the " + type + " is " + name + ", It is in Room "
				+ location.roomInfo();
	}
	@Override
	public void showAllStatus() {
		System.out.print("The refrigerator: ");
		System.out.println(name);
		System.out.println("Status Power: ");
		System.out.println(getPower());
		System.out.println("Status temperature: ");
		System.out.println(getTemperature());
		System.out.println("Status beer count: ");
		System.out.println(getBeerCount());
		
	}
	public void clean(){
		System.out.println("The fridge is cleaning itself!");
	}
	@Override
	public void configMode() {
		
	}
	@Override
	public void changeStatus(String status, String value) {
		
		switch (status){
		case "power":  setPower(value);break;
		case "temperature": setTemperature(value);break;
		case "beercount":setBeerCount(value);break;
		case "clean": clean();break;
		default:System.err.println("Wrong fridge status input");break;
		}

	}
	@Override
	public void showStatus(String status) {
		switch (status){
		case "power":  System.out.println("The power status of the fridge is now "+getPower());break;
		case "temperature": System.out.println("The temperature of the fridge is now "+getTemperature());;break;
		case "beercount":System.out.println("Beer count is now "+getBeerCount());break;
		case "clean":System.out.println("The fridge is clean now");;break;
		default:System.err.println("Wrong fridge status input");break;
		}
	}
	@Override
	public void setDefault() {
		setTemperature("0");
		setPower("OFF");
		setBeerCount("0");
		
	}
}
