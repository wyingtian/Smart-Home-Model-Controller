package cscie97.asn3.housemate.controller.command;

import cscie97.asn3.housemate.controller.command.door.RoomDoorOpenCommand;
import cscie97.asn3.housemate.controller.command.light.HouseLightsOnCommand;
import cscie97.asn3.housemate.model.HouseMateModelFactory;
import cscie97.asn3.housemate.model.ServiceInterface;
import cscie97.asn3.housemate.model.IOTDevices.Ava;
import cscie97.asn3.housemate.model.IOTDevices.Sensor;
import cscie97.asn3.housemate.model.IOTDevices.SmokeDetector;

/**
 * SmokeDetectorCommand is executed when smokeDetected set to fire
 * @author ying
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
        Command com1 = new HouseLightsOnCommand(model,houseName,"");
        com1.execute();
        Command com2 = new RoomDoorOpenCommand((model.findApplianceByType(location,"door","")));
        com2.execute();
        allAvaInHouseSpeak(houseName, FireBroadCastMessage, " ");
        System.out.println("Calling 911");
    }

    /**
     * Ava broadcast fire message in all rooms
     * @param houseName
     * @param broadCastMessage
     * @param authToken
     */
    public void allAvaInHouseSpeak(String houseName,String broadCastMessage,String authToken){
        for(Sensor sen :model.findSensorInHouse(houseName, "Ava", authToken)) {
            ((Ava)sen).speak(broadCastMessage);
        }
    }
}

