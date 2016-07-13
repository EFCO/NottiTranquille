package it.ispw.efco.nottitranquille.controller;

import it.ispw.efco.nottitranquille.model.*;
import it.ispw.efco.nottitranquille.model.Exception.IllegalBookingDate;
import it.ispw.efco.nottitranquille.model.dao.*;
import it.ispw.efco.nottitranquille.model.enumeration.ReservationState;
import it.ispw.efco.nottitranquille.model.enumeration.ReservationType;
import it.ispw.efco.nottitranquille.model.mail.Mailer;
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
     * @param tenantUsername: Tenant has reserved a Location
     * @param locationId:     reserved Location
     * @param period:         Interval of time in which the Location is booked
     * @param buyers:         Other Person will stay in the Location for the respective period
     * @return reservation's
     */
    public Reservation createReservation(String tenantUsername, Long locationId, Interval period, List<String> buyers) throws IllegalBookingDate {

        // Retrieve tenant and location from the db
        Person person = UserDAO.findBy(tenantUsername);
        Location location = LocationDAO.findByID(locationId);

        if (!location.isAvailable(period))
            throw new IllegalBookingDate("The period is not available for reservation");


        Reservation reservation = new Reservation(person, location, period);


        // The tenant specifies other location mates
        if (buyers != null)
            reservation.setBuyers(buyers);

        try {

            // Location is reservable without the confirm of the Manager
            if (location.getType().getReservationType() == ReservationType.Direct)
                this.reserveDirect(reservation);

                // Need Manager confirmation to complete the reservation
            else if (location.getType().getReservationType() == ReservationType.WithConfirm)
                this.reserveWithConfirmation(reservation, location.getManager());


            // add reservation to the tenant collection
            Tenant role = (Tenant) person.getRole("Tenant");
            role.addReservation(reservation);

        } catch (Exception e) {
            // do nothing, so unusual event
            e.printStackTrace();

        }

        // make persistence
        ReservationDAO.store(reservation);
        UserDAO.update(person);

        return reservation;
    }


    public Reservation createReservation(String tenantUsername, Long locationId, Interval period) throws IllegalBookingDate {
        return createReservation(tenantUsername, locationId, period, null);
    }


    /**
     * Reserve the Location with confirmation method: The Manager of the Location must approve the
     * reservation.
     *
     * @param reservation: Reservation to confirm. It has ReservetionType set on 'WithConfirm'
     * @param manager:     Manager of the Location
     */
    private void reserveWithConfirmation(Reservation reservation, Person manager) throws Exception {

        Manager role = null;
        try {
            role = (Manager) manager.getRole("Manager");
        } catch (Exception e) {
            throw e;
        }

        role.addReservationToApprove(reservation);


        reservation.setState(ReservationState.ToApprove);

        if (manager.getEmail() != null) {
            Mailer mailer = new Mailer();
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
     * @param reservation: Reservation declined
     */
    public void declineReservation(Reservation reservation) {
        reservation.setState(ReservationState.Declined);
        ReservationDAO.delete(reservation);
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

    /**
     * Operations used from the View layer to retrieve information from the model and fill the beans
     *
     * @param username Username of a {@link Person}
     * @param role     String that describe if a RegisteredUser is a Manager or a Tenant
     * @return List of Reservations
     */
    public List<Reservation> getReservationsForUser(String username, String role) {

        /**
         * List of {@link Reservation} belonging to an User
         */
        List<Reservation> reservations = null;
        Person person = UserDAO.findBy(username);

        try {

            if (role.equals("Tenant")) {
                Tenant tenant = (Tenant) person.getRole(role);
                reservations = tenant.getReservations();
            } else if (role.equals("Manager")) {
                Manager manager = (Manager) person.getRole(role);
                reservations = manager.getToApprove();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        return reservations;

    }

    /**
     * Return Location to the View
     *
     * @param id Location's id
     * @return {@link Location} corresponding to the given id
     */
    public Location findLocation(Long id) {
        //find Location from database to show it's details
        return LocationDAO.findByID(id);

    }

    /**
     * Retrieve a {@link Reservation} by its id
     *
     * @param id reservation's id
     * @return {@link Reservation} asked for
     */
    public Reservation findReservation(Long id) {

        try {
            // retrieve reservation from db
            return ReservationDAO.findByID(id);

        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }

    }

}