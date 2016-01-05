package it.ispw.efco.nottitranquille.model;

import javax.persistence.*;
import java.util.*;

/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
@Entity
public class Structure {

    public Structure(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    /**
     * Default constructor
     */
    public Structure() {

    }

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private String numberOfLocations;

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
    private Date checkIn;

    /**
     * 
     */
    private Date checkOut;

    @ManyToOne
    private Manager managedBy;

    @ManyToOne
    private Owner owner;

    @OneToOne(cascade = {CascadeType.ALL})
    private Address address;

    @Transient
    private StructureType type;

    @Transient
    private List<Service> services;


    private Long id;

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

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}