package cscie97.asn3.housemate.controller.command.light;

import cscie97.asn3.housemate.controller.command.Command;
import cscie97.asn3.housemate.model.IOTDevices.Light;

/**
 * Created by ying on 10/16/15.
 */
public class LightOffCommand implements Command {
    Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.setPower("OFF");
    }
}