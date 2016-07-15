package it.ispw.efco.nottitranquille.model;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;
import org.joda.time.DateTime;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.*;

/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
@Entity
@SuppressWarnings("JpaDataSourceORMInspection")
public class Structure {

    /**
     * Default constructor
     */
    public Structure() {
        this(null);
    }

    public Structure(String name) {
        this(name, null);
    }

    public Structure(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    /**
     *
     */
    private String name;

    /**
     *
     */
    private String description;

    /**
     *
     */
    private Integer numberOfLocations;

    /**
     *
     */
    @Transient
    private Set<String> photos;

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
    @Transient
    private DateTime checkIn;

    /**
     *
     */
    @Transient
    private DateTime checkOut;

    @ManyToOne
    private Manager managedBy;

    @ManyToOne
    private Owner owner;

    @OneToOne
    @Cascade(CascadeType.ALL)
    private Address address;

//    @ManyToOne
//    private StructureType type;

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
//                ", address=" + address +
//                ", type=" + type +
                ", id=" + id +
                '}';
    }

    @Id
    @GeneratedValue
    private Long id;


    /* GETTER AND SETTER */

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNumberOfLocations() {
        return numberOfLocations;
    }

    public void setNumberOfLocations(Integer numberOfLocations) {
        this.numberOfLocations = numberOfLocations;
    }

    public Set<String> getPhotos() {
        return photos;
    }

    public void setPhotos(Set<String> photos) {
        this.photos = photos;
    }

    public String getTermsOfService() {
        return termsOfService;
    }

    public void setTermsOfService(String termsOfService) {
        this.termsOfService = termsOfService;
    }

    public String getTermsOfCancellation() {
        return termsOfCancellation;
    }

    public void setTermsOfCancellation(String termsOfCancellation) {
        this.termsOfCancellation = termsOfCancellation;
    }

    public DateTime getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(DateTime checkIn) {
        this.checkIn = checkIn;
    }

    public DateTime getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(DateTime checkOut) {
        this.checkOut = checkOut;
    }

    public Manager getManagedBy() {
        return managedBy;
    }

    public void setManagedBy(Manager managedBy) {
        this.managedBy = managedBy;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

//    public StructureType getType() {
//        return type;
//    }
//
//    public void setType(StructureType type) {
//        this.type = type;
//    }

    public void setId(Long id) {
        this.id = id;
    }
}