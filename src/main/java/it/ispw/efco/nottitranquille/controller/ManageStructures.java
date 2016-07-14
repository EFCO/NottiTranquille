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
        Manager managerInstance = ((Manager) manager.getRole("Manager"));
        if (structure.isOwner()) {
            try {
                newStructure = new Structure(structure, (Manager) manager.getRole("Manager"), ((Owner) manager.getRole("Owner")));
                ((Manager) manager.getRole("Manager")).addManagedStructure(newStructure);
                ((Owner) manager.getRole("Owner")).addOwnedStructure(newStructure);
            } catch (Exception e) {
                Owner ownerInstance = new Owner();
                newStructure = new Structure(structure, ((Manager) manager.getRole("Manager")), ownerInstance);
                ownerInstance.addOwnedStructure(newStructure);
                ((Manager) manager.getRole("Manager")).addManagedStructure(newStructure);
                manager.addRole(ownerInstance);
            }
        } else {
            Person newOwner = new Person(structure.getOwnerFirstName(), structure.getOwnerLastName(), structure.getOwnerEmail());
            Owner ownerInstance = new Owner();
            newStructure = new Structure(structure, ((Manager) manager.getRole("Manager")), ownerInstance);
            if (structure.isSameaddress()) {
                newOwner.setAddress(newStructure.getStructureAddress());
            } else {
                newOwner.setAddress(new Address(structure.getOwnerNation(), structure.getOwnerCity(), structure.getOwnerAddress(), structure.getOwnerPostalcode()));
            }
            ownerInstance.addOwnedStructure(newStructure);
            ((Manager) manager.getRole("Manager")).addManagedStructure(newStructure);
            newOwner.addRole(ownerInstance);
            owner = newOwner;
        }

        Request newRequest = new Request(newStructure, managerInstance);
        newStructure.setRequest(newRequest);
        ((Manager) manager.getRole("Manager")).addRequest(newRequest);
        StructureDAO structureDAO = new StructureDAO();
        structureDAO.store(newStructure, manager, owner);
    }

    public static List<Structure> getAll(Manager manager) {
        return manager.getManagedStructures();
    }

}
