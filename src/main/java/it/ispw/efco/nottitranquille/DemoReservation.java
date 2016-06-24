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

        Manager manager = ManagerDAO.findByUserName("manager");
        Tenant zanna = TenantDAO.findByUserName("Zanna");

        /*LocationType*/

        LocationType locanda = new LocationType(ReservationType.Direct);
        LocationType casaInfestata = new LocationType(ReservationType.WithConfirmation);

        LocationTypeDAO.store(locanda);
        LocationTypeDAO.store(casaInfestata);


        /* Interval */

        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd");

        DateTime daA = DateTime.parse("2017-04-21", dateTimeFormatter);
        DateTime aA = DateTime.parse("2017-04-30", dateTimeFormatter);

        Interval intervalA = new Interval(daA, aA);

        DateTime daB = DateTime.parse("2017-02-20", dateTimeFormatter);
        DateTime aB = DateTime.parse("2017-03-11", dateTimeFormatter);

        Interval intervalB = new Interval(daB, aB);


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

        LocationDAO.store(myLocationA);
        LocationDAO.store(myLocationB);


        /* Create Reservation by controller*/

        ReservationController controller = ReservationController.getInstance();

        controller.createReservation(zanna.getUsername(), myLocationA.getId(), intervalA, null);
        controller.createReservation(zanna.getUsername(), myLocationB.getId(), intervalB, null);

    }


}
