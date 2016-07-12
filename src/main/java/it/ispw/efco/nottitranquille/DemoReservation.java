package it.ispw.efco.nottitranquille;

import it.ispw.efco.nottitranquille.controller.ReservationController;
import it.ispw.efco.nottitranquille.model.*;
import it.ispw.efco.nottitranquille.model.dao.*;
import it.ispw.efco.nottitranquille.model.enumeration.ReservationType;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;


/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
public class DemoReservation {

    public static void main(String args[]) {

         /* Manager */

        Person manager = new Person("Claudio", "Pastorini");
        try {
            manager.addRole(new Manager());
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        manager.setUsername("manager");
        manager.setPassword("password");


        /* Tenant */

        Person me = new Person("Emanuele", "Vannacci");
        try {
            me.addRole(new Tenant());
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        me.setUsername("Zanna");
        me.setPassword("password");

        UserDAO.store(manager);
        UserDAO.store(me);


        /*LocationType*/

        LocationType locanda = new LocationType(ReservationType.Direct);
        LocationType casaInfestata = new LocationType(ReservationType.WithConfirmation);

        /* Interval */

        // Available date for Location
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd");

        DateTime daA = DateTime.parse("2017-01-01", dateTimeFormatter);
        DateTime aA = DateTime.parse("2017-12-30", dateTimeFormatter);

        Interval intervalA = new Interval(daA, aA);

        DateTime daB = DateTime.parse("2018-01-01", dateTimeFormatter);
        DateTime aB = DateTime.parse("2018-12-30", dateTimeFormatter);

        Interval intervalB = new Interval(daB, aB);

        // booking period
        DateTime da1 = DateTime.parse("2017-04-01", dateTimeFormatter);
        DateTime a1 = DateTime.parse("2017-4-7", dateTimeFormatter);

        Interval interval1 = new Interval(da1, a1);

        DateTime da2 = DateTime.parse("2018-06-01", dateTimeFormatter);
        DateTime a2 = DateTime.parse("2018-06-12", dateTimeFormatter);

        Interval interval2 = new Interval(da2, a2);


        /* Location */

        Location myLocationA = new Location();

        myLocationA.setType(locanda);

        myLocationA.setName("Locanda del puledro impennato");
        myLocationA.setDescription(
                "Parte della locanda era stata scavata nella collina, tanto che " +
                        "nell'ala nord le finestre del secondo piano si trovavano al livello" +
                        "del suolo. Questa ala è stata attrezzata per ospitare i " +
                        "mezzuomini."
        );

        myLocationA.setPrice(400.00f);
        myLocationA.setManager(manager);
        try {
            myLocationA.addAvalablePeriod(intervalA);
        } catch (Exception e) {
            e.printStackTrace();
        }


        Location myLocationB = new Location();

        myLocationB.setType(casaInfestata);

        myLocationB.setName("Stamberga Strillante");
        myLocationB.setDescription(
                "La Stamberga Strillante si trova a Hogsmeade ed è conosciuta " +
                        "come la casa più infestata di spiriti della Gran Bretagna." +
                        " E' situata  più in alto rispetto al villaggio, su una collinetta, con finestre" +
                        " chiuse da tavolati e il cupo giardino inselvatichito. "
        );


        myLocationB.setPrice(200.00f);
        myLocationB.setManager(manager);
        try {
            myLocationA.addAvalablePeriod(intervalB);
        } catch (Exception e) {
            e.printStackTrace();
        }

        LocationDAO.store(myLocationA);
        LocationDAO.store(myLocationB);


        /* Create Reservation by controller*/

        ReservationController controller = ReservationController.getInstance();

        controller.createReservation(me.getUsername(), myLocationA.getId(), interval1, null);
        controller.createReservation(me.getUsername(), myLocationB.getId(), interval2, null);

    }


}
