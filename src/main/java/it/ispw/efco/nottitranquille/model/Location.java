package it.ispw.efco.nottitranquille.model;

import org.joda.time.Interval;

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
    private Prices prices;

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
    private List<String> photos;

    /**
     *
     */
    private Integer numberOfBedrooms;

    /**
     * @param date
     * @return
     */
    public double getPrice(Date date) {
        // TODO implement here
        return 0.0d;
    }

    /**
     * @param startDate
     * @param endDate
     * @return
     */
    public double getPrice(Date startDate, Date endDate) {
        // TODO implement here
        return 0.0d;
    }

    /**
     * @param interval
     * @return
     */
    public double getPrice(Interval interval) {
        // TODO implement here
        return 0.0d;
    }

    /**
     * @param startDate
     * @param endDate
     * @param services
     * @return
     */
    public double getTotalPrice(Date startDate, Date endDate, Set<Service> services) {
        // TODO implement here
        return 0.0d;
    }

    /**
     * @param interval
     * @param services
     * @return
     */
    public double getTotalPrice(Interval interval, Set<Service> services) {
        // TODO implement here
        return 0.0d;
    }

    /**
     * @param startDate
     * @param endDate
     * @param services
     */
    public void reserve(Date startDate, Date endDate, Set<Service> services) {
        // TODO implement here
    }

    /**
     * @param interval
     * @param services
     */
    public void reserve(Interval interval, Set<Service> services) {
        // TODO implement here
    }

    /**
     *
     */
    public void getAvailability() {
        // TODO implement here
    }

    /**
     *
     * @return
     */
    public Prices getPrices() {
        return this.prices;
    }

}