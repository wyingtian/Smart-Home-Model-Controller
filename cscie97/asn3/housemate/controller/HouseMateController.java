package cscie97.asn3.housemate.controller;

import cscie97.asn3.housemate.controller.command.*;
import cscie97.asn3.housemate.model.*;
import cscie97.asn3.housemate.model.IOTDevices.*;
import cscie97.asn3.housemate.model.exception.SensorNotFoundException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Created by ying on 10/10/15.
 */

public class HouseMateController implements Observer{
    Observable observable;

    private static final HouseMateController theController = new HouseMateController();
    ServiceInterface model = HouseMateModel.getInstance();
//    StringBuilder line;
//    Importer i = new Importer();
//    QueryEngine q = new QueryEngine();
//    private HouseMateController( observable){
//        this.observable = observable;
//        observable.addObserver(this);
//    }

    private HouseMateController(){

    }

    public static HouseMateController getInstance(){
        return theController;
    }
    @Override
    public void update(Observable obs, Object arg) {
        Sensor theSensor;
        Appliance theAppliance;

        if(obs instanceof Refrigerator){

           theAppliance = (Refrigerator)obs;
            if( (Integer.parseInt(((Refrigerator)theAppliance).getBeerCount()) < 4)){
                System.out.println(arg + " has changed");
                model.avaInRoomSpeak(theAppliance.getLocationPair(), "Would you like more beer?", "");
                Scanner in = new Scanner(System.in);
                String s;
                System.out.println("Enter yes or no");
                s = in.nextLine();
                if(s.equals("yes")){
                    System.out.println("Order email has been sent");
                }else if(s.equals("no")){
                    System.out.println("Ok, no beer for you :(");
                }
            }
        }else if(obs instanceof Oven){
            theAppliance = (Oven)obs;
            if( (Integer.parseInt(((Oven)theAppliance).getTimeToCook()) == 0)){
                ((Oven) theAppliance).setPower("off");
                System.out.println(arg + " has changed");
                model.avaInRoomSpeak(theAppliance.getLocationPair(),"Food is Ready","");
            }
        }

    }

    public static void main(String args[]){
        HouseMateController con= HouseMateController.getInstance();
        String inputName = args[0];
        try {
            HouseMateCLI.importConfigFile(inputName);
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + inputName);
        } catch (IOException e) {
            System.out.println("Unable to read file: "+inputName);
        }
    }



//
//    public void createCommand(String deviceName, String deviceStatusName, String deviceStatusValue, String[] tokens,Appliance app; String authToken){
//        Sensor theSensor;
//        String sensorType;
//        theSensor = findSensor(sensorName, authToken);
//        if(theSensor != null ){
//            sensorType = theSensor.getType();
//            if (sensorType.equals("Ava") ) {
//                AvaCommand avaCom = new AvaCommand(getAvaCommand(tokens), (Ava) theSensor);
//                avaCom.execute();
//                // executeAvaCommand(getAvaCommand(tokens), (Ava) theSensor);
//                // System.out.println(theSensor.showStatus());
//            } else if(sensorType.equals("camera")){
//                CameraCommand camCom = new CameraCommand(statusName,value,(Camera)theSensor);
//                camCom.execute();
//                // executeCameraCommand(tokens[4],tokens[6],(Camera)theSensor);
//            }else if(sensorType.equals("smoke_detector")){
//                theSensor.setStatus(statusName,value);
//                if(theSensor.getValue().equals("FIRE")){
//                    SmokeDetectorCommand smoDetCom = new SmokeDetectorCommand(statusName,value,(SmokeDetector)theSensor);
//                    smoDetCom.execute();
//                }
//            }
//        }else{
//            try {
//                throw new SensorNotFoundException(sensorName+ " is not Found" );
//            } catch (SensorNotFoundException e) {
//            }
//        }
//    }


//    public String getAvaCommand(String []tokens){
//        String Stimulus="";
//        String Statement="";
//        line = new StringBuilder();
//        for(int i = 0; i < tokens.length;i++){
//            line.append(tokens[i]);
//            line.append(" ");
//        }
//        Pattern p = Pattern.compile("\"([^\"]*)\"");
//        Matcher m = p.matcher(line);
//        while (m.find()) {
//            Statement=m.group(1);
//        }
//
//        Pattern l = Pattern.compile("([^\']*)\'([^\']*)\'");
//        Matcher n = l.matcher(Statement);
//        while (n.find()) {
//            Stimulus=n.group(2);
//        }
//        return Stimulus;
//    }



    /**
     * set the status of the sensor or appliance
     * @param tokens the String[] is the tokenized command
     * @param auth_token for access control
     */

