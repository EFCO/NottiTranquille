package it.ispw.efco.nottitranquille.controller;

import it.ispw.efco.nottitranquille.model.DAO.LocationDAO;
import it.ispw.efco.nottitranquille.model.Location;
import it.ispw.efco.nottitranquille.model.Structure;
import it.ispw.efco.nottitranquille.view.LocationBean;

import java.util.List;

/**
 * Created by Federico on 13/07/2016.
 */
public class ManageLocation {

    public static void addNewLocation(LocationBean locationBean, Structure structure) {
        Location newLocation = new Location(locationBean);
        LocationDAO locationDAO = new LocationDAO();
        locationDAO.store(newLocation, structure);
    }

    public static void deleteLocation(Long id, List<Location> locations, Structure currentStructure) {
        LocationDAO locationDAO = new LocationDAO();
        for (Location locationToDelete : locations) {
            if (locationToDelete.getId().equals(id)) {
                locationDAO.deleteWithMerge(locationToDelete, currentStructure);
            }
        }
    }

    public static void modifyField(String field, String value, Long id) {
        LocationDAO locationDAO = new LocationDAO();
        Object newvalue;
        if (!field.equals("description")) {
            newvalue = Integer.valueOf(value);
        } else {
            newvalue = value;
        }
        locationDAO.modifyField(field, newvalue, id);
    }

    public static Location getLocationByID(Long id) {
        LocationDAO locationDAO = new LocationDAO();
        return locationDAO.select(id);
    }
}
