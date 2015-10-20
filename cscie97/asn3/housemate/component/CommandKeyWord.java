package cscie97.asn3.housemate.component;

/**
 * enum type of the command keyword
 * 
 * @author ying
 *
 */
public enum CommandKeyWord {
	DEFINE("define"), ADD("add"), SET("set"), SHOW("show");

	private String ComKeyWord;

	private CommandKeyWord(String value) {
		ComKeyWord = value;
	}

	public String getKeyWord() {
		return ComKeyWord;
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
