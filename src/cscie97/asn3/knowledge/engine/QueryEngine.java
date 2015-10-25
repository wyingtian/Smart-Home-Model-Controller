package cscie97.asn3.knowledge.engine;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;

/**
 * This query engine is responsible for get the quries from file and execute
 * them
 * 
 * @author yingtian wang
 *
 */
public class QueryEngine {
	/**
	 * This method is used to process a single query It is case insensitive, so
	 * the differente case of the query will consider as the same query
	 * 
	 * @param query
	 */
	public  void executeQuery(String query) {

		// to separate the results of each query.
		System.out.println("************************");
		System.out.println("input query:");
		System.out.println(query + ".");

		// convert query to Triple
		query = query.toLowerCase();
		String[] tri = query.split(" ");
		Node sub = KnowledgeGraph.getInstance().getNode(tri[0]);
		Predicate pre = KnowledgeGraph.getInstance().getPredicate(tri[1]);
		Node obj = KnowledgeGraph.getInstance().getNode(tri[2]);
		Triple query_triple = new Triple(sub, pre, obj);

		// if the query is same as one of the triple, print this triple
		if (KnowledgeGraph.getInstance().tripleMap.containsKey(query)) {
			System.out.println("output:");
			System.out.println(stdOutput(query) + ".");
		}

		// if query is "? ? ?" print all triple from tripleMap
		else if (query.equals("? ? ?")) {
			System.out.println("output:");
			for (String triple : KnowledgeGraph.getInstance().tripleMap
					.keySet()) {
				System.out.println(stdOutput(triple) + ".");
			}
		} else {

			// get the Set of the statement of the query
			Set<Triple> res = KnowledgeGraph.getInstance().executeQuery(
					query_triple);

			// if the Set is empty, means no acknowledgement for the query
			if (res == null || res.isEmpty()  ) {
				System.out.println("output:");
				System.out.println("<null>");
			} else {
				System.out.println("output:");
				// print all the acknowledgement for the query
				for (Triple trp : res) {
					System.out.println(stdOutput(trp.getIdentifier()) + ".");
				}
			}
		}
	}

	/**
	 * This is to make output Subject and Object first letter upper case.
	 * 
	 * @param input
	 * @return
	 */
	public  String stdOutput(String input) {
		String[] temp = input.split(" ");
		String sub = temp[0].substring(0, 1).toUpperCase()
				+ temp[0].substring(1);
		String obj = temp[2].substring(0, 1).toUpperCase()
				+ temp[2].substring(1);
		String res = sub + " " + temp[1] + " " + obj;
		return res;
	}

	/**
	 * This method get the query file and then execute them one by one
	 * 
	 * @param fileName
	 * @throws QueryEngineException
	 */
	public  void executeQueryFile(String fileName)
			throws QueryEngineException {

		BufferedReader br = null;
		String line;
		String modifiedLine;
		try {
			FileReader fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			while ((line = br.readLine()) != null) {
				modifiedLine = line.replace(".", " ").trim();
				String[] str_array = modifiedLine.split(" ");

				// empty line consider as not major problem so continue
				if (modifiedLine.length() == 0)
					continue;
				try {
					if (modifiedLine.length() > 0 && str_array.length == 3) {
						// replace "." with whitespace and then get rid of all
						// the whitespace in the end of the line.
						modifiedLine = line.replace(".", " ").trim();
						executeQuery(modifiedLine);
					} else
						throw new QueryEngineException(line);
				} catch (QueryEngineException e) {

				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("!!  File not found: " + fileName);
		} catch (IOException e) {
			System.out.println("!!  Unable to read file: " + fileName);
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				System.out.println("!!  Unable to close file: " + fileName);
			} catch (NullPointerException e) {
				System.out.println("!!  NullPointExcepition" + fileName);
			}
		}
	}
}
