package cscie97.asn2.housemate.test;

import java.io.FileNotFoundException;
import java.io.IOException;

import cscie97.asn2.housemate.model.HouseMateCLI;

/**
 * This is a test class
 * <p>
 * The test first import the commands from housesetup.txt and then enter
 * command line interface, for user to enter command enter "exit" to end the
 * command prompt.
 * </p>
 * 
 * @author ying
 */
public class TestDriver {
	public static void main(String[] args) {
		String inputName = args[0];
		try {
			HouseMateCLI.importConfigFile(inputName);
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + inputName);
		} catch (IOException e) {
			System.out.println("Unable to read file: "+inputName);
		}
		HouseMateCLI.comLineInterface();
	}
}
