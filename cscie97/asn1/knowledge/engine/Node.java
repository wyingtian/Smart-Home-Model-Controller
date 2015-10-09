package cscie97.asn1.knowledge.engine;

/**
 * This is the Node class represents the instance of subjects and objects
 * 
 * @author yingtian wang
 *
 */
public class Node {
	private String identifier;

	/**
	 * this constructs the Node and change it to lowercase for case insensitve
	 * search
	 * 
	 * @param identifier
	 */
	public Node(String identifier) {
		identifier = identifier.toLowerCase();
		this.identifier = identifier;
	}

	/**
	 * this methord return the identifier of node
	 * 
	 * @return
	 */
	public String getIdentifier() {
		return identifier;
	}

}
