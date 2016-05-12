package it.ispw.efco.nottitranquille.view;

import it.ispw.efco.nottitranquille.controller.ReservationController;
import it.ispw.efco.nottitranquille.model.Location;
import it.ispw.efco.nottitranquille.model.Person;
import it.ispw.efco.nottitranquille.model.Structure;
import it.ispw.efco.nottitranquille.model.Tenant;
import it.ispw.efco.nottitranquille.model.dao.LocationDAO;
import it.ispw.efco.nottitranquille.model.dao.TenantDao;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.*;

/**
 * Bean for Reservation. View Class contains all Tenant information entered by himself
 *
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
public class TenantFormReservation {

    private String tenantUsername;
    private String tenantPass;

    private LocationBean locationBean;

    private List<Person> buyers;

    private String startDate;
    private String endDate;

    public void addBuyer(String firstname, String surname) {

        if (buyers == null) {
            buyers = new ArrayList<Person>();
        }

        Person buyer = new Person();
        buyer.setFirstName(firstname);
        buyer.setLastName(surname);
        buyers.add(buyer);
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

    public String getTenantUsername() {
        return tenantUsername;
    }

    public void setTenantUsername(String tenantUsername) {
        this.tenantUsername = tenantUsername;
    }

    public void setTenantPass(String tenantPass) {
        this.tenantPass = tenantPass;
    }

    public LocationBean getLocationBean() {
        return locationBean;
    }

    public void setLocationBean(LocationBean locationBean) {
        this.locationBean = locationBean;
    }

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

        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("dd-mm-yyyy");

        DateTime da = DateTime.parse(startDate, dateTimeFormatter);
        DateTime a = DateTime.parse(endDate, dateTimeFormatter);

        Tenant tenant = TenantDao.findByNameAndPassword(tenantUsername, tenantPass);
        Location location = LocationDAO.findByID(new Long(locationBean.getId()));

        Interval period = new Interval(da, a);
        ReservationController.getInstance().createReservation(tenant, location, period, buyers);

        return true;
    }

}