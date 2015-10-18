package cscie97.asn3.housemate.controller.command;

import cscie97.asn3.housemate.model.HouseMateModel;
import cscie97.asn3.housemate.model.HouseMateModelFactory;
import cscie97.asn3.housemate.model.IOTDevices.Appliance;
import cscie97.asn3.housemate.model.IOTDevices.Ava;
import cscie97.asn3.housemate.model.ServiceInterface;

import java.util.List;

/**
 * Created by ying on 10/17/15.
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
                model.turnOnAllLights(model.findApplianceByType(ava.getLocationPair(), "light", ""));
            }else if(stimulus.equals("open door")){
                model.openDoors(model.findApplianceByType(ava.getLocationPair(), "door", ""));
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
