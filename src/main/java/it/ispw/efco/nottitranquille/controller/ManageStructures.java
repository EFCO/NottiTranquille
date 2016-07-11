package it.ispw.efco.nottitranquille.controller;

import it.ispw.efco.nottitranquille.model.*;
import it.ispw.efco.nottitranquille.view.StructureBean;

import java.util.List;

/**
 * Created by claudio on 7/10/16.
 */
public class ManageStructures {

    public static void addNewStructure(StructureBean structure, Person manager) throws Exception {


        Request request = null;
        Manager managerInstance = null;
        try {
            managerInstance = (Manager) manager.getRole("Manager");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Structure newStructure = new Structure(structure, managerInstance, new Owner()); //TODO check if manager is the owner
        request = new Request(newStructure, managerInstance);
        newStructure.setRequest(request);
//        RequestDAO requestDAO = new RequestDAO();
//        requestDAO.saveRequest(request);
        StructureDAO structureDAO = new StructureDAO();
        structureDAO.store(newStructure, manager.getId());
    }

    public static List<Structure> getAll(Applicant manager) {
        return ((Manager) manager).getStructures();
    }

}
