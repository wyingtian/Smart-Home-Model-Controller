package cscie97.asn1.test;

import cscie97.asn1.knowledge.engine.*;

/**
 * This is the test class for the knowledge graph it gets the name of the input
 * file and query file from commandline
 * 
 * @author yingtian wang
 *
 */

public class TestDriver {
	/**
	 * This is the main function for showing the query result.
	 * @param args
	 */

	public static void main(String[] args) {

		try {
			Importer.importTripleFile("./resource/"+args[0]);
		} catch (ImportException e) {
			System.out.println("illegal input!");
			System.out.println(e);
			System.exit(1);
		}

		try {
			QueryEngine.executeQueryFile("./resource/"+ args[1]);
		} catch (QueryEngineException e) {
			System.out.println("illegal query!");
			System.out.println(e);
			System.exit(1);
		}
	}
}
