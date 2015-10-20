package cscie97.asn3.housemate.command;

import cscie97.asn3.housemate.model.IOTDevices.Appliance;

/**
 * Created by ying on 10/19/15.
 */
public class ApplianceNoOpCommand implements Command{
    Appliance theAppliance;
    String statusName;
    public ApplianceNoOpCommand(Appliance theAppliance, String statusName){
        this.theAppliance = theAppliance;
        this.statusName = statusName;
    }
    @Override
    public void execute() {
        theAppliance.showStatus(statusName);
    }
}
