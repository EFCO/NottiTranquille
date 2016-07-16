package it.ispw.efco.nottitranquille.controller;

import it.ispw.efco.nottitranquille.model.*;
import it.ispw.efco.nottitranquille.model.DAO.AccessDAO;
import it.ispw.efco.nottitranquille.model.DAO.StructureDAO;
import it.ispw.efco.nottitranquille.view.StructureBean;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by claudio on 7/10/16.
 */
public class ManageStructures {

    public static void addNewStructure(StructureBean structure, Person manager) throws Exception {

        Owner ownerInstance;
        Manager managerInstance = ((Manager) manager.getRole("Manager"));

        StructureDAO structureDAO = new StructureDAO();
        AccessDAO accessDAO = new AccessDAO();

        if (structure.isOwner()) {
            ownerInstance = (Owner) manager.getRole("Owner");
            if (ownerInstance != null) {
                structureDAO.store(structure, managerInstance, ownerInstance);
            } else {
                accessDAO.addOwnerRole(manager);
                manager = accessDAO.selectUserByEmail(manager.getEmail());
                structureDAO.store(structure, (Manager) manager.getRole("Manager"), (Owner) manager.getRole("Owner"));
            }

        } else {
            Person newOwner = new Person(structure.getOwnerFirstName(), structure.getOwnerLastName(), structure.getOwnerEmail());
            if (structure.isSameaddress()) {
                newOwner.setAddress(structure.getNation(), structure.getCity(), structure.getAddress(), structure.getPostalcode());
            } else {
                newOwner.setAddress(structure.getOwnerNation(), structure.getOwnerCity(), structure.getOwnerAddress(), structure.getOwnerPostalcode());
            }
            accessDAO.register(newOwner);
            accessDAO.addOwnerRole(newOwner);
            newOwner = accessDAO.selectUserByEmail(newOwner.getEmail());
            structureDAO.store(structure, (Manager) newOwner.getRole("Manager"), (Owner) newOwner.getRole("Owner"));


        }
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
            Object newvalue;
            if (field.equals("checkIn") || field.equals("checkOut")) {
                newvalue = DateTime.parse(value[0], DateTimeFormat.forPattern("dd-MM-yyyy"));
            } else {
                newvalue = value[0];
            }
            structureDAO.modifyField(field, newvalue, id);
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
