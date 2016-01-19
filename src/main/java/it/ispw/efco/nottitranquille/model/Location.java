package it.ispw.efco.nottitranquille.model;

import it.ispw.efco.nottitranquille.model.enumeration.ReservationType;
import org.hibernate.annotations.CollectionType;
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.Type;
import org.jadira.usertype.dateandtime.joda.PersistentInterval;
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
public class Location {

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
    private List<Interval> AvailableDate;


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
     * Not Used
     * photo of the Location
     */
    @Transient
    private String[] photos;

    /**
     * Basic price for Location. It can be decorated with fee or discount
     */
    @Transient
    private float price;

    /**
     * Default constructor
     */
    public Location() {
        this(null);
    }

    public Location(Structure structure) {
        this.structure = structure;
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
        return AvailableDate;
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
        AvailableDate = availableDate;
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


}