package cscie97.asn3.housemate.controller;


import cscie97.asn3.housemate.controller.HouseMateController;

/**
 * Factory for accessing the HouseMateController singleton instance
 * @author ying
 */
public class HouseMateControllerFactory {
    public static HouseMateController getInstance(){
        return HouseMateController.getInstance();
    }
}
