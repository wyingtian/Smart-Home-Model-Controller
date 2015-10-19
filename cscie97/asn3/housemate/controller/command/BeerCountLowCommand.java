package cscie97.asn3.housemate.controller.command;

import cscie97.asn3.housemate.model.HouseMateModelFactory;
import cscie97.asn3.housemate.model.IOTDevices.Refrigerator;
import cscie97.asn3.housemate.model.ServiceInterface;

import java.util.Scanner;

/**
 * Created by ying on 10/19/15.
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
        model.avaInRoomSpeak(refrigerator.getLocationPair(), "Would you like more beer?", "");
        beerRequestPrompt();
    }


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
