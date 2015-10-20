package cscie97.asn3.housemate.command;

import cscie97.asn3.housemate.command.door.RoomDoorOpenCommand;
import cscie97.asn3.housemate.command.light.RoomLightsOnCommand;
import cscie97.asn3.housemate.component.HouseMateModel;
import cscie97.asn3.housemate.component.HouseMateModelFactory;
import cscie97.asn3.housemate.model.IOTDevices.Appliance;
import cscie97.asn3.housemate.model.IOTDevices.Ava;
import cscie97.asn3.housemate.component.ServiceInterface;

import java.util.List;

/**
 * AvaCommand is executed when ava status changes
 * @author ying
 */
public class AvaCommand implements Command {
    String stimulus;
    Ava ava;
    ServiceInterface model;


    public AvaCommand(String stimulus, Ava ava) {
        this.stimulus = stimulus;
        this.ava = ava;
        model = HouseMateModelFactory.getInstance();
    }


    @Override
    public void execute() {
            String[] tokens = stimulus.split(" ");
            if(stimulus.equals("lights on")){
                ava.getLocationPair();
                Command com = new RoomLightsOnCommand(model.findApplianceByType(ava.getLocationPair(), "light", ""));
                com.execute();
            }else if(stimulus.equals("open door")){
                Command com = new RoomDoorOpenCommand(model.findApplianceByType(ava.getLocationPair(), "door", ""));
                com.execute();
            }
            else if(tokens.length == 3 && tokens[0].equals("where") && tokens[1].equals("is")){
                model.getQueryEngine().executeQuery(tokens[2] + " is_in " + "?");
            }else if(tokens.length == 3 && HouseMateModel.isApplianceType(tokens[0])){
                List<Appliance> list = model.findApplianceByType(ava.getLocationPair(),tokens[0] , "");
                for(Appliance app:list){
                    app.changeStatus(tokens[1],tokens[2]);
                    app.showStatus(tokens[1]);
                }
            }else if(tokens.length == 3 && (tokens[0].equals("?")||tokens[1].equals("?")||tokens[2].equals("?"))){
                model.getQueryEngine().executeQuery(stimulus);
            }
    }

}
