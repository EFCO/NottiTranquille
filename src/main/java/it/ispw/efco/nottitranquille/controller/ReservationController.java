package it.ispw.efco.nottitranquille.controller;

import it.ispw.efco.nottitranquille.model.*;
import it.ispw.efco.nottitranquille.model.dao.LocationDAO;
import it.ispw.efco.nottitranquille.model.dao.ReservationDAO;
import it.ispw.efco.nottitranquille.model.dao.TenantDao;
import it.ispw.efco.nottitranquille.model.enumeration.ReservationState;
import it.ispw.efco.nottitranquille.model.enumeration.ReservationType;
import it.ispw.efco.nottitranquille.model.mail.Mail;
import org.joda.time.Interval;

import javax.persistence.NoResultException;
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

    /**
     * It Instantiates and saves a new Reservation.
     *
     * @param tenant:   Tenant has reserved a Location
     * @param location: reserved Location
     * @param period:   Interval of time in which the Location is reserver
     * @param buyers:   Other Person will stay in the Location for the respective period
     */
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


    /**
     * Reserve the Location with confirmation method: The Manager of the Location must approve the
     * reservation.
     *
     * @param reservation: Reservation to confirm. It has ReservetionType set on 'WithConfirmation'
     * @param manager:     Manager of the Location
     */
    private void reserveWithConfirmation(Reservation reservation, Manager manager) {
        manager.addReservationToApprove(reservation);
        reservation.setState(ReservationState.ToApprove);

        if (manager.getEmail() != null) {
            Mail mailer = new Mail();
            mailer.send(manager.getEmail(), "[NottiTranquille:] Nuova prenotazione", "Hai una nuova prenotazione da controllare!",
                    null);
        }
    }

    /**
     * Reserve the Location with direct method. Reservation has ReservationType set on 'Direct'
     *
     * @param reservation: Reservation is set on the 'ToPay' state without Manager confirmation
     */
    private void reserveDirect(Reservation reservation) {
        reservation.setState(ReservationState.ToPay);
    }

    /**
     * The Manager can confirm Reservation created by a Tenant.
     *
     * @param reservation: Reservation confirmed
     */
    public void approveReservation(Reservation reservation) {
        reservation.setState(ReservationState.ToPay);
        ReservationDAO.update(reservation);
    }

    /**
     * The Manager can decline Reservation created by a Tenant.
     *
     * @param reservation: Reservation declined
     */
    public void declineReservation(Reservation reservation) {
        reservation.setState(ReservationState.Declined);
        ReservationDAO.delete(reservation);
    }

    /**
     * The Manager can confirm Reservation created by a Tenant.
     *
     * @param id: confirmed Reservation's id
     * @return boolean
     */
    public boolean approveReservation(Long id) {
        try {
            Reservation reservation = ReservationDAO.findByID(id);

            reservation.setState(ReservationState.ToPay);
            ReservationDAO.update(reservation);

        } catch (NoResultException e) {
            return false;
        }

        return true;
    }

    /**
     * The Manager can decline Reservation created by a Tenant.
     *
     * @param id: declined Reservation's id
     * @return boolean
     */
    public boolean declineReservation(Long id) {
        try {
            Reservation reservation = ReservationDAO.findByID(id);

            reservation.setState(ReservationState.Declined);
            ReservationDAO.update(reservation);

        } catch (NoResultException e) {
            return false;
        }

        return true;
    }

}