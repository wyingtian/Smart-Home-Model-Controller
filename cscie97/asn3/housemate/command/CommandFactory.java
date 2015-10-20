package cscie97.asn3.housemate.command;

import cscie97.asn3.housemate.model.IOTDevices.*;
import java.util.Observable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A factory to create command based on type of sensor or appliance
 * It also parses the Ava voice input used as Ava status value
 * @author ying
 */
public class CommandFactory {
    /**
     * This method generate a type of  sensor command and return the command
     * @param theSensor
     * @param sensorType
     * @param statusName
     * @param value
     * @param tokens
     * @return
     */
    public static Command createSensorCommand(Sensor theSensor,String sensorType, String statusName, String value, String[] tokens){
        Command command = null;
        if (sensorType.equals("Ava") ) {
            command = new AvaCommand(getAvaCommand(tokens), (Ava) theSensor);
        } else if(sensorType.equals("camera")){
            command  = new CameraCommand(statusName,value,(Camera)theSensor);
        }else if(sensorType.equals("smoke_detector")){
            theSensor.setStatus(statusName,value);
            if(theSensor.getValue().equals("FIRE")){
                command= new SmokeDetectorCommand(statusName,value,(SmokeDetector)theSensor);
            }else {
                command = new SensorNoOpCommand(theSensor);}
        }else {
            command = new SensorNoOpCommand(theSensor);
        }
        return command;
    }

    /**
     * This method generate a type of appliance command and return the command
     * @param obs
     * @param statusName
     * @return
     */
    public static Command createApplianceCommand(Observable obs,String statusName){
        Command command = null;
        Appliance theAppliance = (Appliance)obs;
        if(obs instanceof Refrigerator){
            theAppliance = (Refrigerator)obs;
            if( (Integer.parseInt(((Refrigerator)theAppliance).getBeerCount()) < 4)){
                command = new BeerCountLowCommand((Refrigerator)theAppliance);
            }else{
                command = new ApplianceNoOpCommand(theAppliance,statusName);
            }
        }else if(obs instanceof Oven){
            theAppliance = (Oven)obs;
            if( (Integer.parseInt(((Oven)theAppliance).getTimeToCook()) == 0)){
                    command = new OvenFoodReadyCommand(theAppliance,statusName);
            }else{
                command = new ApplianceNoOpCommand(theAppliance,statusName);
            }
        }else {
            command = new ApplianceNoOpCommand(theAppliance,statusName);
        }
        return command;
    }

    /**
     * This method use regular expression to get the ava command input like ’open door’
     * @param tokens
     * @return
     */
    public static String getAvaCommand(String []tokens){
        StringBuilder line;
        String Stimulus="";
        String Statement="";
        line = new StringBuilder();
        for(int i = 0; i < tokens.length;i++){
            line.append(tokens[i]);
            line.append(" ");
        }
        Pattern p = Pattern.compile("\"([^\"]*)\"");
        Matcher m = p.matcher(line);
        while (m.find()) {
            Statement=m.group(1);
        }

        Pattern l = Pattern.compile("([^\']*)\'([^\']*)\'");
        Matcher n = l.matcher(Statement);
        while (n.find()) {
            Stimulus=n.group(2);
        }
        return Stimulus;
    }
}
