package it.ispw.efco.nottitranquille.controller;

import it.ispw.efco.nottitranquille.model.*;
import it.ispw.efco.nottitranquille.model.dao.StructureDao;
import it.ispw.efco.nottitranquille.view.StructureBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by claudio on 7/10/16.
 */
public class ManageStructures {

    public static void addNewStructure(StructureBean structure, Person manager) throws Exception {


        Structure newStructure;
        Person owner = null;
        Owner ownerInstance;
        Manager managerInstance = ((Manager) manager.getRole("Manager"));
        if (structure.isOwner()) {
            try {
                ownerInstance = ((Owner) manager.getRole("Owner"));
                newStructure = new Structure(structure, managerInstance, ownerInstance);
                managerInstance.addManagedStructure(newStructure);
                owner = manager;
            } catch (Exception e) {
                ownerInstance = new Owner();
                newStructure = new Structure(structure, managerInstance, ownerInstance);
                manager.addRole(ownerInstance);
            }
        } else {
            Person newOwner = new Person(structure.getOwnerFirstName(), structure.getOwnerLastName(), structure.getOwnerEmail());
            ownerInstance = new Owner();
            newStructure = new Structure(structure, managerInstance, ownerInstance);
            if (structure.isSameaddress()) {
                newOwner.setAddress(newStructure.getStructureAddress());
            } else {
                newOwner.setAddress(new Address(structure.getOwnerNation(), structure.getOwnerCity(), structure.getOwnerAddress(), structure.getOwnerPostalcode()));
            }
            newOwner.addRole(ownerInstance);
            owner = newOwner;
        }

        newStructure.addOwner(ownerInstance);
        Request newRequest = new Request(managerInstance);
        newStructure.setRequest(newRequest);
        StructureDAO structureDAO = new StructureDAO();
        structureDAO.store(newStructure, manager, owner);
    }

    public static void deleteStructure(List<Structure> structures, Long id) throws Exception {
        StructureDAO structureDAO = new StructureDAO();
        Structure structureDeleted = null;
        for (Structure structureToDelete : new ArrayList<Structure>(structures)) {
            if (structureToDelete.getId().equals(id)) {
                structureDeleted = structureToDelete;
                structureDAO.delete(structureToDelete);
            }
        }
        if (structureDeleted != null)
            structures.remove(structureDeleted);
    }

    public static void modifyField(String field, String[] value, Long id) {
        StructureDAO structureDAO = new StructureDAO();
        if (value.length == 1) {
            structureDAO.modifyField(field, value[0], id);
        } else {
            Address newAddress = new Address(value[1], value[2], value[0], value[3]);
            structureDAO.modifyAddress(newAddress, id);
        }
    }

    public static List<Structure> getAll(Manager manager) {
        return manager.getManagedStructures();
    }

    public static Structure getStructuredWithID(Long id) {
        StructureDAO structureDAO = new StructureDAO();
        return structureDAO.select(id);
    }
}
