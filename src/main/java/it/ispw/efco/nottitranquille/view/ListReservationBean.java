package it.ispw.efco.nottitranquille.view;

import it.ispw.efco.nottitranquille.controller.ReservationController;
import it.ispw.efco.nottitranquille.model.Location;
import it.ispw.efco.nottitranquille.model.Reservation;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;

/**
 * JavaBean Class hold a List of {@link ReservationBean} matching a Tenant or a Manager
 *
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
public class ListReservationBean {

    /**
     * @see ReservationBean
     */
    private List<ReservationBean> beans;

    /**
     * Number of ReservationBean in the HashMap
     */
    private int nRes; // number of ReservationBean

    /**
     * Populate the instance with the information from the model
     *
     * @param username Username of a {@link it.ispw.efco.nottitranquille.model.Person}
     * @param role     String that describe if a RegisteredUser is a Manager or a Tenant
     */
    public void populate(String username, String role) {
        beans = new ArrayList<ReservationBean>();

        ReservationController controller = ReservationController.getInstance();
        List<Reservation> reservations = controller.getReservationsForUser(username, role);

        for (Reservation res : reservations) {
            ReservationBean bean = new ReservationBean();

            //populare reservationBean with information from the entity
            bean.populate(res);

            beans.add(bean);
        }

        nRes = beans.size();
    }

    /**
     * Manager approves a Reservation
     *
     * @param id: Reservation's id
     * @return boolean
     */
    public boolean approve(String id) {
        Long ID = new Long(id);

        ReservationController controller = ReservationController.getInstance();
        // return true if end successful
        return controller.approveReservation(ID);

    }

    /**
     * Manager declines a Reservation
     *
     * @param id: Reservation's id
     * @return boolean
     */
    public boolean decline(String id) {
        Long ID = new Long(id);

        ReservationController controller = ReservationController.getInstance();
        // return true if end successful
        return controller.declineReservation(ID);

    }

    public void setBeans(List<ReservationBean> beans) {
        this.beans = beans;
    }

    public List<ReservationBean> getBeans() {
        return beans;
    }

    public int getnRes() {
        return nRes;
    }

    public void setnRes(int n) {
        this.nRes = n;
    }

}
