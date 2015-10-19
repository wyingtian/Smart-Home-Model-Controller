package cscie97.asn3.housemate.controller.command;

import cscie97.asn3.housemate.model.IOTDevices.Appliance;

/**
 * Created by ying on 10/19/15.
 */
public class NoOpCommand implements Command{
    Appliance theAppliance;
    String statusName;
    public NoOpCommand( Appliance theAppliance,String statusName){
        this.theAppliance = theAppliance;
        this.statusName = statusName;
    }

    @Override
    public void execute() {
        theAppliance.showStatus(statusName);
    }
}
