package it.ispw.efco.nottitranquille.model;

import javax.persistence.*;

/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */

@Entity
public class Address {

    private String nation;

    private String city;

    private String address;

    private String postalcode;

    public Address(String nation, String city, String address, String postalcode) {
        this.nation = nation;
        this.city = city;
        this.address = address;
        this.postalcode = postalcode;
    }

    /**
     * Default constructor
     */
    public Address() {

    }

    @Id
    @GeneratedValue
    private Long id;

    public Long getId() {
        return id;
    }
}