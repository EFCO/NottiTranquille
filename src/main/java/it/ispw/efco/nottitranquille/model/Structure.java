package it.ispw.efco.nottitranquille.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.*;

/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
@Entity
public class Structure {

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

    private Manager managedBy;

    private Owner owner;

    private Address address;

    private StructureType type;

    private List<Service> services;


    private String id;

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}