package cscie97.asn3.housemate.test;

import cscie97.asn3.housemate.controller.HouseMateControllerFactory;
import cscie97.asn3.housemate.model.HouseMateCLI;
import cscie97.asn3.housemate.model.HouseMateModelFactory;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by ying on 10/25/15.
 */
public class TestDriver {

    public static void main(String args[]){
        String inputName;
        if (args.length == 0){
            System.out.println("Please add configuration file name,\n" +
                    "For example: \n  javac -cp . cscie97.asn3.housemate.test.TestDriver avatest1.txt ");
        }else {
            inputName = args[0];
            try {
                HouseMateCLI.importConfigFile(inputName);
                System.out.println("\n\n **********Test Knowledge Graph ***********\n\n");
                System.out.println("****\"? is_in ? \"to find out all the location of occupants***\n");
                HouseMateModelFactory.getInstance().getQueryEngine().executeQuery("? is_in ?");
                System.out.println("\n***\"? is ? \"to find out all the status of occupants****\n");
                HouseMateModelFactory.getInstance().getQueryEngine().executeQuery("? is ?");
            } catch (FileNotFoundException e) {
                System.out.println("File not found: " + inputName);
            } catch (IOException e) {
                System.out.println("Unable to read file: " + inputName);
            }
        }
    }
}
