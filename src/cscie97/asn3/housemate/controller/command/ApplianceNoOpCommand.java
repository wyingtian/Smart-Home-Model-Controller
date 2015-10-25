package cscie97.asn3.housemate.controller.command;

import cscie97.asn3.housemate.model.IOTDevices.Appliance;

/**
 * when the appliance status changes but no action is performed
 * it only show the message of status changes
 * @author ying
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
