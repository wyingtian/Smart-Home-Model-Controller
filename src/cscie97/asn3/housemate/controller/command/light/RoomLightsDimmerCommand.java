package cscie97.asn3.housemate.controller.command.light;

import cscie97.asn3.housemate.controller.command.Command;
import cscie97.asn3.housemate.model.IOTDevices.Appliance;
import cscie97.asn3.housemate.model.IOTDevices.Light;

import java.util.List;

/**
 * Created by ying on 10/19/15.
 */
public class RoomLightsDimmerCommand implements Command {
    List<Appliance> list;
    public RoomLightsDimmerCommand(List<Appliance> list){
        this.list = list;
    }
    @Override
    public void execute() {

        if(list.isEmpty()){
            System.out.println("no lights in this room");
            return;
        }
        for(Appliance app: list){
            LightDimmerCommand com = new LightDimmerCommand((Light)app);
            com.execute();
            app.showStatus("intensity");
        }
    }

}
