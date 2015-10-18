package cscie97.asn3.housemate.controller.command;

import cscie97.asn3.housemate.model.HouseMateModelFactory;
import cscie97.asn3.housemate.model.IOTDevices.SmokeDetector;
import cscie97.asn3.housemate.model.ServiceInterface;

/**
 * Created by ying on 10/18/15.
 */

public class SmokeDetectorCommand implements Command {
    String mode;
    String value;
    SmokeDetector smokeDetector;
    ServiceInterface model;

    public SmokeDetectorCommand(String mode, String value, SmokeDetector smokeDetector) {
        this.mode = mode;
        this.value = value;
        this.smokeDetector = smokeDetector;
        model = HouseMateModelFactory.getInstance();
    }

    @Override
    public void execute() {
        String location = smokeDetector.getLocationPair();
        System.out.println("FIRE!! Starting evacuation procedure");
        String houseName = location.split(":")[0];
        String roomName = location.split(":")[1];
        String FireBroadCastMessage = "Fire in "+ roomName +" , Please leave "+ houseName +" immediately";
        model.turnOnLightsInHouse(houseName,"");
        model.allAvaInHouseSpeak(houseName, FireBroadCastMessage, " ");
        System.out.println("Calling 911");

    }

}

