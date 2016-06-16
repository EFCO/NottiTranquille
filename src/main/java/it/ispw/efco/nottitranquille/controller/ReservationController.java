package it.ispw.efco.nottitranquille.controller;

import it.ispw.efco.nottitranquille.model.*;
import it.ispw.efco.nottitranquille.model.dao.*;
import it.ispw.efco.nottitranquille.model.enumeration.ReservationState;
import it.ispw.efco.nottitranquille.model.enumeration.ReservationType;
import it.ispw.efco.nottitranquille.model.mail.Mail;
import it.ispw.efco.nottitranquille.view.ListReservationBean;
import it.ispw.efco.nottitranquille.view.LocationBean;
import it.ispw.efco.nottitranquille.view.ReservationBean;
import org.joda.time.Interval;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

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
     * @param period:         Interval of time in which the Location is reserver
     * @param buyers:         Other Person will stay in the Location for the respective period
     */
    public void createReservation(String tenantUsername, Long locationId, Interval period, List<Person> buyers) {

        Tenant tenant = (Tenant) RegisteredUserDAO.findByUserName(tenantUsername, Tenant.class);
        Location location = LocationDAO.findByID(locationId);

        Reservation reservation = new Reservation(tenant, location, period);

        if (buyers != null)
            reservation.setBuyers(buyers);

        if (location.getType().getReservationType() == ReservationType.Direct)
            this.reserveDirect(reservation);
        else if (location.getType().getReservationType() == ReservationType.WithConfirmation)
            this.reserveWithConfirmation(reservation, location.getManager());

        tenant.addReservation(reservation);
        reservation.notifyLocation();

        ReservationDAO.store(reservation);
        RegisteredUserDAO.update(tenant, Tenant.class);
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
        reservation.notifyLocation();
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
            reservation.notifyLocation();
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
        reservation.notifyLocation();
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
            reservation.notifyLocation();
            ReservationDAO.update(reservation);

        } catch (NoResultException e) {
            return false;
        }

        return true;
    }

    public List<ReservationBean> fillReservationBeans(String username, String role) {

        /**
         * List of {@link Reservation} belonging to an User
         */
        List<Reservation> reservations = new ArrayList<Reservation>();

        /**
         * List of beans to fill with the informations of a reservation
         * @see ReservationBean
         * @see ListReservationBean
         * @see LocationBean
         */
        List<ReservationBean> beanList = new ArrayList<ReservationBean>();

        // retrieve User corresponding to username and password and
        // Reservations of the User
        if (role.equals("Tenant")) {
            Tenant tenant = (Tenant) RegisteredUserDAO.findByUserName(username, Tenant.class);
            reservations = tenant.getReservations();
        } else if (role.equals("Manager")) {
            Manager manager = (Manager) RegisteredUserDAO.findByUserName(username, Manager.class);
            reservations = manager.getToApprove();
        }

        for (Reservation res : reservations) {
            ReservationBean bean = new ReservationBean();

            bean.setId(res.getId().toString());
            bean.setTenantUsername(res.getTenant().getUsername());
            bean.setTenant(res.getTenant().getCompleteName());
            bean.setState(res.getState());
            bean.setBuyers(res.getBuyers());

            DateTimeFormatter formatter = DateTimeFormat.forPattern("MM/dd/yyyy");
            bean.setStartDate(res.getStartDate().toString(formatter));
            bean.setEndDate(res.getEndDate().toString(formatter));
            bean.setPrice(res.getPrice());

            Location loc = res.getLocation();
            LocationBean locBean = new LocationBean();
            locBean.setServices(loc.getServices());
            locBean.setName(loc.getName());
            locBean.setDescription(loc.getDescription());
            locBean.setEnablesDate(loc.getAvailableDate());
            locBean.setId(loc.getId().toString());

            bean.setLocationBean(locBean);

            beanList.add(bean);
        }

        return beanList;
    }

    public void fillLocationBean(LocationBean bean, Long id) {
        //find Location from database to show it's details
        Location location = LocationDAO.findByID(id);

        bean.setServices(location.getServices());
        bean.setName(location.getName());
        bean.setDescription(location.getDescription());
        bean.setEnablesDate(location.getAvailableDate());
        bean.setId(location.getId().toString());

    }

}