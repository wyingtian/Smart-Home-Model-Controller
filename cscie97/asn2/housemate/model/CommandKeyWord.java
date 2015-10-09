package cscie97.asn2.housemate.model;

/**
 * enum type of the command keyword
 * 
 * @author ying
 *
 */
public enum CommandKeyWord {
	DEFINE("define"), ADD("add"), SET("set"), SHOW("show");

	private String CommkeyWord;

	private CommandKeyWord(String value) {
		CommkeyWord = value;
	}

	public String getKeyWord() {
		return CommkeyWord;
	}

	/**
	 * check if the command has the keyword
	 * @param test
	 * @return
	 */
	public static boolean contains(String test) {

		for (CommandKeyWord c : CommandKeyWord.values()) {
			if (c.getKeyWord().equals(test)) {
				return true;
			}
		}

		return false;
	}

}
