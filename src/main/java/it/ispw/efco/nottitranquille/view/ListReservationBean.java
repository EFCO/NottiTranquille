package it.ispw.efco.nottitranquille.view;

import it.ispw.efco.nottitranquille.controller.ReservationController;
import it.ispw.efco.nottitranquille.model.Location;
import it.ispw.efco.nottitranquille.model.Reservation;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
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

    /**
     * Populate the instance with the information from the model
     *
     * @param username Username of a {@link it.ispw.efco.nottitranquille.model.RegisteredUser}
     * @param role     String that describe if a RegisteredUser is a Manager or a Tenant
     */
    public void populate(String username, String role) {
        beans = new ArrayList<ReservationBean>();

        ReservationController controller = ReservationController.getInstance();
        List<Reservation> reservations = controller.getReservationsForUser(username, role);

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