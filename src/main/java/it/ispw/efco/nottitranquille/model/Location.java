package it.ispw.efco.nottitranquille.model;


import org.joda.time.DateTime;
import org.joda.time.Interval;

import javax.persistence.*;
import java.util.*;

/**
 * This class represents an habitable building that can be booked.
 * {@See LocationType}
 *
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */

@Entity
public class Location {

    /**
     * Default constructor
     */
    public Location() {
        this(null);
    }

    public Location(Structure structure) {
        this.structure = structure;

        booked = new ArrayList<Interval>();
        availableDate = new ArrayList<Interval>();
        services = new ArrayList<Service>();

        price = 0f;

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Name of the Location
     */
    private String name;
    /**
     * A type contains service for a specific class of location and
     * the method with it can be booked
     *
     * @see it.ispw.efco.nottitranquille.model.enumeration.ReservationType
     */
    @ManyToOne
    private LocationType type;

    /**
     * A structure is a set of locations
     */
    @ManyToOne
    private Structure structure;

    /**
     * List of interval of days in which this Location is occupied
     */
    @ElementCollection(targetClass = Interval.class)
    @Column(length = 100000)
    private List<Interval> booked;

    /**
     * List of interval of days in which this Location is still available
     */
    @ElementCollection(targetClass = Interval.class)
    @Column(length = 100000)
    private List<Interval> availableDate;

    /**
     * Description of the Location entered by the Manager
     */
    private String description;

    /**
     * Number of rooms of the Location
     */
    private Integer numberOfRooms;

    /**
     * Number of bathrooms of the Location
     */
    private Integer numberOfBathrooms;

    /**
     * Maximum number of Guest who can book the Location
     */
    private Integer maxGuestsNumber;

    /**
     * Number of Bedrooms in the Location
     */
    private Integer numberOfBedrooms;

    /**
     * Number of beds in the Location
     */
    private Integer numberOfBeds;

    /**
     * Basic price for Location. It can be decorated with fee or discount.
     */
    private Float price;

    @ManyToOne
    private Manager manager;

    /**
     * Not Used
     * photo of the Location
     */
    @Transient
    private String[] photos;

    @Transient
    List<Service> services;

    /**
     * If a Tenant reserved this Location, the system has to update the intervals of day still
     * available
     *
     * @param bookingPeriod the interval of days that now is not available for reservation.
     */
    public void bookPeriod(Interval bookingPeriod) throws IllegalArgumentException {
        boolean found = false;

        for (Interval avlPeriod : availableDate) {
            if (!avlPeriod.contains(bookingPeriod))
                break;


            found = true;

            DateTime oldStart = avlPeriod.getStart();
            DateTime oldEnd = avlPeriod.getEnd();

            DateTime bookingStart = bookingPeriod.getStart();
            DateTime bookingEnd = bookingPeriod.getEnd();

            availableDate.remove(avlPeriod);

            Interval interval1 = new Interval(oldStart, bookingStart);
            Interval interval2 = new Interval(oldEnd, bookingEnd);

            // Check that they not are zero duration intervals (ex. [29/10/2000 29/10/2000] )
            if (!interval1.contains(interval1)) {
                availableDate.add(interval1);
            }

            if (!interval2.contains(interval2))
                availableDate.add(interval2);

        }

        if (!found)
            throw new IllegalArgumentException("Interval of time not available for booking");

        booked.add(bookingPeriod);

    }

    /**
     * Check if the given interval of time is available to book the Location
     *
     * @param interval : contiguous range of days that we want to test are available
     * @return bool Return true if interval is available
     */
    public boolean isAvailable(Interval interval) {
        for (Interval inter : this.booked) {
            if (interval.isEqual(inter) || (inter.getStart().isBefore(interval.getStart())
                    && inter.getEnd().isAfter(interval.getEnd()))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method needs to update Location in the Database.
     * We have to instantiate a new Location with update attributes
     *
     * @param toUpdate: Location to update in database
     * @see it.ispw.efco.nottitranquille.model.dao.LocationDAO
     */
    public void update(Location toUpdate) {
        this.id = toUpdate.getId();
        this.name = toUpdate.getName();
        this.type = toUpdate.type;
        this.price = toUpdate.price;
        this.availableDate = toUpdate.availableDate;
        this.booked = toUpdate.booked;
        this.description = toUpdate.description;
        this.maxGuestsNumber = toUpdate.maxGuestsNumber;
        this.numberOfBathrooms = toUpdate.numberOfBathrooms;
        this.numberOfBedrooms = toUpdate.numberOfBedrooms;
        this.numberOfBeds = toUpdate.numberOfBeds;
        this.numberOfRooms = toUpdate.numberOfRooms;
    }

    /* Getter and Setter */

    public Long getId() {
        return id;
    }

    public LocationType getType() {
        return type;
    }

    public Structure getStructure() {
        return structure;
    }

    public List<Interval> getBooked() {
        return booked;
    }

    public List<Interval> getAvailableDate() {
        return this.availableDate;
    }

    public String getDescription() {
        return description;
    }

    public Integer getNumberOfRooms() {
        return numberOfRooms;
    }

    public Integer getNumberOfBathrooms() {
        return numberOfBathrooms;
    }

    public Integer getMaxGuestsNumber() {
        return maxGuestsNumber;
    }

    public Integer getNumberOfBedrooms() {
        return numberOfBedrooms;
    }

    public Integer getNumberOfBeds() {
        return numberOfBeds;
    }

    public String[] getPhotos() {
        return photos;
    }

    public Float getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(LocationType type) {
        this.type = type;
    }

    public void setStructure(Structure structure) {
        this.structure = structure;
    }

    public void setBooked(List<Interval> booked) {
        this.booked = booked;
    }

    public void setAvailableDate(List<Interval> availableDate) {
        this.availableDate = availableDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNumberOfRooms(Integer numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public void setNumberOfBathrooms(Integer numberOfBathrooms) {
        this.numberOfBathrooms = numberOfBathrooms;
    }

    public void setMaxGuestsNumber(Integer maxGuestsNumber) {
        this.maxGuestsNumber = maxGuestsNumber;
    }

    public void setNumberOfBedrooms(Integer numberOfBedrooms) {
        this.numberOfBedrooms = numberOfBedrooms;
    }

    public void setNumberOfBeds(Integer numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    public void setPhotos(String[] photos) {
        this.photos = photos;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }
}