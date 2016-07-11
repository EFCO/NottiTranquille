package it.ispw.efco.nottitranquille.model;

import it.ispw.efco.nottitranquille.model.enumeration.LocationType;
import org.joda.time.DateTime;
import org.joda.time.Interval;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
@Entity
public class Location {

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

    public Integer getMaxGuestsNumber() {
        return maxGuestsNumber;
    }

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

    @ManyToMany
    private List<Service> services;

    @ManyToOne
    private Structure structure;

    @ElementCollection(targetClass = Interval.class)
    @Column(length=100000) //for the Data too long error
    private List<Interval> booking = new ArrayList<Interval>();

    @Id
    @GeneratedValue
    private Long id;

    /**
     * Default constructor
     */
    public Location() {
    }

    public Location(List<Interval> booking, Structure structure, Integer maxGuestsNumber, LocationType type) {
        this.booking = booking;
        this.structure = structure;
        this.type = type;
        this.maxGuestsNumber = maxGuestsNumber;
        structure.addLocation(this);
    }

    public Long getId() {
        return id;
    }

    public Structure getStructure() {
        return structure;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public String getDescription() {
        return description;
    }

    public String getLocationAddress() {
        return this.structure.getStructureAddress().getAddress();
    }

    public Integer getNumberOfBathrooms() {
        return numberOfBathrooms;
    }

    public void setNumberOfBathrooms(Integer numberOfBathrooms) {
        this.numberOfBathrooms = numberOfBathrooms;
    }

    public Integer getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(Integer numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    public Integer getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(Integer numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNumberOfBedrooms() {
        return numberOfBedrooms;
    }

    public void setNumberOfBedrooms(Integer numberOfBedrooms) {
        this.numberOfBedrooms = numberOfBedrooms;
    }

    public LocationType getType() {
        return type;
    }

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
}