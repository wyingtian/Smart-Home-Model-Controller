package cscie97.asn2.housemate.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * This is to proviede command line interface and file input.
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

		if (CommandKeyWord.contains(tokens[0]) && tokens.length < 10) {
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
			HouseMateModel.getInstance().defineHouse(tokens, auth_token);
		}

		// This is for the command
		// "define room <room_name> floor <floor> type<room_type> house <house_name>"

		else if (tokens[0].equals("define") && tokens[1].equals("room")) {
			HouseMateModel.getInstance().defineRoom(tokens, auth_token);
		}
		// This is for the command
		// "define occupants <occupant_name> type <occupant_type>"
		else if (tokens[0].equals("define") && tokens[1].equals("occupant")) {
			HouseMateModel.getInstance().defineOccupant(tokens, auth_token);
		}
		// This is for the command
		// "add occupant <occupant_name> to_house <house_name>"
		else if (tokens[0].equals("add") && tokens[1].equals("occupant")
				&& tokens[3].equals("to_house")) {
			HouseMateModel.getInstance().addOccupant2House(tokens, auth_token);
		}

		// This is for the command
		// "define sensor <name> type <sensor_type> room <house_name>:<room_name>"
		else if (tokens[0].equals("define") && tokens[1].equals("sensor")) {
			HouseMateModel.getInstance().defineSensor(tokens, auth_token);
		}
		// This is for the command
		// "define appliance <name> type <appliance_type> room <house_name>:<room_name>"
		else if (tokens[0].equals("define") && tokens[1].equals("appliance")) {
			HouseMateModel.getInstance().defineAppliance(tokens, auth_token);
		}
		// This is the command for set appliance house_name:room_name name

		else if (tokens[0].equals("set")
				&& (tokens[1].equals("sensor") || tokens[1].equals("appliance"))
				&& (tokens.length == 3 || tokens.length == 7)) {
			HouseMateModel.getInstance().setSenOrApp(tokens, auth_token);
		} else if (tokens[0].equals("show")
				&& (tokens[1].equals("sensor") || tokens[1].equals("appliance"))
				&& (tokens.length > 2 && tokens.length < 6)) {
			HouseMateModel.getInstance().showSenOrApp(tokens, auth_token);
		} else if (tokens.length == 4 && tokens[0].equals("show")
				&& tokens[1].equals("configuration")
				&& tokens[2].equals("house")) {
			HouseMateModel.getInstance().showConfigHouse(tokens, auth_token);
		} else if (tokens.length == 4 && tokens[0].equals("show")
				&& tokens[1].equals("configuration")
				&& tokens[2].equals("room")) {
			HouseMateModel.getInstance().showConfigRoom(tokens, auth_token);
		} else if (tokens.length == 2 && tokens[0].equals("show")
				&& tokens[1].equals("configuration")) {
			HouseMateModel.getInstance().showConfigAllHouse(auth_token);
		} else {
			System.err.println("invalid command input");
		}

	}
}
