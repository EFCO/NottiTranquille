package it.ispw.efco.nottitranquille.model;


import org.joda.time.DateTime;
import org.joda.time.Interval;

import javax.persistence.*;
import java.util.*;

/**
 * {@See LocationType}
 *
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */

@Entity
public class Location extends Observer {

    /**
     * Default constructor
     */
    public Location() {
        this(null);
    }

    public Location(Structure structure) {
        this.structure = structure;

        booking = new ArrayList<Interval>();
        availableDate = new ArrayList<Interval>();
        services = new ArrayList<Service>();

        price = 0;

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToOne
    private LocationType type;

    @ManyToOne
    private Structure structure;

    @ElementCollection(targetClass = Interval.class)
    @Column(length = 100000)
    private List<Interval> booking;


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
     * Basic price for Location. It can be decorated with fee or discount
     */
    private float price;

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
     * @param reservation the Subject to observe. If reservatio's interval of days changes the Location
     *                    must update its available date.
     * @param arg the interval of days that now is not available for reservation.
     */
    @Override
    public void update(Subject reservation, Object arg) {
        if (!reservation.hasChanged())
            return;

        Interval notAvailable = (Interval) arg;

        List<Interval> newIntervals = new ArrayList<Interval>();

        DateTime notAvStart = notAvailable.getStart();
        DateTime notAvEnd = notAvailable.getEnd();

        Iterator iterator = availableDate.iterator();
        while (iterator.hasNext()) {
            Interval interval = (Interval) iterator.next();

            DateTime oldStart = interval.getStart();
            DateTime oldEnd = interval.getEnd();

            if ((oldStart.isEqual(notAvStart) && oldEnd.isEqual(notAvEnd)) ||
                    (oldStart.isBefore(notAvStart)) || (oldEnd.isAfter(notAvEnd))) {
                iterator.remove();
            }

            if (oldStart.isBefore(notAvStart)) {

                DateTime newEnd = notAvStart.minusDays(1);
                Interval newFirstInterval = new Interval(oldStart, newEnd);

                newIntervals.add(newFirstInterval);
            }

            if (oldEnd.isAfter(notAvEnd)) {

                DateTime newStart = notAvEnd.plusDays(1);
                Interval newSecondInterval = new Interval(newStart, oldEnd);

                newIntervals.add(newSecondInterval);

            }
        }

        availableDate.addAll(newIntervals);
    }


    /**
     *
     */
    public void addBookingDate(Interval date) {
        booking.add(date);
        getAvailableDate().add(date);
        //TODO check if date is already present
    }


    /**
     * @param interval : contiguous range of days that we want to test are available
     * @return bool
     */
    public boolean isAvailable(Interval interval) {
        for (Interval inter : this.booking) {
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

    public List<Interval> getBooking() {
        return booking;
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

    public float getPrice() {
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

    public void setBooking(List<Interval> booking) {
        this.booking = booking;
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

    public void setPrice(float price) {
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