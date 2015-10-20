package cscie97.asn3.housemate.command;

import cscie97.asn3.housemate.model.IOTDevices.Appliance;
import cscie97.asn3.housemate.model.IOTDevices.Sensor;

/**
 * Created by ying on 10/19/15.
 */
public class SensorNoOpCommand implements Command{
    Sensor theSensor;

    public SensorNoOpCommand(Sensor theSensor){
        this.theSensor = theSensor;
    }
    @Override
    public void execute() {
        System.out.println(theSensor.showStatus());
    }
}
