package it.ispw.efco.nottitranquille.controller;

import it.ispw.efco.nottitranquille.model.*;
import it.ispw.efco.nottitranquille.model.dao.LocationDAO;
import it.ispw.efco.nottitranquille.model.dao.ReservationDAO;
import it.ispw.efco.nottitranquille.model.dao.TenantDao;
import it.ispw.efco.nottitranquille.model.enumeration.ReservationState;
import it.ispw.efco.nottitranquille.model.enumeration.ReservationType;
import org.joda.time.Interval;

import java.util.*;


/**
 * ReservationController Class implements design pattern Singleton.
 *
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 * @see Reservation
 * @see ReservationDAO
 */
public class ReservationController {

    private static ReservationController ourInstance = new ReservationController();

    public static ReservationController getInstance() {
        return ourInstance;
    }

    private ReservationController() {
    }

    public void createReservation(Tenant tenant, Location location, Interval period, List<Person> buyers) {

            Reservation reservation = new Reservation(tenant, location, period);

            if (buyers != null)
                reservation.setBuyers(buyers);

            if (location.getType().getReservationType() == ReservationType.Direct)
                this.reserveDirect(reservation);
            else if (location.getType().getReservationType() == ReservationType.WithConfirmation)
                this.reserveWithConfirmation(reservation, location.getManager());

            tenant.addReservation(reservation);
            reservation.notifyObserver();

            ReservationDAO.store(reservation);
            TenantDao.update(tenant);
            LocationDAO.update(location);

    }


    private void reserveWithConfirmation(Reservation reservation, Manager manager) {
        manager.addReservationToApprove(reservation);
        reservation.setState(ReservationState.ToApprove);
    }

    private void reserveDirect(Reservation reservation) {
        reservation.setState(ReservationState.ToPay);
    }

    public void approveReservation(Reservation reservation) {
        reservation.setState(ReservationState.ToPay);
        ReservationDAO.update(reservation);
        System.out.println("approata");
    }

    public void declineReservation(Reservation reservation, Manager manager) {
        reservation.setState(ReservationState.Declined);
        ReservationDAO.delete(reservation);
    }


}