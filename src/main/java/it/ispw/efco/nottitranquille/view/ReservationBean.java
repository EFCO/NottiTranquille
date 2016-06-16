package it.ispw.efco.nottitranquille.view;

import it.ispw.efco.nottitranquille.controller.ReservationController;
import it.ispw.efco.nottitranquille.model.*;
import it.ispw.efco.nottitranquille.model.enumeration.ReservationState;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.*;

/**
 * Bean for Reservation. Boundary class that contains all Tenant informations entered by himself
 *
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
public class ReservationBean {

    private String id;

    /**
     * Tenant username used and memorized in the system
     */
    private String tenantUsername;

    /**
     * Full name of the Tenant
     */
    private String tenant;

    /**
     * @see LocationBean
     */
    private LocationBean locationBean;

    /**
     * @see ReservationState
     */
    private ReservationState state;   // state of reservation

    /**
     * @see it.ispw.efco.nottitranquille.model.Reservation#buyers
     */
    private List<Person> buyers;

    /**
     * Day when the reservation begins
     */
    private String startDate;

    /**
     * Day when the reservation ends
     */
    private String endDate;

    /**
     * Amount paid or to pay for location in the corresponding interval of days
     */
    private Float price;

    /**
     * This method is called whene a Tenant confirms the end of the booking procedure
     *
     * @return true if all information are syntactically correct
     */
    public boolean validate() {
        if (startDate == null || startDate.equals("") || endDate == null || endDate.equals(""))
            return false;

        if (buyers == null)
            return false;

        for (Person buyer : buyers) {
            if (buyer.getFirstName() == null || buyer.getFirstName().equals("") ||
                    buyer.getLastName() == null || buyer.getLastName().equals("")) {
                return false;
            }
        }

        /* format date */
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("dd-mm-yyyy");

        DateTime da = DateTime.parse(startDate, dateTimeFormatter);
        DateTime a = DateTime.parse(endDate, dateTimeFormatter);

        Interval period = new Interval(da, a);

        /* if all information are syntactically correct the system create a new reservation */
        ReservationController controller = ReservationController.getInstance();

        Long ID = new Long(locationBean.getId());
        controller.createReservation(tenantUsername, ID, period, buyers);

        return true;
    }

    public void addBuyer(String firstname, String surname) {

        if (buyers == null) {
            buyers = new ArrayList<Person>();
        }

        Person buyer = new Person();
        buyer.setFirstName(firstname);
        buyer.setLastName(surname);
        buyers.add(buyer);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenantUsername() {
        return tenantUsername;
    }

    public void setTenantUsername(String tenantUsername) {
        this.tenantUsername = tenantUsername;
    }

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
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

    public List<Person> getBuyers() {
        return buyers;
    }

    public void setBuyers(List<Person> buyers) {
        this.buyers = buyers;
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
}