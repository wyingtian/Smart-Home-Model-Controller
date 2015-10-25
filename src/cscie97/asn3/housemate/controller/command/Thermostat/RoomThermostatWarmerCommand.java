package cscie97.asn3.housemate.controller.command.Thermostat;

import cscie97.asn3.housemate.controller.command.Command;
import cscie97.asn3.housemate.model.IOTDevices.Appliance;

import cscie97.asn3.housemate.model.IOTDevices.Thermostat;

import java.util.List;

/**
 * Created by ying on 10/19/15.
 */
public class RoomThermostatWarmerCommand implements Command {
    List<Appliance> list;
    public RoomThermostatWarmerCommand(List<Appliance> list){
        this.list = list;
    }
    @Override
    public void execute() {

        if(list.isEmpty()){
            System.out.println("no thermostat in this room");
            return;
        }
        for(Appliance app: list){
            ThermostatWarmerCommand com = new ThermostatWarmerCommand((Thermostat)app);
            com.execute();
        }
    }

}
