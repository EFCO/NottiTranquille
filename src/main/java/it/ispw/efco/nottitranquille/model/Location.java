package it.ispw.efco.nottitranquille.model;

import it.ispw.efco.nottitranquille.model.enumeration.LocationType;
import org.joda.time.DateTime;
import org.joda.time.Interval;

import javax.persistence.*;
import java.util.*;

/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
@Entity
public class Location {

    /**
     * Default constructor
     */
    public Location() {
    }

    public Location(List<Interval> booking, Structure structure) {
        this.booking = booking;
        this.structure = structure;
        structure.addLocation(this);
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
    @ElementCollection(targetClass = String.class)
    private List<String> photos;

    /**
     * 
     */
    private Integer numberOfBedrooms;

    //TODO @OneToMany
    @Transient
    private Prices prices;

    @Enumerated
    private LocationType type;

    //TODO @ElementCollection(targetClass = Service.class)
    @Transient
    private List<Service> services;

    @ManyToOne
    private Structure structure;

    @ElementCollection(targetClass = Interval.class)
    @Column(length=100000) //for the Data too long error
    private List<Interval> booking = new ArrayList<Interval>();

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
    public boolean isAvailable(Interval interval) {
        for (Interval inter : this.booking) {
            if (interval.isEqual(inter) || (inter.getStart().isBefore(interval.getStart()) && inter.getEnd().isAfter(interval.getEnd()))) {
                return true;
            }
        }
        return false;
    }

    @Id
    @GeneratedValue
    private Long id;

    public Long getId() {
        return id;
    }

    public Structure getStructure() {
        return structure;
    }
}