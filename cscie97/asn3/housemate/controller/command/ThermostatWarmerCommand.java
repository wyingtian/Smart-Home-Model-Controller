package cscie97.asn3.housemate.controller.command;

import cscie97.asn3.housemate.model.IOTDevices.Thermostat;

/**
 * Created by ying on 10/16/15.
 */
public class ThermostatWarmerCommand implements Command {
    Thermostat theThermostat;

    public ThermostatWarmerCommand(Thermostat thermostat) {
        this.theThermostat = thermostat;
    }

    @Override
    public void execute() {
        theThermostat.changeStatus("temperature","warmer");
    }
}