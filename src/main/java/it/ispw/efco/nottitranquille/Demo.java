package it.ispw.efco.nottitranquille;

import it.ispw.efco.nottitranquille.model.Address;
import it.ispw.efco.nottitranquille.model.Location;
import it.ispw.efco.nottitranquille.model.LocationType;
import it.ispw.efco.nottitranquille.model.Structure;
import it.ispw.efco.nottitranquille.model.dao.LocationDAO;
import it.ispw.efco.nottitranquille.model.dao.LocationTypeDAO;
import it.ispw.efco.nottitranquille.model.enumeration.ReservationType;


/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
public class Demo {

    public static void main(String args[]) {

        LocationType hostelRoom = new LocationType(ReservationType.Direct);
        LocationTypeDAO.store(hostelRoom);


    }


}
