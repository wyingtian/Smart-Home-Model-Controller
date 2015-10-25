package cscie97.asn3.knowledge.engine;

/**
 * This is the triple class represents the instance of triple it has subject
 * predicate and object.
 * 
 * @author yingtian wang
 *
 */
public class Triple {
	private String identifier;
	private Node sub;
	private Predicate pre;
	private Node obj;

	/**
	 * This is the construct to for the triple
	 * 
	 * @param sub
	 * @param pre
	 * @param obj
	 */
	public Triple(Node sub, Predicate pre, Node obj) {
		this.sub = sub;
		this.pre = pre;
		this.obj = obj;

	}

	/**
	 * this is to get the Triple identifier string
	 * 
	 * @return
	 */
	public String getIdentifier() {
		identifier = sub.getIdentifier() + " " + pre.getIdentifier() + " "
				+ obj.getIdentifier();
		return identifier;
	}

	/**
	 * this is to get the subject of the triple string
	 * 
	 * @return
	 */
	public String getSubIdentifier() {
		return sub.getIdentifier();
	}

	/**
	 * this is to get the predicate of the triple string
	 * 
	 * @return
	 */
	public String getPreIdentifier() {
		return pre.getIdentifier();
	}

	/**
	 * this is to get the object of the triple string
	 * 
	 * @return
	 */
	public String getObjIdentifier() {
		return obj.getIdentifier();
	}
}