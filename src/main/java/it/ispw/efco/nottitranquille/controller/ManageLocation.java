package it.ispw.efco.nottitranquille.controller;

import it.ispw.efco.nottitranquille.model.DAO.LocationDAO;
import it.ispw.efco.nottitranquille.model.Location;
import it.ispw.efco.nottitranquille.model.Structure;
import it.ispw.efco.nottitranquille.view.LocationBean;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.format.DateTimeFormat;

import java.util.ArrayList;
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

    public static void modifyField(String field, String[] value, Long id) {
        LocationDAO locationDAO = new LocationDAO();
        Object newvalue;
        if (value.length == 1) {
            if (!field.equals("description")) {
                newvalue = Integer.valueOf(value[0]);
            } else {
                newvalue = value;
            }
            locationDAO.modifyField(field, newvalue, id);
        } else {
            List<Interval> intervalList = new ArrayList<Interval>();
            for (int i = 0; i < value.length; ) {
                DateTime firstDate = DateTime.parse(value[i], DateTimeFormat.forPattern("dd-MM-yyyy"));
                DateTime secondDate = DateTime.parse(value[i + 1], DateTimeFormat.forPattern("dd-MM-yyyy"));
                intervalList.add(new Interval(firstDate, secondDate));
                i += 2;
            }
            locationDAO.modifyBooking(intervalList, id);
        }
    }

    public static Location getLocationByID(Long id) {
        LocationDAO locationDAO = new LocationDAO();
        return locationDAO.select(id);
    }
}
