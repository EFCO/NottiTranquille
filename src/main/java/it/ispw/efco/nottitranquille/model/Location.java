package it.ispw.efco.nottitranquille.model;

import java.util.*;

/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
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
    private String[] photos;

    /**
     * 
     */
    private Integer numberOfBedrooms;








    /**
     * @param date 
     * @return
     */
    public Float getPrice(Date date) {
        // TODO implement here
        return null;
    }

    /**
     * @param fromDate 
     * @param toDate 
     * @return
     */
    public Float getPrices(Date fromDate, Date toDate) {
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
    public void reserve(Date fromDate, Date toDate, Set<Condition> conditions) {
        // TODO implement here
    }

    /**
     * 
     */
    public void getAvailability() {
        // TODO implement here
    }

}