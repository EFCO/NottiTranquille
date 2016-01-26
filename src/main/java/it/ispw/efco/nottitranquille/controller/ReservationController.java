package it.ispw.efco.nottitranquille.controller;

import it.ispw.efco.nottitranquille.model.*;
import it.ispw.efco.nottitranquille.model.dao.ReservationDAO;
import it.ispw.efco.nottitranquille.model.dao.TenantDao;
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
        reservation.setBuyers(buyers);

        reservation.notifyObserver();

        tenant.addReservation(reservation);
        TenantDao.update(tenant);

        if(location.getType().getReservationType() == ReservationType.Direct)
            this.reserveDirect(reservation);
        else if (location.getType().getReservationType() == ReservationType.WithConfirmation)
            this.reserveWithConfirmation(reservation, location.getManager());

    }


    private void reserveWithConfirmation(Reservation reservation, Manager manager) {
            Notification notify = new Notification("ReservationNotify");
            notify.setMessage("you have a new reservation to apprive!");
            manager.sendNotification(notify);

            manager.addReservationToApprove(reservation);
    }

    private void reserveDirect(Reservation reservation){
        reservation.setState(ReservationState.ToPay);
    }

    public void approveReservation(Reservation reservation, Manager manager){
        reservation.setState(ReservationState.ToPay);

        Notification notify = new Notification("ReservationConfirmed");
        notify.setMessage("Your reservation has been confirmed! Now you can pay within 3 days!");

        reservation.getTenant().sendNotification(notify);
    }

    public void declineReservation(Reservation reservation, Manager manager){

        Notification notify = new Notification("ReservationDeclined");
        notify.setMessage("you reservation has been declined by the owner. We are sorry!");
        reservation.getTenant().sendNotification(notify);

        reservation.setState(ReservationState.Declined);
        ReservationDAO.delete(reservation);

    }


}