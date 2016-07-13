package it.ispw.efco.nottitranquille.view;

import it.ispw.efco.nottitranquille.controller.ReservationController;
import it.ispw.efco.nottitranquille.model.*;
import it.ispw.efco.nottitranquille.model.Exception.IllegalBookingDate;
import it.ispw.efco.nottitranquille.model.enumeration.ReservationState;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.*;

/**
 * JavaBean for {@link Reservation}.
 *
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
public class ReservationBean {

    public ReservationBean() {
    }

    private String id;

    private String username;

    /**
     * @see LocationBean
     */
    private LocationBean locationBean;

    /**
     * @see ReservationState
     */
    private ReservationState state;   // state of reservationervation

    /**
     * @see it.ispw.efco.nottitranquille.model.Reservation#buyers
     */
    private List<String> buyers;

    private String startDate;

    private String endDate;

    private Float price;

    /**
     * Method called when a Tenant confirms the end of the booking procedure.
     * Conversion and verification of the data.
     *
     * @return true if all information are syntactically correct
     */
    public boolean validate() {
        if (startDate == null || startDate.equals("") || endDate == null || endDate.equals(""))
            return false;

        /* format date */
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("dd-MM-yyyy");

        DateTime da = DateTime.parse(startDate, dateTimeFormatter);
        DateTime a = DateTime.parse(endDate, dateTimeFormatter);

        Interval period = new Interval(da, a);

        /* if all information are syntactically correct the system create a new reservationervation */
        ReservationController controller = ReservationController.getInstance();

        Long ID = new Long(locationBean.getId());

        try {

            // return true if reservation is created, false is period is not available
            Reservation reservation = controller.createReservation(username, ID, period, buyers);
            id = reservation.getId().toString();

        } catch (IllegalBookingDate e) {
            e.printStackTrace();
            return false;
        }

        return true;

    }

    public void populate(Reservation reservation) {

        this.setId(reservation.getId().toString());
        this.setUsername(reservation.getTenant().getUsername());
        this.setState(reservation.getState());
        this.setBuyers(reservation.getBuyers());

        DateTimeFormatter formatter = DateTimeFormat.forPattern("dd-MM-yyyy");
        this.setStartDate(reservation.getStartDate().toString(formatter));
        this.setEndDate(reservation.getEndDate().toString(formatter));
        this.setPrice(reservation.getPrice());

        Location loc = reservation.getLocation();
        LocationBean locBean = new LocationBean();
        locBean.setServices(loc.getServices());
        locBean.setName(loc.getName());
        locBean.setDescription(loc.getDescription());
        locBean.setEnablesDate(loc.getAvailableDate());
        locBean.setId(loc.getId().toString());

        this.setLocationBean(locBean);

    }

    public void addBuyer(String firstname, String surname) {

        if (buyers == null) {
            buyers = new ArrayList<String>();
        }

        buyers.add(firstname + " " + surname);

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocationBean getLocationBean() {
        return locationBean;
    }

    public void setLocationBean(LocationBean locationBean) {
        this.locationBean = locationBean;
    }

    public ReservationState getState() {
        return state;
    }

    public void setState(ReservationState state) {
        this.state = state;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public List<String> getBuyers() {
        return buyers;
    }

    public void setBuyers(List<String> buyers) {
        this.buyers = buyers;
    }

}