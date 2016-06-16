package it.ispw.efco.nottitranquille.view;

import it.ispw.efco.nottitranquille.controller.ReservationController;
import it.ispw.efco.nottitranquille.model.Manager;
import it.ispw.efco.nottitranquille.model.Reservation;
import it.ispw.efco.nottitranquille.model.Tenant;
import it.ispw.efco.nottitranquille.model.dao.ManagerDAO;
import it.ispw.efco.nottitranquille.model.dao.TenantDao;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This bean contains a list of {@link ReservationBean} associated with the id of the Reservation
 * that it describes.
 *
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
public class ListReservationBean {

    /**
     * A ReservationBean correspond to a Long value equals to the id of reservation
     * inner the ReservationBean itself.
     */
    private List<ReservationBean> beans;

    /**
     * Number of ReservationBean in the HashMap
     */
    private int nRes; // number of ReservationBean

    public void populate(String username, String role) {
        ReservationController controller = ReservationController.getInstance();
        beans = controller.fillReservationBeans(username, role);

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
        // return true if end successfull
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
        // return true if end successfull
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
