package it.ispw.efco.nottitranquille.controller;

import it.ispw.efco.nottitranquille.model.Location;
import it.ispw.efco.nottitranquille.model.Person;
import it.ispw.efco.nottitranquille.model.Reservation;
import it.ispw.efco.nottitranquille.model.Tenant;
import it.ispw.efco.nottitranquille.model.dao.ReservationDAO;
import it.ispw.efco.nottitranquille.model.enumeration.ReservationState;
import it.ispw.efco.nottitranquille.model.enumeration.ReservationType;
import org.joda.time.Interval;

import java.util.*;


/**
 * ReservationController Class implements design pattern Singleton.
 *
 * @see  Reservation
 * @see ReservationDAO
 *
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
public class ReservationController {

    private static ReservationController ourInstance = new ReservationController();

    public static ReservationController getInstance() {
        return ourInstance;
    }

    private ReservationController() {
    }

    public void createReservation(Tenant tenant, Location location, Interval period, List<Person> buyers){
        Reservation reservation = new Reservation(tenant, location, period);
        //TODO throw Exception if period not available
        reservation.setBuyers(buyers);

        reservation.notifyObserver();
        ReservationDAO.store(reservation);

        if(location.getType().getReservationType() == ReservationType.Direct)
            this.reserveDirect(reservation);
        else if (location.getType().getReservationType() == ReservationType.WithConfirmation)
            this.reserveWithConfirmation(reservation);


    }
    private void reserveWithConfirmation(Reservation reservation){

    }

    private void reserveDirect(Reservation reservation){
        //TODO set only if in state of Unknown
        reservation.setState(ReservationState.ToPay);
        ReservationDAO.update(reservation);
    }



}