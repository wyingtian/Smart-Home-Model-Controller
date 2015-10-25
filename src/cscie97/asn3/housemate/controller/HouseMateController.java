package cscie97.asn3.housemate.controller;
import cscie97.asn3.housemate.controller.command.CommandFactory;
import cscie97.asn3.housemate.model.IOTDevices.Sensor;

import java.util.Observable;

/**
 *The House Mate Controller Service use the interface of the House Mate Model Service
 *to monitor the status the IOT devices.
 * @author ying
 */
public class HouseMateController implements ModelObserverInterface {

    private  static HouseMateController theController = null;
    private HouseMateController(){

    }

    /**
     * HouseMateController is a singleton, this method
     * return the instance of itself
     * @return  the instance of itself
     */
    public static  HouseMateController getInstance(){
        if (theController == null) {
            theController = new HouseMateController();
        }
        return theController;
    }

    /**
     * House Mate Controller is an observer implements ModelObserverInterface,
     * this is to update a sensor settings when it observed changes.
     * @param theSensor
     * @param statusName
     * @param value
     * @param tokens
     * @param authToken
     */
    public void updateSensor(Sensor theSensor, String statusName, String value, String[] tokens, String authToken){
            String sensorType;
            sensorType = theSensor.getType();
            CommandFactory.createSensorCommand(theSensor, sensorType, statusName, value, tokens).execute();
    }

    /**
     * House Mate Controller is an observer implements ModelObserverInterface,
     * this is to update a appliance settings and take actions
     * based on the trigger when it observed changes.
     * @param obs
     * @param arg
     */
    public void updateAppliance(Observable obs, String arg) {
           CommandFactory.createApplianceCommand(obs,arg).execute();
    }


}

