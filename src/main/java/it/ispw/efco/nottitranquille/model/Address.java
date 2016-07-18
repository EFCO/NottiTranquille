package it.ispw.efco.nottitranquille.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Represents a physical address of a {@link Structure} or of a {@link Person}
 *
 * @see Structure
 * @see Person
 *
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
@Entity
public class Address {

    @Id
    @GeneratedValue
    private Long id;

    /**
     * The nation
     */
    private String nation;

    /**
     * The city
     */
    private String city;

    /**
     * The address
     */
    private String address;

    /**
     * The postal code
     */
    private String postalCode;

    /**
     * Default constructor
     */
    public Address() {
    }

    public Address(String nation, String city, String address, String postalCode) {
        this.nation = nation;
        this.city = city;
        this.address = address;
        this.postalCode = postalCode;
    }

    public Long getId() {
        return id;
    }

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

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return address + ", " + city + ", " + postalCode + " " + nation;
    }
}