package it.ispw.efco.nottitranquille.model;

import javax.persistence.*;
import javax.persistence.Entity;
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

    public Structure(List<Service> services) {
        this.services = services;
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

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    private Address address;

    @ManyToOne
    private StructureType type;

    @OneToMany
    private List<Service> services;

    public void setRequest(Request request) {
        this.request = request;
    }

    @OneToOne(optional=false, mappedBy="structure")
    private Request request;

    public Structure() {
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

    @Id
    @GeneratedValue
    private Long id;

    public Long getId() {
        return id;
    }

}