//    public void setSensor(String[] tokens, String auth_token) {
//        Sensor theSensor;
//        String sensorType;
//        theSensor = model.findSensor(tokens[2], auth_token);
//        if(theSensor != null ){
//        sensorType = theSensor.getType();
//            if (sensorType.equals("Ava") ) {
//                AvaCommand avaCom = new AvaCommand(getAvaCommand(tokens), (Ava) theSensor);
//                avaCom.execute();
//           // executeAvaCommand(getAvaCommand(tokens), (Ava) theSensor);
//            // System.out.println(theSensor.showStatus());
//            } else if(sensorType.equals("camera")){
//                CameraCommand camCom = new CameraCommand(tokens[4],tokens[6],(Camera)theSensor);
//                camCom.execute();
//           // executeCameraCommand(tokens[4],tokens[6],(Camera)theSensor);
//            }
//        }else{
//            try {
//                throw new SensorNotFoundException(tokens[2]+ " is not Found" );
//            } catch (SensorNotFoundException e) {
//            }
//        }
//    }


    //    public void executeAvaCommand(String stimulus, Ava ava){
//        String[] tokens = stimulus.split(" ");
//        if(stimulus.equals("lights on")){
//            ava.getLocationPair();
//            turnOnAllLights(findApplianceByType(ava.getLocationPair(), "light", ""));
//        }else if(stimulus.equals("open door")){
//            openDoors(findApplianceByType(ava.getLocationPair(), "door", ""));
//        }else if(tokens.length == 3 && tokens[0].equals("where") && tokens[1].equals("is")){
//            q.executeQuery(tokens[2]+" is_in "+"?");
//        }else if(tokens.length == 3 && HouseMateModel.isApplianceType(tokens[0])){
//            List<Appliance> list = findApplianceByType(ava.getLocationPair(),tokens[0] , "");
//            for(Appliance app:list){
//                app.changeStatus(tokens[1],tokens[2]);
//                app.showStatus(tokens[1]);
//            }
//        }else if(tokens.length == 3 && (tokens[0].equals("?")||tokens[1].equals("?")||tokens[2].equals("?"))){
//            q.executeQuery(stimulus);
//        }
//    }




    //
//    public void sleepProcedure(String roomLocationPair,String auth_token){
//        model.dimmerAllLights(model.findApplianceByType(roomLocationPair, "light", auth_token));
//    }
//    public void detectedProcedure(String roomLocationPair,String auth_token){
//        model.turnOnAllLights(model.findApplianceByType(roomLocationPair, "light", auth_token));
//       model.warmerThermostat(model.findApplianceByType(roomLocationPair, "thermostat", auth_token));
//    }
//    public void leavingProcedure(String roomLocationPair,String auth_token){
//        model.turnOffAllLights(model.findApplianceByType(roomLocationPair, "light", auth_token));
//        model.coolerThermostat(model.findApplianceByType(roomLocationPair, "thermostat", auth_token));
//    }
//    public void executeCameraCommand(String occStatus,String occ,Camera cam){
//        String location = cam.getLocationPair();
//        String tripleString = occ+" is_in "+location;
//
//        if(!model.getAllOccupantMap().containsKey(occ)){
//            System.out.println("Unknown person " + occ +" detected");
//        }else{
//            if(occStatus.equals("OCCUPANT_DETECTED")){
//                System.out.println(occ+" entered "+ location);
//                model.getImporter().importTripleLine(tripleString);
//                detectedProcedure(location, "");
//            }else if(occStatus.equals("OCCUPANT_LEAVING")){
//                System.out.println(occ+" left "+ location);
//                model.getKnowledgeGraph().removeTriples(tripleString);
//                leavingProcedure(location,"");
//            }else if(occStatus.equals("OCCUPANT_ACTIVE")){
//                System.out.println(occ+" is "+ "active");
//                model.getKnowledgeGraph().removeTriples(occ + " is " + "sleeping");
//                model.getImporter().importTripleLine(occ + " is " + "active");
//            }else if(occStatus.equals("OCCUPANT_INACTIVE")){
//                System.out.println(occ+" is "+ "sleeping");
//                model.getKnowledgeGraph().removeTriples(occ + " is " + "active");
//                model.getImporter().importTripleLine(occ+" is "+"sleeping");
//                sleepProcedure(location, "");
//
//            }
//        }
//        model.findRoom(location," ");
//        tripleString = person + " "+"is_in"+" "+location;
//        i.importTripleLine("joe_smith"+ " " +"is_in" +" house1:kitchen1");
//        g.removeTriples(tripleString);
//        i.importTripleLine("joe_smith" + " "+"is_in"+" "+"house1:room2");
//        i.importTripleLine("joe_smith" + " "+"is"+" "+"active");
//        i.importTripleLine("Rover" + " "+"is_in"+" "+"house1:kitchen1");
//        g.removeTriples("joe_smith" + " "+"is"+" "+"active");
//        i.importTripleLine("joe_smith" + " "+"is_in"+" "+"sleeping");
//        q.executeQuery("joe_smith ? ?");
//    }
}

