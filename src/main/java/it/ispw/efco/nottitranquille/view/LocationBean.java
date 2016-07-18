package it.ispw.efco.nottitranquille.view;

import it.ispw.efco.nottitranquille.controller.ReservationController;
import it.ispw.efco.nottitranquille.model.Location;
import it.ispw.efco.nottitranquille.model.Service;
import it.ispw.efco.nottitranquille.model.enumeration.ReservationType;
import org.joda.time.DateTime;
import org.joda.time.Interval;

import java.util.Iterator;
import java.util.List;

/**
 * JavaBean for {@link Location}
 *
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
public class LocationBean {

    private String id;

    private String name;

    private String description;

    private List<Interval> enablesDate;

    private List<Interval> booked;

    private Float price;

    private String locationType;

    private String reservationType;

    private String address;

    /**
     * Populate the instance with information from the model layer.
     *
     * @param id matching a {@link Location}
     */
    public void populate(String id) {

        Long ID = new Long(id);

        ReservationController controller = ReservationController.getInstance();
        Location location = controller.findLocation(ID);
        name = location.getName();
        description = location.getDescription();
        enablesDate = location.getAvailableDate();
        booked = location.getBooked();
        price = location.getPrice();
        address = location.getStructure().getAddress().getCity() +
                " " + location.getStructure().getAddress().getAddress() +
                " " + location.getStructure().getAddress().getPostalcode();


        this.reservationType = location.getReservationType().getText();

        this.id = id;

    }

    /**
     * Populate this instance of JavaBean with Location data
     *
     * @param location
     */
    public void populate(Location location) {

        id = location.getId().toString();
        name = location.getName();
        description = location.getDescription();
        enablesDate = location.getAvailableDate();
        booked = location.getBooked();
        price = location.getPrice();
        address = location.getStructure().getAddress().getCity() +
                " " + location.getStructure().getAddress().getAddress() +
                " " + location.getStructure().getAddress().getPostalcode();


        this.reservationType = location.getReservationType().getText();
    }

    /**
     * Obtain availble date for reservation
     *
     * @return string formatted for DatePicker
     */
    public String getEnablesDate() {
        String enable = "";

        Iterator<Interval> availableInterval = enablesDate.iterator();
        while (availableInterval.hasNext()) {
            Interval interval = availableInterval.next();
            DateTime start = interval.getStart();
            DateTime end = interval.getEnd();

            enable += String.format(" {from : [ %d, %d, %d ] , to: [%d,%d,%d] }",
                    start.getYear(), start.getMonthOfYear() - 1, start.getDayOfMonth() - 1,
                    end.getYear(), end.getMonthOfYear() - 1, end.getDayOfMonth() - 1);


            if (availableInterval.hasNext())
                enable += " , ";
        }

        return enable;
    }

    /**
     * Obtain not available date for reservation
     *
     * @return string formatted for DatePicker
     */
    public String getDisableDate() {

        String disable = "";

        // get closer available day
        DateTime closer = null;
        for (Interval date : enablesDate) {
            if (closer == null)
                closer = date.getStart();
            else
                closer = date.getStart().compareTo(closer) == -1 ? date.getStart() : closer;
        }

        // Adding period from today to first available day
        if (closer != null)
            disable += String.format(" {from : [ %d, %d, %d ] , to: [%d,%d,%d] }",
                    DateTime.now().getYear(), DateTime.now().getMonthOfYear() - 1, DateTime.now().getDayOfMonth() - 1,
                    closer.getYear(), closer.getMonthOfYear() - 1, closer.getDayOfMonth() - 1);

        /* add booked days */
        for (Interval interval : booked) {
            DateTime start = interval.getStart();
            DateTime end = interval.getEnd();

            disable += String.format(" ,{from : [ %d, %d, %d ] , to: [%d,%d,%d] }",
                    start.getYear(), start.getMonthOfYear() - 1, start.getDayOfMonth() - 1,
                    end.getYear(), end.getMonthOfYear() - 1, end.getDayOfMonth() - 1);


        }

        return disable;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEnablesDate(List<Interval> enablesDate) {
        this.enablesDate = enablesDate;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Interval> getBooked() {
        return booked;
    }

    public void setBooked(List<Interval> booked) {
        this.booked = booked;
    }

    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    public String getReservationType() {
        return reservationType;
    }

    public void setReservationType(String reservationType) {
        this.reservationType = reservationType;
    }
}
