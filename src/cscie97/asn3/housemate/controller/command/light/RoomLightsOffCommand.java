package cscie97.asn3.housemate.controller.command.light;

import cscie97.asn3.housemate.controller.command.Command;
import cscie97.asn3.housemate.model.IOTDevices.Appliance;

import java.util.List;

/**
 * Created by ying on 10/19/15.
 */
public class RoomLightsOffCommand implements Command {
    List<Appliance> list;
    public RoomLightsOffCommand(List<Appliance> list){
        this.list = list;
    }
    @Override
    public void execute() {

        if(list.isEmpty()){
            System.out.println("no lights in this room");
            return;
        }
        for(Appliance app: list){
            app.changeStatus("power","off");
        }
    }

}
