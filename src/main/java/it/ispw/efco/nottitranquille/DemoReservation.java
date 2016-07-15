package it.ispw.efco.nottitranquille;

import it.ispw.efco.nottitranquille.controller.ReservationController;
import it.ispw.efco.nottitranquille.model.*;
import it.ispw.efco.nottitranquille.model.Exception.IllegalBookingDate;
import it.ispw.efco.nottitranquille.model.dao.*;
import it.ispw.efco.nottitranquille.model.enumeration.*;
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

        /* Address */
        Address addressA = new Address("Italia", "Roma", "Via del pirata 12", "00040");
        Address addressB = new Address("Italia", "Frascati", "Viale Ungheria 34", "00044");

        /* Structure */

        Structure structureA = new Structure();
        structureA.setAddress(addressA);

        Structure structureB = new Structure();
        structureB.setAddress(addressB);


        /* Interval */

        // Available date for Location
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("dd-MM-yyyy");

        DateTime daA = DateTime.parse("01-01-2017", dateTimeFormatter);
        DateTime aA = DateTime.parse("01-01-2018", dateTimeFormatter);

        Interval intervalA = new Interval(daA, aA);

        DateTime daB = DateTime.parse("01-01-2018", dateTimeFormatter);
        DateTime aB = DateTime.parse("30-12-2019", dateTimeFormatter);

        Interval intervalB = new Interval(daB, aB);

        // booking period
        DateTime da1 = DateTime.parse("01-04-2017", dateTimeFormatter);
        DateTime a1 = DateTime.parse("07-04-2017", dateTimeFormatter);

        Interval interval1 = new Interval(da1, a1);

        DateTime da2 = DateTime.parse("01-06-2017", dateTimeFormatter);
        DateTime a2 = DateTime.parse("12-06-2017", dateTimeFormatter);

        Interval interval2 = new Interval(da2, a2);


        /* Location */

        Location myLocationA = new Location();

        myLocationA.setLocationType(LocationType.Agriturismo);
        myLocationA.setReservationType(ReservationType.WithConfirm);

        myLocationA.setName("Locanda del puledro impennato");
        myLocationA.setDescription(
                "Parte della locanda era stata scavata nella collina, tanto che " +
                        "nell'ala nord le finestre del secondo piano si trovavano al livello" +
                        "del suolo. Questa ala è stata attrezzata per ospitare i " +
                        "mezzuomini."
        );

        myLocationA.setPrice(400.00f);
        myLocationA.setManager(manager);
        myLocationA.setStructure(structureA);

        Location myLocationB = new Location();

        myLocationB.setLocationType(LocationType.BedBreakfast);
        myLocationB.setReservationType(ReservationType.Direct);

        myLocationB.setName("Stamberga Strillante");
        myLocationB.setDescription(
                "La Stamberga Strillante si trova a Hogsmeade ed è conosciuta " +
                        "come la casa più infestata di spiriti della Gran Bretagna." +
                        " E' situata  più in alto rispetto al villaggio, su una collinetta, con finestre" +
                        " chiuse da tavolati e il cupo giardino inselvatichito. "
        );


        myLocationB.setPrice(200.00f);
        myLocationB.setManager(manager);
        myLocationB.setStructure(structureB);

        try {
            myLocationA.addAvailablePeriod(intervalA);
            myLocationA.addAvailablePeriod(intervalB);
            myLocationB.addAvailablePeriod(intervalA);
            myLocationB.addAvailablePeriod(intervalB);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        LocationDAO.store(myLocationA);
        LocationDAO.store(myLocationB);


        /* Create Reservation by controller*/

        ReservationController controller = ReservationController.getInstance();

        try {

            controller.createReservation(me.getUsername(), myLocationA.getId(), interval1, null);
            controller.createReservation(me.getUsername(), myLocationB.getId(), interval2, null);
        } catch (IllegalBookingDate e) {
            e.printStackTrace();
            System.exit(1);
        }

        System.exit(0);

    }


}
