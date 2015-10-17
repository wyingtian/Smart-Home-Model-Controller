package cscie97.asn3.housemate.controller.command;

import cscie97.asn3.housemate.model.HouseMateModelFactory;
import cscie97.asn3.housemate.model.IOTDevices.Camera;
import cscie97.asn3.housemate.model.ServiceInterface;

/**
 * Created by ying on 10/17/15.
 */

public class CameraCommand implements Command {
    String occStatus;
    String occ;
    Camera cam;
    ServiceInterface model;

    public CameraCommand(String occStatus, String occ, Camera cam) {
        this.occStatus = occStatus;
        this.occ = occ;
        this.cam = cam;
        model = HouseMateModelFactory.getInstance();
    }

    @Override
    public void execute() {
        String location = cam.getLocationPair();
        String tripleString = occ + " is_in " + location;

        if (!model.getAllOccupantMap().containsKey(occ)) {
            System.out.println("Unknown person " + occ + " detected");
        } else {
            if (occStatus.equals("OCCUPANT_DETECTED")) {
                System.out.println(occ + " entered " + location);
                model.getImporter().importTripleLine(tripleString);
                detectedProcedure(location, "");
            } else if (occStatus.equals("OCCUPANT_LEAVING")) {
                System.out.println(occ + " left " + location);
                model.getKnowledgeGraph().removeTriples(tripleString);
                leavingProcedure(location, "");
            } else if (occStatus.equals("OCCUPANT_ACTIVE")) {
                System.out.println(occ + " is " + "active");
                model.getKnowledgeGraph().removeTriples(occ + " is " + "sleeping");
                model.getImporter().importTripleLine(occ + " is " + "active");
            } else if (occStatus.equals("OCCUPANT_INACTIVE")) {
                System.out.println(occ + " is " + "sleeping");
                model.getKnowledgeGraph().removeTriples(occ + " is " + "active");
                model.getImporter().importTripleLine(occ + " is " + "sleeping");
                sleepProcedure(location, "");

            }
        }
    }

    public void sleepProcedure(String roomLocationPair,String auth_token){
        model.dimmerAllLights(model.findApplianceByType(roomLocationPair, "light", auth_token));
    }
    public void detectedProcedure(String roomLocationPair,String auth_token){
        model.turnOnAllLights(model.findApplianceByType(roomLocationPair, "light", auth_token));
        model.warmerThermostat(model.findApplianceByType(roomLocationPair, "thermostat", auth_token));
    }
    public void leavingProcedure(String roomLocationPair,String auth_token){
        model.turnOffAllLights(model.findApplianceByType(roomLocationPair, "light", auth_token));
        model.coolerThermostat(model.findApplianceByType(roomLocationPair, "thermostat", auth_token));
    }
}