package it.ispw.efco.nottitranquille.controller;

import it.ispw.efco.nottitranquille.model.Person;
import it.ispw.efco.nottitranquille.model.Request;
import it.ispw.efco.nottitranquille.model.Structure;
import it.ispw.efco.nottitranquille.view.StructureBean;

/**
 * Created by claudio on 7/10/16.
 */
public class ManageStructures {

    public void addNewStructure(StructureBean structure, Person manager) {
        Structure newStructure = new Structure(structure, manager);

        Request request = new Request(newStructure);
        structure.setRequest(request);
    }

}
