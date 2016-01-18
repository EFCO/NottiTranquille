package it.ispw.efco.nottitranquille.model;

import org.hibernate.annotations.Columns;
import org.hibernate.annotations.Type;
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

    @OneToOne
    private LocationType type;

    @ManyToOne
    private Structure structure;

    @ElementCollection
    private List<Interval> requestDate;


    @ElementCollection
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
     * Number of Bedroom in the Location
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
    }

    public Location(String name, String description){
        this.name=name;
        this.description=description;
    }


    public Long getId() {
        return id;
    }

    public LocationType getType() {
        return type;
    }

    public Structure getStructure() {
        return structure;
    }

    public List<Interval> getRequestDate() {
        return requestDate;
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

    public void setRequestDate(List<Interval> requestDate) {
        this.requestDate = requestDate;
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