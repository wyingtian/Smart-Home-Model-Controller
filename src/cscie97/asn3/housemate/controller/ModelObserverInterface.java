package cscie97.asn3.housemate.controller;

import cscie97.asn3.housemate.model.IOTDevices.Sensor;

import java.util.Observable;

/**
 * Interface for HouseMateController implement observer methods.
 * @author ying
 */
public interface ModelObserverInterface {

    /**
     * House Mate Controller is an observer implements ModelObserverInterface,
     * this is to update a appliance settings and take actions
     * based on the trigger when it observed changes.
     * @param obs
     * @param arg
     */
    public void  updateAppliance(Observable obs, String arg);

    /**
     * House Mate Controller is an observer implements ModelObserverInterface,
     * this is to update a sensor settings when it observed changes.
     * @param theSensor
     * @param statusName
     * @param value
     * @param tokens
     * @param authToken
     */
    public void updateSensor(Sensor theSensor, String statusName, String value, String[] tokens, String authToken);
}
