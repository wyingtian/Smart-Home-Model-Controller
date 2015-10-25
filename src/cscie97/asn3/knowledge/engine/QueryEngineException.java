package cscie97.asn3.knowledge.engine;

/**
 * This is the QueryEngineException class for illagal query entry
 * 
 * @author yingtian wang
 *
 */
public class QueryEngineException extends Exception {
	public QueryEngineException() {
		super();
	}

	public QueryEngineException(String s) {
		super(s);
		System.out.println("************************");
		System.out.println("Bad query :" + s);
		System.out.println();

	}
}