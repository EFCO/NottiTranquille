package it.ispw.efco.nottitranquille.controller;

import it.ispw.efco.nottitranquille.model.Location;
import it.ispw.efco.nottitranquille.model.LocationDAO;
import it.ispw.efco.nottitranquille.model.Structure;
import it.ispw.efco.nottitranquille.view.LocationBean;

/**
 * Created by Federico on 13/07/2016.
 */
public class ManageLocation {

    public static void addNewLocation(LocationBean locationBean, Structure structure) {
        Location newLocation = new Location(locationBean);
        LocationDAO locationDAO = new LocationDAO();
        locationDAO.store(newLocation, structure);
    }
}
