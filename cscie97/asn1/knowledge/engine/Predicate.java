package cscie97.asn1.knowledge.engine;

/**
 * This is the Node class represents the instance of predicate
 * 
 * @author yingtian wang
 *
 */
public class Predicate {
	private String identifier;

	/**
	 * this constructs the Node and change it to lowercase for case insensitve
	 * search
	 * 
	 * @param identifier
	 */
	public Predicate(String identifier) {
		identifier = identifier.toLowerCase();
		this.identifier = identifier;
	}

	/**
	 * this methord return the identifier of predicate
	 * 
	 * @return
	 */

	public String getIdentifier() {
		return identifier;
	}
}
