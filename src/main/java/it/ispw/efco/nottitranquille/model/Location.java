package it.ispw.efco.nottitranquille.model;

import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.*;

/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
//TODO @Entity
public class Location {

    /**
     * Default constructor
     */
    public Location() {
    }

    /**
     * 
     */
    private String description;

    /**
     * 
     */
    private Integer numberOfRooms;

    /**
     * 
     */
    private Integer numberOfBathrooms;

    /**
     * 
     */
    private Integer maxGuestsNumber;

    /**
     * 
     */
    private Integer numberOfBeds;

    /**
     * 
     */
    //TODO @ElementCollection(targetClass = String.class)
    private List<String> photos;

    /**
     * 
     */
    private Integer numberOfBedrooms;

    //TODO @OneToMany
    private Prices prices;

    //TODO @Embedded
    private LocationType type;

    //TODO @ElementCollection(targetClass = Service.class)
    private List<Service> services;

    //TODO @ManyToOne
    private Structure structure;

    //TODO @ElementCollection(targetClass = BookingCalendar.class)
    private List<BookingCalendar> booking;

    /**
     * @param date 
     * @return
     */
    public Float getPrice(DateTime date) {
        // TODO implement here
        return null;
    }

    /**
     * @param fromDate 
     * @param toDate 
     * @return
     */
    public Float getPrices(DateTime fromDate, DateTime toDate) {
        // TODO implement here
        return null;
    }

    /**
     * @param basePrice 
     * @param conditions 
     * @return
     */
    public Float getTotalPrice(Prices basePrice, Set<Condition> conditions) {
        // TODO implement here
        return null;
    }

    /**
     * @param fromDate 
     * @param toDate 
     * @param conditions
     */
    public void reserve(DateTime fromDate, DateTime toDate, Set<Condition> conditions) {
        // TODO implement here
    }

    /**
     * 
     */
    public void getAvailability() {
        // TODO implement here
    }

}