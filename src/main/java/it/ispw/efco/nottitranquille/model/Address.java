package it.ispw.efco.nottitranquille.model;

import javax.persistence.*;

/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */

@Entity
public class Address {

    private String nation;

    private String city;

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

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

    public String getAddress() {
        return address;
    }
}