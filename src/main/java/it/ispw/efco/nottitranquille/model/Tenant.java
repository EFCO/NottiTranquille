package it.ispw.efco.nottitranquille.model;

import javax.persistence.*;
import java.util.*;

/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
@Entity
@DiscriminatorValue("Tenant")
public class Tenant extends Person {

    /**
     * Default constructor
     * @param reservations
     */
    public Tenant(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    /**
     * 
     */
    @Transient //TODO to resolve
    private List<Reservation> reservations;

    public Tenant() {
    }

    /**
     * @param location
     */
    public void reserve(Location location) {
        // TODO implement here
    }

}