package cscie97.asn3.knowledge.engine;

/**
 * This is the ImportException class for illegal import entry
 * 
 * @author yingtian wang
 *
 */
public class ImportException extends Exception {
	public ImportException() {
		super();
	}

	public ImportException(String s) {
		super(s);
		System.out.println("************************");
		System.out.println("Bad input :"+ s);
		System.out.println();

	}
}