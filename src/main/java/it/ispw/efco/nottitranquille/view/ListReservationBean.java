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

    // A ReservationBean correspond to a Long value equals to the id of reservation
    // inner the ReservationBean itself.
    private HashMap<Long, ReservationBean> beans = new HashMap<Long, ReservationBean>();

    private Integer nRes; // number of ReservationBean

    /**
     * This method set the HashMap filling in ReservationBean the informations about
     * the user's reservations.
     *
     * @param username: User's username
     * @param password: User's password
     * @param role:     User can be a Tenant or a Manager. Otherwise the method return setting nothing
     */
    public void populate(String username, String password, String role) {

        try {

            List<Reservation> reservations = new ArrayList<Reservation>();

            // retrieve User corrisponding to username and password and
            // Reservervations of the User
            if (role.equals("Tenant")) {
                Tenant tenant = TenantDao.findByNameAndPassword(username, password);
                reservations = tenant.getReservations();
            } else if (role.equals("Manager")) {
                Manager manager = ManagerDAO.findByNameAndPassword(username, password);
                reservations = manager.getToApprove();
            }

            nRes = reservations.size();

            // load information from reservations
            for (int i = 0; i < nRes; i++) {
                Reservation res = reservations.get(i);
                ReservationBean bean = new ReservationBean();
                bean.populate(res);
                beans.put(res.getId(), bean);
            }

        } catch (NoResultException e) {
            // The first time that jsp pages are analyzed no User is logged
            // so exception is thrown. Return null and bean is not loaded.
            return;
        }

    }

    public HashMap<Long, ReservationBean> getBeans() {
        return beans;
    }

    /**
     * Get the number of Reservation created by the Tenant or asked to the Manager
     *
     * @return Integer
     */
    public Integer getnRes() {
        return nRes;
    }

    /**
     * Get ReservationBean in relation to the Reservation's id
     *
     * @param resId
     * @return
     */
    public ReservationBean get(Long resId) {
        return beans.get(resId);
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

}
