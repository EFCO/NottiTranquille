package it.ispw.efco.nottitranquille;

import it.ispw.efco.nottitranquille.model.*;
import it.ispw.efco.nottitranquille.model.dao.LocationDAO;
import it.ispw.efco.nottitranquille.model.dao.LocationTypeDAO;
import it.ispw.efco.nottitranquille.model.dao.TenantDao;
import it.ispw.efco.nottitranquille.model.enumeration.ReservationType;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;


/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
public class Demo {

    public static void main(String args[]) {

        LocationType hostelRoom = new LocationType(ReservationType.Direct);
        LocationTypeDAO.store(hostelRoom);

        LocationTypeDAO.store(hostelRoom);

        /* Instantiate Location */

        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-mm-dd");

        DateTime da = DateTime.parse("2016-01-21", dateTimeFormatter);
        DateTime a = DateTime.parse("2016-01-30", dateTimeFormatter);

        Interval interval = new Interval(da, a);

        Location myLocation = new Location();
        myLocation.addBookingDate(interval);

        myLocation.setType(hostelRoom);

        myLocation.setName("Appartamento 0 di Villa Tasos");
        myLocation.setDescription("Bellissimo apparamento fornito di ogni tipo di" +
                "servizi, televisione, area condizionata. Molto paranamico davvero consigliato");

        LocationDAO.store(myLocation);

        /* Istantiate Tenant */

        Tenant me = new Tenant();
        me.setFirstName("Emanuele");
        me.setLastName("Vannacci");

        me.setUserName("Zanna");

        TenantDao.store(me);

       /* System.out.println(LocationDAO.findAllLocation().get(0).getDescription());*/

    }


}
