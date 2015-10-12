package cscie97.asn3.housemate.controller;

import cscie97.asn1.knowledge.engine.KnowledgeGraph;
import cscie97.asn1.knowledge.engine.Importer;
import cscie97.asn1.knowledge.engine.QueryEngine;
import cscie97.asn3.housemate.model.HouseMateCLI;
import cscie97.asn3.housemate.model.HouseMateModel;
import cscie97.asn3.housemate.model.Occupants.Occupant;
import cscie97.asn3.housemate.model.ServiceInterface;

import java.io.FileNotFoundException;

/**
 * Created by ying on 10/10/15.
 */
public class HouseMateController {
    ServiceInterface model = HouseMateModel.getInstance();
    KnowledgeGraph g = KnowledgeGraph.getInstance();
    Importer i = new Importer();
    QueryEngine q = new QueryEngine();



    public static void main(String args[]){

        HouseMateCLI.comLineInterface();

        HouseMateController c = new HouseMateController();
        c.cameraCommand();
    }


    public  void cameraCommand(){
        String person = "joe_smith";
        String location = "house1:kitchen1";
        String tripleString ;
        if(model.getAllOccupantMap().containsKey(person)){

            Occupant occ = model.getAllOccupantMap().get("person");
            model.findRoom(location," ");
            tripleString = person + " "+"is_in"+" "+location;
            i.importTripleLine(tripleString);
        }
        q.executeQuery("? is_in ?");
    }
}
