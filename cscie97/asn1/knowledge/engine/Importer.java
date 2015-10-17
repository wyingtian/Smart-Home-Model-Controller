package cscie97.asn1.knowledge.engine;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This is a class to import the triple file It open the input file and store
 * the data to knowledge map class
 * 
 * @author yingtian wang
 *
 */
public class Importer {




	List<Triple> triple_list ;


	/**
	 *
	 * @param modifiedLine
	 */
	public   void importTripleLine(String modifiedLine){
		// split by space
		String[] str_array = modifiedLine.split(" ");

		List<Triple> triple_list = new ArrayList<>();

		try {
			if (modifiedLine.length() > 0 && str_array.length == 3) {
				Node sub = KnowledgeGraph.getInstance().getNode(
						str_array[0]);
				Predicate pre = KnowledgeGraph.getInstance()
						.getPredicate(str_array[1]);
				Node obj = KnowledgeGraph.getInstance().getNode(
						str_array[2]);
				Triple tri = KnowledgeGraph.getInstance().getTriple(
						sub, pre, obj);
				triple_list.add(tri);

				// call graph's import method to import triples

				KnowledgeGraph.getInstance().importTriples(triple_list);
			} else
				throw new ImportException(modifiedLine);
		} catch (ImportException e) {

		}
	}

	/**
	 * This method get the input file name from command line It import the data
	 * to knowledge graph If the the input has an empty line, it will show there
	 * is an empty line If the input has more than three part, it will notify
	 * the user and then keep processing If the input has other error it will
	 * throw the exception and stop the program.
	 *
	 *
	 * @param fileName
	 * @throws ImportException
	 */
	public  void importTripleFile(String fileName) throws ImportException {

		String line;
		String modifiedLine;
		BufferedReader br = null;

		try {
			FileReader fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			while ((line = br.readLine()) != null) {
				// replace "." with whitespace and then get rid of all the
				// whitespace in the end of the line.
				modifiedLine = line.replace(".", " ").trim();
				// empty line consider as not major problem so continue
				if (modifiedLine.length() == 0) {
					System.out.println("************************");
					System.out.println("There is an empty line in the input file!");
					System.out.println();

					continue;
				}
				importTripleLine(modifiedLine);
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
