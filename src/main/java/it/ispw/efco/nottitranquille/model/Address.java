package it.ispw.efco.nottitranquille.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
@Entity
public class Address {

    private Long id;

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

    public Address() {
    }

    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}