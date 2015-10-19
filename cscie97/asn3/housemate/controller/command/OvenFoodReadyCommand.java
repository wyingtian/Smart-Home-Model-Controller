package cscie97.asn3.housemate.controller.command;

import cscie97.asn3.housemate.model.HouseMateModelFactory;
import cscie97.asn3.housemate.model.IOTDevices.Appliance;
import cscie97.asn3.housemate.model.IOTDevices.Oven;

/**
 * Created by ying on 10/19/15.
 */
public class OvenFoodReadyCommand implements Command {
    Appliance theAppliance;
    String statusName;
    public OvenFoodReadyCommand(Appliance theAppliance, String statusName){
        this.theAppliance = theAppliance;
        this.statusName =statusName;
    }
    @Override
    public void execute() {

        ((Oven) theAppliance).setPower("off");
        System.out.println( "time to cook changed to 0");
        HouseMateModelFactory.getInstance().avaInRoomSpeak(theAppliance.getLocationPair(), "Food is Ready", "");
    }
}
