package cscie97.asn3.housemate.controller;

import cscie97.asn1.knowledge.engine.KnowledgeGraph;
import cscie97.asn1.knowledge.engine.Importer;
import cscie97.asn1.knowledge.engine.QueryEngine;
import cscie97.asn3.housemate.controller.command.*;
import cscie97.asn3.housemate.model.*;
import cscie97.asn3.housemate.model.IOTDevices.*;
import cscie97.asn3.housemate.model.exception.SensorNotFoundException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Created by ying on 10/10/15.
 */
public class HouseMateController {

    private static final HouseMateController controller = new HouseMateController();
    ServiceInterface model = HouseMateModel.getInstance();
    KnowledgeGraph g = KnowledgeGraph.getInstance();
    Importer i = new Importer();
    QueryEngine q = new QueryEngine();

    StringBuilder line;

    private HouseMateController(){

    }

    public static HouseMateController getInstance(){
        return controller;
    }

    public String getAvaCommand(String []tokens){
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
    public void executeAvaCommand(String stimulus, Ava ava){
        String[] tokens = stimulus.split(" ");
        if(stimulus.equals("lights on")){
            ava.getLocationPair();
            turnOnAllLights(findApplianceByType(ava.getLocationPair(), "light", ""));
        }else if(stimulus.equals("open door")){
            openDoors(findApplianceByType(ava.getLocationPair(), "door", ""));
        }else if(tokens.length == 3 && tokens[0].equals("where") && tokens[1].equals("is")){
            q.executeQuery(tokens[2]+" is_in "+"?");
        }else if(tokens.length == 3 && HouseMateModel.isApplianceType(tokens[0])){
            List<Appliance> list = findApplianceByType(ava.getLocationPair(),tokens[0] , "");
            for(Appliance app:list){
                app.changeStatus(tokens[1],tokens[2]);
                app.showStatus(tokens[1]);
            }
        }else if(tokens.length == 3 && (tokens[0].equals("?")||tokens[1].equals("?")||tokens[2].equals("?"))){
            q.executeQuery(stimulus);
        }
    }
    /**
     *
     * @param location location is in the form of house:room
     * @param type      type is the appliance type String
     * @return  a list of matching appliance.
     */
    public List<Appliance> findApplianceByType(String location, String type, String auth_token){
        List<Appliance> appList = new ArrayList<Appliance>();
        Room theRoom = model.findRoom(location,auth_token);
        for(Appliance app: theRoom.getApplianceMap().values()){
            if(app.getType().equals(type)){
                appList.add(app);
            }
        }
        return appList;
    }

    public void openDoors(List<Appliance> list){
        if(list.isEmpty()){
            System.out.println("no doors in this room");
            return;
        }
        for(Appliance app: list){
            DoorOpenCommand com = new DoorOpenCommand((Door)app);
            com.execute();
        }
    }
    public void turnOnAllLights(List<Appliance> list){
        if(list.isEmpty()){
            System.out.println("no lights in this room");
            return;
        }
        for(Appliance app: list){
            LightOnCommand com = new LightOnCommand((Light)app);
            com.execute();
        }
    }
    public void turnOffAllLights(List<Appliance> list){
        if(list.isEmpty()){
            System.out.println("no lights in this room");
            return;
        }
        for(Appliance app: list){
            LightOffCommand com = new LightOffCommand((Light)app);
            com.execute();
        }
    }
    public void dimmerAllLights(List<Appliance> list){
        if(list.isEmpty()){
            System.out.println("no lights in this room");
            return;
        }
        for(Appliance app: list){
            LightDimmerCommand com = new LightDimmerCommand((Light)app);
            com.execute();
            app.showStatus("intensity");
        }
    }
    public void coolerThermostat(List<Appliance> list){
        if(list.isEmpty()){
            System.out.println("no thermostat in this room");
            return;
        }
        for(Appliance app: list){
            ThermostatCoolerCommand com = new ThermostatCoolerCommand((Thermostat)app);
            com.execute();
            app.showStatus("temperature");
        }
    }
    public void warmerThermostat(List<Appliance> list){
        if(list.isEmpty()){
            System.out.println("no thermostat in this room");
            return;
        }
        for(Appliance app: list){
            ThermostatWarmerCommand com = new ThermostatWarmerCommand((Thermostat)app);
            com.execute();
            app.showStatus("temperature");
        }
    }
    /**
     * set the status of the sensor or appliance
     * @param tokens the String[] is the tokenized command
     * @param auth_token for access control
     */

    public void setSensor(String[] tokens, String auth_token) {
        Sensor theSensor;
        String sensorType;
        theSensor = model.findSensor(tokens[2], auth_token);
        if(theSensor != null ){
        sensorType = theSensor.getType();
            if (sensorType.equals("Ava") ) {
            executeAvaCommand(getAvaCommand(tokens),(Ava)theSensor);
            // System.out.println(theSensor.showStatus());
            } else if(sensorType.equals("camera")){
            executeCameraCommand(tokens[4],tokens[6],(Camera)theSensor);
            }
        }else{
            try {
                throw new SensorNotFoundException(tokens[2]+ " is not Found" );
            } catch (SensorNotFoundException e) {
            }
        }
    }


    public void sleepProcedure(String roomLocationPair,String auth_token){
        dimmerAllLights(findApplianceByType(roomLocationPair, "light", auth_token));
    }
    public void detectedProcedure(String roomLocationPair,String auth_token){
        turnOnAllLights(findApplianceByType(roomLocationPair, "light", auth_token));
        warmerThermostat(findApplianceByType(roomLocationPair, "thermostat", auth_token));
    }
    public void leavingProcedure(String roomLocationPair,String auth_token){
        turnOffAllLights(findApplianceByType(roomLocationPair, "light", auth_token));
        coolerThermostat(findApplianceByType(roomLocationPair, "thermostat", auth_token));
    }
    public void executeCameraCommand(String occStatus,String occ,Camera cam){
        String location = cam.getLocationPair();
        String tripleString = occ+" is_in "+location;

        if(!model.getAllOccupantMap().containsKey(occ)){
            System.out.println("Unknown person " + occ +" detected");
        }else{
            if(occStatus.equals("OCCUPANT_DETECTED")){
                System.out.println(occ+" entered "+ location);
                i.importTripleLine(tripleString);
                detectedProcedure(location, "");
            }else if(occStatus.equals("OCCUPANT_LEAVING")){
                System.out.println(occ+" left "+ location);
                g.removeTriples(tripleString);
                leavingProcedure(location,"");
            }else if(occStatus.equals("OCCUPANT_ACTIVE")){
                System.out.println(occ+" is "+ "active");
                g.removeTriples(occ + " is " + "sleeping");
                i.importTripleLine(occ + " is " + "active");
            }else if(occStatus.equals("OCCUPANT_INACTIVE")){
                System.out.println(occ+" is "+ "sleeping");
                g.removeTriples(occ + " is " + "active");
                i.importTripleLine(occ+" is "+"sleeping");
                sleepProcedure(location, "");

            }
        }
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
}
