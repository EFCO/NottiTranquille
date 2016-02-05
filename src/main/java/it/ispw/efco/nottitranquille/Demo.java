package it.ispw.efco.nottitranquille;

import it.ispw.efco.nottitranquille.controller.ReservationController;
import it.ispw.efco.nottitranquille.model.*;
import it.ispw.efco.nottitranquille.model.dao.LocationDAO;
import it.ispw.efco.nottitranquille.model.dao.LocationTypeDAO;
import it.ispw.efco.nottitranquille.model.dao.ManagerDAO;
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

        /* Manager */

        Manager manager = new Manager();
        manager.setFirstName("Claudio");
        manager.setLastName("Pastorini");

        manager.setUsername("manager");
        manager.setPassword("password");

        ManagerDAO.store(manager);

        /* Tenant */

        Tenant me = new Tenant();
        me.setFirstName("Emanuele");
        me.setLastName("Vannacci");

        me.setUsername("Zanna");
        me.setPassword("password");

        TenantDao.store(me);

        /*LocationType*/

        LocationType hostelRoom = new LocationType(ReservationType.Direct);
        LocationType apartment = new LocationType(ReservationType.WithConfirmation);

        LocationTypeDAO.store(hostelRoom);
        LocationTypeDAO.store(apartment);


        /* Interval */

        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd");

        DateTime daA = DateTime.parse("2016-04-21", dateTimeFormatter);
        DateTime aA = DateTime.parse("2016-04-30", dateTimeFormatter);

        Interval intervalA = new Interval(daA, aA);

        DateTime daB = DateTime.parse("2016-02-20" , dateTimeFormatter);
        DateTime aB = DateTime.parse("2016-03-11" , dateTimeFormatter);

        Interval intervalB = new Interval(daB, aB);


        /* Location */

        Location myLocationA = new Location();
        myLocationA.addBookingDate(intervalA);

        myLocationA.setType(hostelRoom);

        myLocationA.setName("stanza 0 dell'ostello di Villa Tasos");
        myLocationA.setDescription("Bellissimo stanza fornita di ogni tipo di" +
                "servizi, televisione, area condizionata. Molto paranamico davvero consigliato");

        myLocationA.setPrice(400);
        myLocationA.setManager(manager);

        Location myLocationB = new Location();
        myLocationB.addBookingDate(intervalB);

        myLocationB.setType(apartment);

        myLocationB.setName("appartamento privato");
        myLocationB.setDescription("Bellissimo apparamento fornito di ogni tipo di" +
                "servizi, televisione, area condizionata. Molto paranamico davvero consigliato");

        myLocationB.setPrice(200);
        myLocationB.setManager(manager);

        LocationDAO.store(myLocationA);
        LocationDAO.store(myLocationB);


        /* Create Reservation by controller*/

        ReservationController controller = ReservationController.getInstance();

        controller.createReservation(me, myLocationA, intervalA, null);
        controller.createReservation(me, myLocationB, intervalB, null);


    }


}
