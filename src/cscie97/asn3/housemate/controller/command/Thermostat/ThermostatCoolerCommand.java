package cscie97.asn3.housemate.controller.command.Thermostat;

import cscie97.asn3.housemate.controller.command.Command;
import cscie97.asn3.housemate.model.IOTDevices.Thermostat;

/**
 * Created by ying on 10/16/15.
 */
public class ThermostatCoolerCommand implements Command {
    Thermostat theThermostat;

    public ThermostatCoolerCommand(Thermostat thermostat) {
        this.theThermostat = thermostat;
    }

    @Override
    public void execute() {
        theThermostat.changeStatus("temperature","cooler");
    }
}