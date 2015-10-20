package cscie97.asn3.housemate.command;

import cscie97.asn3.housemate.component.HouseMateModelFactory;
import cscie97.asn3.housemate.model.IOTDevices.Ava;
import cscie97.asn3.housemate.model.IOTDevices.Refrigerator;
import cscie97.asn3.housemate.model.IOTDevices.Sensor;
import cscie97.asn3.housemate.component.ServiceInterface;

import java.util.Scanner;

/**
 * the command for when the beer count is low
 * @author ying
 */
public class BeerCountLowCommand implements Command {
    Refrigerator refrigerator;
    ServiceInterface model;

    public BeerCountLowCommand(Refrigerator refrigerator){
        this.refrigerator = refrigerator;
        model = HouseMateModelFactory.getInstance();
    }
    @Override
    public void execute() {
        System.out.println("beer Count has changed");
        avaInRoomSpeak(refrigerator.getLocationPair(), "Would you like more beer?", "");
        beerRequestPrompt();
    }

    /**
     * use the ava in the room that same as the room the fridge is in to speak
     * @param avaLocation
     * @param broadCastMessage
     * @param authToken
     */
    public void avaInRoomSpeak(String avaLocation,String broadCastMessage,String authToken){
        for(Sensor sen :model.findSensorInRoom(avaLocation, "Ava", authToken)) {
            ((Ava)sen).speak(broadCastMessage);
        };

    }

    /**
     * ask if the occupant want more beer
     */
    public void beerRequestPrompt(){
        Scanner in = new Scanner(System.in);
        String s;
        System.out.println("Enter yes or no");
        s = in.nextLine();
        if(s.equals("yes")){
            System.out.println("Order email has been sent");

        }else if(s.equals("no")){
            System.out.println("Ok, no beer for you :(");
        }else beerRequestPrompt();
    }
}
