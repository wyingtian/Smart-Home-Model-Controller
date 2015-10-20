package cscie97.asn3.housemate.component;

import cscie97.asn3.housemate.model.IOTDevices.Sensor;

import java.util.Observable;

/**
 * Created by ying on 10/19/15.
 */
public interface ModelObserverInterface {
    public void  updateAppliance(Observable obs, String arg);
    public void updateSensor(Sensor theSensor, String statusName, String value, String[] tokens, String authToken);
}
