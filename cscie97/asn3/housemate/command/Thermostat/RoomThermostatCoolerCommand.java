package cscie97.asn3.housemate.command.Thermostat;

import cscie97.asn3.housemate.command.Command;
import cscie97.asn3.housemate.model.IOTDevices.Appliance;

import cscie97.asn3.housemate.model.IOTDevices.Thermostat;

import java.util.List;

/**
 * Created by ying on 10/19/15.
 */
public class RoomThermostatCoolerCommand implements Command {
    List< Appliance > list;
    public RoomThermostatCoolerCommand(List<Appliance> list){
        this.list = list;
    }
    @Override
    public void execute() {

        if(list.isEmpty()){
            System.out.println("no thermostat in this room");
            return;
        }
        for(Appliance app: list){
            ThermostatCoolerCommand com = new ThermostatCoolerCommand((Thermostat)app);
            com.execute();
        }
    }

}
