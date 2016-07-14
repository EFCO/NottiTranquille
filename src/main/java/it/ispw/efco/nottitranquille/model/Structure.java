package it.ispw.efco.nottitranquille.model;

import org.joda.time.DateTime;

import javax.persistence.*;
import java.net.DatagramPacket;
import java.util.*;

/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
@Entity
public class Structure {

    @Id
    @GeneratedValue
    private Long id;

    /**
     *
     */
    private String name;

    /**
     *
     */
    private int numberOfLocations;

    /**
     *
     */
    @ElementCollection
    private List<String> photos;

    /**
     *
     */
    private String termsOfService;

    /**
     *
     */
    private String termsOfCancellation;

    /**
     *
     */
    private DateTime checkIn;

    /**
     *
     */
    private DateTime checkOut;

    @Transient
    private Person managedBy;

    @Transient
    //TODO Make List<Person>
    private Person owner;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    private Address address;

    @Transient
    private StructureType type;

    @Transient
    private List<Service> services;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Location> locations = new ArrayList<Location>();

    @Transient
    private Request request;


    public Structure() {
    }

    public Structure(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public Structure(List<Service> services) {
        this.services = services;
    }


    public String getName() {
        return name;
    }

    public Address getStructureAddress() {
        return address;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Long getId() {
        return id;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setNumberOfLocations(int numberOfLocations) {
        this.numberOfLocations = numberOfLocations;
    }

    public void setTermsOfService(String termsOfService) {
        this.termsOfService = termsOfService;
    }

    public void setTermsOfCancellation(String termsOfCancellation) {
        this.termsOfCancellation = termsOfCancellation;
    }

    public void setCheckIn(DateTime checkIn) {
        this.checkIn = checkIn;
    }

    public void setCheckOut(DateTime checkOut) {
        this.checkOut = checkOut;
    }

    public void setManagedBy(Person managedBy) {
        this.managedBy = managedBy;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public void setType(StructureType type) {
        this.type = type;
    }

    public Request getRequest() {
        return request;
    }

    public int getNumberOfLocations() {
        return numberOfLocations;
    }

    public StructureType getType() {
        return type;
    }

    public Person getManagedBy() {
        return managedBy;
    }

    public void addLocation(Location location) {
        this.locations.add(location);
    }

    @Override
    public String toString() {
        return "Structure{" +
                "name='" + name + '\'' +
                ", numberOfLocations='" + numberOfLocations + '\'' +
                ", photos=" + photos +
                ", termsOfService='" + termsOfService + '\'' +
                ", termsOfCancellation='" + termsOfCancellation + '\'' +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                ", managedBy=" + managedBy +
                ", owner=" + owner +
                ", address=" + address +
                ", type=" + type +
                ", services=" + services +
                ", id=" + id +
                '}';
    }
}