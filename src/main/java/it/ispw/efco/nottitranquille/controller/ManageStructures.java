package it.ispw.efco.nottitranquille.controller;

import it.ispw.efco.nottitranquille.model.*;
import it.ispw.efco.nottitranquille.view.StructureBean;

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
                newStructure = new Structure(structure, (Manager) manager.getRole("Manager"), ownerInstance);
                ((Manager) manager.getRole("Manager")).addManagedStructure(newStructure);
                owner = manager;
            } catch (Exception e) {
                ownerInstance = new Owner();
                newStructure = new Structure(structure, ((Manager) manager.getRole("Manager")), ownerInstance);
                manager.addRole(ownerInstance);
            }
        } else {
            Person newOwner = new Person(structure.getOwnerFirstName(), structure.getOwnerLastName(), structure.getOwnerEmail());
            ownerInstance = new Owner();
            newStructure = new Structure(structure, ((Manager) manager.getRole("Manager")), ownerInstance);
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

    public static void deleteStructure(Long id, Person manager) throws Exception {
        StructureDAO structureDAO = new StructureDAO();
        Structure structure = structureDAO.select(id);
        structure.removeOwners();
        structure.removeManagedBy();
        structure.getRequest().removeRequestedBy();

        structureDAO.delete(structure);
    }

    public static List<Structure> getAll(Manager manager) {
        return manager.getManagedStructures();
    }

}
