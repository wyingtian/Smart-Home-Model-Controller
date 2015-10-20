package cscie97.asn3.housemate.component;


import cscie97.asn3.housemate.component.HouseMateController;

/**
 * Factory for accessing the HouseMateController singleton instance
 * @author ying
 */
public class HouseMateControllerFactory {
    public static HouseMateController getInstance(){
        return HouseMateController.getInstance();
    }
}
