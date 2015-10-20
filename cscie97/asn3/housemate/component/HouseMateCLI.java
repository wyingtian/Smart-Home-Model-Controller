package cscie97.asn3.housemate.component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * This is to provide command line interface and file input.
 * 
 * @author ying
 *
 */
public class HouseMateCLI {
	/**
	 * method for providing command line interface
	 */
	public static void comLineInterface() {
		Scanner scanner = null;
		String auth_token = "";
		try {
			scanner = new Scanner(System.in);
			// rest of the code
			String s = "";
			System.out.println("****************************************");
			System.out.println("Welcome to House Mate command line interface");
			System.out.println("Please enter your command. Enter \"exit\" to terminate");

			while (!(s = scanner.nextLine()).equals("exit")) {
				executeCommand(s, auth_token);
				System.out.println("Please enter your command");
			}
		} finally {
			if (scanner != null)
				scanner.close();
		}
	}

	/**
	 * method for input file
	 *
	 * @param fileName
	 */
	public static void importConfigFile(String fileName) throws IOException {
		int commandCount = 1;
		BufferedReader br = null;
		String line;
		String modifiedLine;
		String auth_token = "";
		try {
			FileReader fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			while ((line = br.readLine()) != null) {
				if (line.indexOf('#') != -1) {
					continue;
				}
				// replace "." with whitespace and then get rid of all the
				// whitespace in the end of the line.
				modifiedLine = line.trim();
				if (modifiedLine.length() == 0)
					continue;
				System.out
						.println("******************************************");
				System.out.print(commandCount + ".  ");
				executeCommand(modifiedLine, auth_token);
				commandCount++;
			}

		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + fileName);
		} catch (IOException e) {
			System.out.println("Unable to read file: " + fileName);
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				System.out.println("Unable to close file: " + fileName);
			} catch (NullPointerException e) {
				System.out.println("NullPointExcepition");
			}
		}

	}

	/**
	 * method for taking a command, execute if passed the general format test
	 *
	 * @param command
	 * @param auth_token
	 */
	public static void executeCommand(String command, String auth_token) {
		String checked[];
		checked = checkCommand(command);
		if (checked != null) {
			exeCheckedCommand(checked, auth_token);
		} else {
			System.err.println("Invalid format");
		}
	}

	/**
	 * method for basic format check of the command
	 *
	 * @param command
	 * @return tokenized command
	 */
	public static String[] checkCommand(String command) {
		// show the input String command
		System.out.println("");
		System.out.println("input command: " + command);
		System.out.println("*********************************************");

		command = command.trim();
		String[] tokens;
		tokens = command.split("\\s+");

		if (CommandKeyWord.contains(tokens[0]) ) {
			return tokens;
		} else
			return null;
	}

	/**
	 * execute the command
	 *
	 * @param tokens
	 */
	public static void exeCheckedCommand(String[] tokens, String auth_token) {

		if (tokens[0].equals("define") && tokens[1].equals("house")) {
			if (tokens.length < 3) {
				System.err.println("Please add the name of the house");
			} else if (tokens.length > 3) {
				System.err.println("The name should not contain space");
			} else {
				HouseMateModelFactory.getInstance().defineHouse(tokens[2], auth_token);
			}
		}

		// This is for the command
		// "define room <room_name> floor <floor> type<room_type> house <house_name>"

		else if (tokens[0].equals("define") && tokens[1].equals("room")) {

			if (tokens.length == 9 && tokens[3].equals("floor")
					&& tokens[5].equals("type") && tokens[7].equals("house")){
				HouseMateModel.getInstance().defineRoom(tokens[2],tokens[4],tokens[6],tokens[8], auth_token);
			}else {
				System.err
						.println("invalid input, Please try to define the room again");
			}
		}
		// This is for the command
		// "define occupants <occupant_name> type <occupant_type>"
		else if (tokens[0].equals("define") && tokens[1].equals("occupant")) {
			// check the format of the input, the length has to be 3;
			if (tokens.length < 5) {
				System.err.println("Please add the all the info of the occupant");
			} else if (tokens.length > 5) {
				System.err
						.println("Please define the name and type of the occupant");
			} else {
				HouseMateModel.getInstance().defineOccupant(tokens[2],tokens[4], auth_token);
			}
		}
		// This is for the command
		// "add occupant <occupant_name> to_house <house_name>"
		else if (tokens[0].equals("add") && tokens[1].equals("occupant")
				&& tokens[3].equals("to_house")) {
			HouseMateModel.getInstance().addOccupant2House(tokens[2], tokens[4], auth_token);
		}

		// This is for the command
		// "define sensor <name> type <sensor_type> room <house_name>:<room_name>"
		else if (tokens[0].equals("define") && tokens[1].equals("sensor")) {
			// if the length is not valid
			if (tokens.length < 7) {
				System.err.println("Need more info to define sensor");
			} else if (tokens.length > 7) {
				System.err.println("Please check your define sensor format");
			}else {
				HouseMateModel.getInstance().defineSensor(tokens[2],tokens[4],tokens[6], auth_token);
			}
		}
		// This is for the command
		// "define appliance <name> type <appliance_type> room <house_name>:<room_name>"
		else if (tokens[0].equals("define") && tokens[1].equals("appliance")) {
			// if the length is not valid
			if (tokens.length < 7) {
				System.err.println("Need more info to define appliance");
			} else if (tokens.length > 7) {
				System.err
						.println("Please check your define appliance statement format");
			} else{
				HouseMateModel.getInstance().defineAppliance(tokens[2],tokens[4],tokens[6], auth_token);
			}
		}
		// This is the command for set appliance house_name:room_name name

		else if (tokens[0].equals("set")
				&& (tokens[1].equals("sensor") )
				) {
			HouseMateModel.getInstance().setSensor(tokens[2],tokens[4],tokens[6],tokens, auth_token);
		} else if (tokens[0].equals("set")
				&& (tokens[1].equals("appliance"))
				) {
			HouseMateModel.getInstance().setApplianceStatus(tokens[2],tokens[4],tokens[6], auth_token);
		}
		else if (tokens[0].equals("show")
				&& (tokens[1].equals("sensor") )
				&& (tokens.length > 2 && tokens.length < 6)) {
			HouseMateModel.getInstance().showSensor(tokens[2], auth_token);
		}else if (tokens[0].equals("show")
				&& tokens[1].equals("appliance")
				&& tokens.length == 5) {
			HouseMateModel.getInstance().showApplianceStatus(tokens[2], tokens[4], auth_token);
		}else if (tokens[0].equals("show")
				&& tokens[1].equals("appliance")
				&& (tokens.length == 3)) {
			HouseMateModel.getInstance().showAllApplianceStatus(tokens[2], auth_token);
		}
		else if (tokens.length == 4 && tokens[0].equals("show")
				&& tokens[1].equals("configuration")
				&& tokens[2].equals("house")) {
			HouseMateModel.getInstance().showConfigHouse(tokens[3], auth_token);
		} else if (tokens.length == 4 && tokens[0].equals("show")
				&& tokens[1].equals("configuration")
				&& tokens[2].equals("room")) {
			HouseMateModel.getInstance().showConfigRoom(tokens[3], auth_token);
		} else if (tokens.length == 2 && tokens[0].equals("show")
				&& tokens[1].equals("configuration")) {
			HouseMateModel.getInstance().showConfigAllHouse(auth_token);
		} else {
			System.err.println("invalid command input");
		}

	}
}
