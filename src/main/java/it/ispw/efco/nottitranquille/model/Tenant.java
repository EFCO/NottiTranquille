package it.ispw.efco.nottitranquille.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.*;

/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
@Entity
@DiscriminatorValue("Tenant")
public class Tenant extends Person {

    /**
     * Default constructor
     */
    public Tenant() {
    }

    /**
     * 
     */
    @Transient //TODO
    public List<Reservation> reservations;



    /**
     * @param Location
     */
    public void reserve(Location location) {
        // TODO implement here
    }

}