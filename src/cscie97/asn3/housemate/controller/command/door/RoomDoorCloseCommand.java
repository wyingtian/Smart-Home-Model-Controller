package cscie97.asn3.housemate.controller.command.door;

import cscie97.asn3.housemate.controller.command.Command;
import cscie97.asn3.housemate.model.IOTDevices.Appliance;
import cscie97.asn3.housemate.model.IOTDevices.Door;

import java.util.List;

/**
 * Created by ying on 10/19/15.
 */
public class RoomDoorCloseCommand implements Command {
    List<Appliance> list;
    public RoomDoorCloseCommand(List<Appliance> list){
        this.list = list;
    }
    @Override
    public void execute() {

        if(list.isEmpty()){
            System.out.println("no door in this room");
            return;
        }
        for(Appliance app: list){
            DoorCloseCommand com = new DoorCloseCommand((Door)app);
            com.execute();
        }
    }

}
