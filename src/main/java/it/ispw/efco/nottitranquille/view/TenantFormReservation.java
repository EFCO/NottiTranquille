package it.ispw.efco.nottitranquille.view;

import it.ispw.efco.nottitranquille.controller.ReservationController;
import it.ispw.efco.nottitranquille.model.Location;
import it.ispw.efco.nottitranquille.model.Person;
import it.ispw.efco.nottitranquille.model.Structure;
import it.ispw.efco.nottitranquille.model.Tenant;
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

    Tenant tenant;
    Location location;

    List<Person> buyers;

    String startDate;
    String endDate;

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

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public boolean validate() {
        if (startDate == null || startDate == "" || endDate == null || endDate == "")
            return false;

        if (buyers == null)
            return false;

        for (Person buyer : buyers) {
            if (buyer.getFirstName() == null || buyer.getFirstName().equals("") ||
                    buyer.getLastName() == null || buyer.getLastName().equals("")) {
                return false;
            }
        }

        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-mm-dd");

        DateTime da = DateTime.parse(startDate, dateTimeFormatter);
        DateTime a = DateTime.parse(endDate, dateTimeFormatter);

        Interval period = new Interval(da, a);
        ReservationController.getInstance().createReservation(tenant, location, period, buyers);

        return true;
    }

}