package cscie97.asn3.housemate.controller.command;

import cscie97.asn3.housemate.model.HouseMateModelFactory;
import cscie97.asn3.housemate.model.ServiceInterface;
import cscie97.asn3.housemate.model.IOTDevices.Appliance;
import cscie97.asn3.housemate.model.IOTDevices.Ava;
import cscie97.asn3.housemate.model.IOTDevices.Oven;
import cscie97.asn3.housemate.model.IOTDevices.Sensor;

/**
 * the command for food is ready
 * @author ying
 */
public class OvenFoodReadyCommand implements Command {
    Appliance theAppliance;
    String statusName;
    ServiceInterface model;
    public OvenFoodReadyCommand(Appliance theAppliance, String statusName){
        this.theAppliance = theAppliance;
        this.statusName =statusName;
        model = HouseMateModelFactory.getInstance();
    }
    @Override
    public void execute() {
        ((Oven) theAppliance).setPower("off");
        System.out.println( "time to cook changed to 0");
        avaInRoomSpeak(theAppliance.getLocationPair(), "Food is Ready", "");
    }
    public void avaInRoomSpeak(String avaLocation,String broadCastMessage,String authToken){
        for(Sensor sen :model.findSensorInRoom(avaLocation, "Ava", authToken)) {
            ((Ava)sen).speak(broadCastMessage);
        }
    }
}
