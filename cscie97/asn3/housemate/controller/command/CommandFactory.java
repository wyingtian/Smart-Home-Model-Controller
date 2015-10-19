package cscie97.asn3.housemate.controller.command;

import cscie97.asn3.housemate.controller.HouseMateControllerFactory;
import cscie97.asn3.housemate.model.HouseMateModelFactory;
import cscie97.asn3.housemate.model.IOTDevices.*;

import java.util.Observable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ying on 10/19/15.
 */
public class CommandFactory {

    public static Command createSensorCommand(Sensor theSensor,String sensorType, String statusName, String value, String[] tokens){
        Command command = null;
        if (sensorType.equals("Ava") ) {
            command = new AvaCommand(getAvaCommand(tokens), (Ava) theSensor);
            // executeAvaCommand(getAvaCommand(tokens), (Ava) theSensor);
            // System.out.println(theSensor.showStatus());
        } else if(sensorType.equals("camera")){
            command  = new CameraCommand(statusName,value,(Camera)theSensor);

            // executeCameraCommand(tokens[4],tokens[6],(Camera)theSensor);
        }else if(sensorType.equals("smoke_detector")){
            theSensor.setStatus(statusName,value);
            if(theSensor.getValue().equals("FIRE")){
                command= new SmokeDetectorCommand(statusName,value,(SmokeDetector)theSensor);
            }
        }
        return command;
    }
    public static Command createApplianceCommand(Observable obs,String statusName){
        Command command = null;
        Appliance theAppliance = (Appliance)obs;
        if(obs instanceof Refrigerator){
            theAppliance = (Refrigerator)obs;
            if( (Integer.parseInt(((Refrigerator)theAppliance).getBeerCount()) < 4)){
                command = new BeerCountLowCommand((Refrigerator)theAppliance);
//                System.out.println(arg + " has changed");
//                model.avaInRoomSpeak(theAppliance.getLocationPair(), "Would you like more beer?", "");
//                beerRequestPrompt();
            }else{
                command = new NoOpCommand(theAppliance,statusName);
            }
        }else if(obs instanceof Oven){
            theAppliance = (Oven)obs;
            if( (Integer.parseInt(((Oven)theAppliance).getTimeToCook()) == 0)){
                    command = new OvenFoodReadyCommand(theAppliance,statusName);
            }else{
                command = new NoOpCommand(theAppliance,statusName);
            }
        }else {
            command = new NoOpCommand(theAppliance,statusName);
        }
        return command;
    }
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
