package it.ispw.efco.nottitranquille.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.*;

/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */

@Entity
public class Address {

    private String nation;
    private String city;
    private String address;
    private String postalcode;
    /**
     * Default constructor
     */
    public Address() {
    }


    private String id;

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}